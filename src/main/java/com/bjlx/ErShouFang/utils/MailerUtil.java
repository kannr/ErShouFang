package com.bjlx.ErShouFang.utils;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.util.ByteArrayDataSource;

import java.io.UnsupportedEncodingException;
import java.util.*;

public class MailerUtil {
	public String mailServerHost;
	public String mailServerPort = "25";
	public String userName;
	public String password;

	public MailerUtil(String mailServerHost, String mailServerPort, String userName, String password) {
		super();
		this.mailServerHost = mailServerHost;
		this.mailServerPort = mailServerPort;
		this.userName = userName;
		this.password = password;
	}

	public MailerUtil(String mailServerHost, String userName, String password) {
		super();
		this.mailServerHost = mailServerHost;
		this.userName = userName;
		this.password = password;
	}

	public Properties getProperties() {
		Properties p = new Properties();
		p.put("mail.smtp.host", this.mailServerHost);
		p.put("mail.smtp.port", this.mailServerPort);
		p.put("mail.smtp.auth", "true");
		return p;
	}

	public static class OctopusMail {
		public String fromAddress;
		public String toAddress;
		public String ccAddress;
		public String subject;
		public String content;
		public List<String> attachFileNames = new ArrayList<String>();
		public Map<String, byte[]> binAttachments = null;
	}

	protected static class MyAuthenticator extends Authenticator {
		String userName = null;
		String password = null;

		public MyAuthenticator(String username, String password) {
			this.userName = username;
			this.password = password;
		}

		@Override
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(userName, password);
		}
	}

	public static InternetAddress GetAddressObj(String address) {
		String alias = null;
		int delimIdx = address.indexOf('=');
		if (delimIdx > 0) {
			alias = address.substring(delimIdx + 1);
			address = address.substring(0, delimIdx);
		}
		InternetAddress addrObj = null;
		if (alias == null) {
			try {
				addrObj = new InternetAddress(address);
			} catch (AddressException e) {
				e.printStackTrace();
			}
		} else
			try {
				addrObj = new InternetAddress(address, alias);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		return addrObj;
	}

	/***
	 * Send text mail via OctopusMail object
	 * @param mail
	 * @return
	 */
	public boolean sendTextMail(OctopusMail mail) {
		MyAuthenticator authenticator = null;
		Properties pro = getProperties();
		authenticator = new MyAuthenticator(userName, password);
		Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
		try {
			Message mailMessage = new MimeMessage(sendMailSession);
			Address from = GetAddressObj(mail.fromAddress);
			mailMessage.setFrom(from);
			String[] toList = mail.toAddress.split(";");
			Address[] to = new InternetAddress[toList.length];
			for (int i = 0; i < toList.length; i++)
				to[i] = GetAddressObj(toList[i]);
			mailMessage.setRecipients(Message.RecipientType.TO, to);
			if("".equals(mail.ccAddress) || mail.ccAddress == null) {
				// 不设置抄送
			} else {
				// 设置抄送
				String[] ccList = mail.ccAddress.split(";");
				Address[] cc = new InternetAddress[ccList.length];
				for (int i = 0; i < ccList.length; i++)
					cc[i] = GetAddressObj(ccList[i]);
				mailMessage.setRecipients(Message.RecipientType.CC, cc);
			}
			
			mailMessage.setSubject(mail.subject);
			mailMessage.setSentDate(new Date());
			String mailContent = mail.content;
			mailMessage.setText(mailContent);
			for (int i = 0; i < 3; i++)
				try {
					Transport.send(mailMessage);
					break;
				} catch (Exception e) {
				}
			return true;
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	/***
	 * Send simple text mail
	 * @param mailSubject
	 * @param mailBody
	 * @param fromAddress
	 * @param toAddress
	 * @param ccAddress
	 * @return
	 */
	public boolean sendTextMail(String mailSubject, String mailBody, String fromAddress, String toAddress,
			String ccAddress) {
		OctopusMail mail = new OctopusMail();
		mail.fromAddress = fromAddress;
		mail.toAddress = toAddress;
		mail.ccAddress = ccAddress;
		mail.subject = mailSubject;
		mail.content = mailBody;
		return sendTextMail(mail);
	}

	/***
	 * Send html mail via OctopusMail object
	 * @param mail
	 * @return
	 */
	public boolean sendHtmlMail(OctopusMail mail) {
		MyAuthenticator authenticator = null;
		Properties pro = getProperties();
		authenticator = new MyAuthenticator(userName, password);
		Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
		try {
			Message mailMessage = new MimeMessage(sendMailSession);
			Address from = GetAddressObj(mail.fromAddress);
			mailMessage.setFrom(from);

			String[] toList = mail.toAddress.split(";");
			String[] ccList = mail.ccAddress.split(";");
			Address[] to = new InternetAddress[toList.length];
			Address[] cc = new InternetAddress[ccList.length];

			for (int i = 0; i < toList.length; i++)
				to[i] = GetAddressObj(toList[i]);
			for (int i = 0; i < ccList.length; i++)
				cc[i] = GetAddressObj(ccList[i]);
			mailMessage.setRecipients(Message.RecipientType.TO, to);
			mailMessage.setRecipients(Message.RecipientType.CC, cc);

			mailMessage.setSubject(mail.subject);
			mailMessage.setSentDate(new Date());

			MimeMultipart mainMM = new MimeMultipart();
			mainMM.setSubType("related");
			BodyPart bodyPart = new MimeBodyPart();
			bodyPart.setContent(mail.content, "text/html; charset=utf-8");
			mainMM.addBodyPart(bodyPart);

			BodyPart mainBP = new MimeBodyPart();
			mainBP.setContent(mainMM);

			MimeMultipart wholeMM = new MimeMultipart();
			wholeMM.setSubType("mixed");
			wholeMM.addBodyPart(mainBP);

			if (mail.binAttachments != null)
				for (String fileName : mail.binAttachments.keySet()) {
					boolean isInline = fileName.startsWith("@");
					if (isInline)
						fileName = fileName.substring(1);
					MimeBodyPart part = new MimeBodyPart();
					byte[] bin = mail.binAttachments.get(fileName);
					ByteArrayDataSource bads = new ByteArrayDataSource(bin, "application/octet-stream");
					part.setDataHandler(new DataHandler(bads));
					if (isInline)
						try {
							part.setContentID(MimeUtility.encodeText(fileName));
							mainMM.addBodyPart(part);
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
					else
						try {
							part.setFileName(MimeUtility.encodeText(fileName));
							wholeMM.addBodyPart(part);
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
				}

			if (mail.attachFileNames != null)
				for (String fileName : mail.attachFileNames) {
					boolean isInline = fileName.startsWith("@");
					if (isInline)
						fileName = fileName.substring(1);
					MimeBodyPart part = new MimeBodyPart();
					FileDataSource fds = new FileDataSource(fileName);
					part.setDataHandler(new DataHandler(fds));
					if (isInline)
						try {
							part.setContentID(MimeUtility.encodeText(fds.getName()));
							mainMM.addBodyPart(part);
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
					else
						try {
							part.setFileName(MimeUtility.encodeText(fds.getName()));
							wholeMM.addBodyPart(part);
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
				}

			mailMessage.setContent(wholeMM);
			for (int i = 0; i < 3; i++)
				try {
					Transport.send(mailMessage);
					break;
				} catch (Exception e) {
					e.printStackTrace();
				}
			return true;
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	/***
	 * Send rich format email.
	 * @param mailSubject
	 * @param mailBody
	 * @param fromAddress
	 * @param toAddress
	 * @param ccAddress
	 * @return
	 */
	public boolean sendHtmlMail(String mailSubject, String mailBody, String fromAddress, String toAddress,
			String ccAddress) {
		if (fromAddress == null)
			fromAddress = "";
		if (toAddress == null)
			toAddress = "";
		if (ccAddress == null)
			ccAddress = "";
		OctopusMail mail = new OctopusMail();
		mail.fromAddress = fromAddress;
		mail.toAddress = toAddress;
		mail.ccAddress = ccAddress;
		mail.subject = mailSubject;
		mail.content = mailBody;
		return sendHtmlMail(mail);
	}

	/***
	 * Send rich format email.
	 * @param mailSubject
	 * @param mailBody
	 * @param fromAddress
	 * @param toAddress
	 * @param ccAddress
	 * @param attatchment Files to be attached. if multiple, please seperate the paths with semicolon.
	 * @return
	 */
	public boolean sendHtmlMail(String mailSubject, String mailBody, String fromAddress, String toAddress,
			String ccAddress, String attatchment) {
		OctopusMail mail = new OctopusMail();
		mail.fromAddress = fromAddress;
		mail.toAddress = toAddress;
		mail.ccAddress = ccAddress;
		if (mail.fromAddress == null)
			mail.fromAddress = "";
		if (mail.toAddress == null)
			mail.toAddress = "";
		if (mail.ccAddress == null)
			mail.ccAddress = "";
		mail.subject = mailSubject;
		mail.content = mailBody;
		String[] fileNameList = attatchment.split(";");
		for (String fileName : fileNameList)
			mail.attachFileNames.add(fileName);
		return sendHtmlMail(mail);
	}

	/***
	 * Send rich format email.
	 * @param mailSubject
	 * @param mailBody
	 * @param fromAddress
	 * @param toAddress
	 * @param ccAddress
	 * @param attatchment Files to be attached. 
	 * @return
	 */
	public boolean sendHtmlMail(String mailSubject, String mailBody, String fromAddress, String toAddress,
			String ccAddress, Map<String, byte[]> attatchment) {
		OctopusMail mail = new OctopusMail();
		mail.fromAddress = fromAddress;
		mail.toAddress = toAddress;
		mail.ccAddress = ccAddress;
		mail.subject = mailSubject;
		mail.content = mailBody;
		mail.binAttachments = attatchment;
		return sendHtmlMail(mail);
	}

	/***
	 * Send rich html email.
	 * @param mailSubject
	 * @param mailBody
	 * @param fromAddress
	 * @param toAddress
	 * @param ccAddress
	 * @param attName
	 * @param attBin
	 * @return
	 */
	public boolean sendHtmlMail(String mailSubject, String mailBody, String fromAddress, String toAddress,
			String ccAddress, String attName, byte[] attBin) {
		OctopusMail mail = new OctopusMail();
		mail.fromAddress = fromAddress;
		mail.toAddress = toAddress;
		mail.ccAddress = ccAddress;
		mail.subject = mailSubject;
		mail.content = mailBody;
		mail.binAttachments = new HashMap<String, byte[]>();
		mail.binAttachments.put(attName, attBin);
		return sendHtmlMail(mail);
	}

	private static MailerUtil mailer = new MailerUtil("smtp.exmail.qq.com", "25", "service@bujilvxing.com", "BuJiLvXing168");
	
	public static boolean sendSimpleEmail(String mailSubject, String mailBody, String fromAddress,
			String toAddress, String ccAddress) {
		if (mailer == null)
			throw new RuntimeException("Mailer has not been initialized!");
		return mailer.sendTextMail(mailSubject, mailBody, fromAddress, toAddress, ccAddress);
	}
}

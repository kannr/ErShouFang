package com.bjlx.ErShouFang.core;


import com.bjlx.ErShouFang.core.formatter.account.UserInfoFormatter;
import com.bjlx.ErShouFang.model.account.Credential;
import com.bjlx.ErShouFang.model.account.SecretKey;
import com.bjlx.ErShouFang.model.account.UserInfo;
import com.bjlx.ErShouFang.model.misc.ValidationCode;
import com.bjlx.ErShouFang.utils.ErShouFangResult;
import com.bjlx.ErShouFang.utils.ErrorCode;
import com.bjlx.ErShouFang.utils.MorphiaFactory;
import com.bjlx.ErShouFang.utils.SmsSendUtil;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * 账户核心实现
 * Created by xiaozhi on 2016/10/21.
 */
public class AccountAPI {

	/**
	 * 取得数据库对象
	 */
	private static Datastore ds = MorphiaFactory.getInstance();
	
	
    /**
     * 初始化一个mapper对象
     */
    public static ObjectMapper mapper = new ObjectMapper().enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);
    
    /**
     * 是否允许发送验证码
     * @param tel 手机号
     * @return 是否允许
     */
    public static boolean isAllowSendValidationCode(String tel)  throws Exception {
    	long currentTime = System.currentTimeMillis();
    	
    	Query<ValidationCode> query = ds.createQuery(ValidationCode.class).field("action").equal(1);
    	query.field(ValidationCode.fd_tel).equal(tel).field(ValidationCode.fd_resendTime).greaterThan(currentTime);

    	try {
    		return query.get() == null;
        } catch (Exception e) {
         	throw e;
        }
    }

	/**
	 * 发送验证码
	 * @param tel 手机号
	 * @param action 验证码的用途
	 * @return 发送结果
	 * @throws Exception 运行时异常
	 */
    public static String sendValidationCode(String tel, int action) throws Exception {
    	// 产生验证码
        String code = String.format("%d", (int) (100000 + Math.random() * 900000));
        // 短信内容
        String[] smsdata = new String[1];
        smsdata[0] = code;
        Long currentTime = System.currentTimeMillis();
        ValidationCode result = null;
        Query<ValidationCode> query = ds.createQuery(ValidationCode.class);
        UpdateOperations<ValidationCode> ops = ds.createUpdateOperations(ValidationCode.class);
		ValidationCode validationCode = new ValidationCode(currentTime, currentTime + 6 * 60 * 1000, code, tel, action);
		// 存数据库
		query.field(ValidationCode.fd_tel).equal(tel);

		ops.set(ValidationCode.fd_tel, validationCode.getTel())
			.set(ValidationCode.fd_createTime, validationCode.getCreateTime())
				.set(ValidationCode.fd_expireTime, validationCode.getExpireTime())
				.set(ValidationCode.fd_action, validationCode.getAction())
				.set(ValidationCode.fd_code, validationCode.getCode())
				.set(ValidationCode.fd_lastSendTime, validationCode.getLastSendTime())
				.set(ValidationCode.fd_used, validationCode.isUsed())
				.set(ValidationCode.fd_resendTime, validationCode.getResendTime())
				.set(ValidationCode.fd_failCnt, validationCode.getFailCnt());
		int smsResult = 0;
		try {
			result = ds.findAndModify(query, ops, false, true);
			smsResult = SmsSendUtil.sendMessageByTemplate(tel, smsdata);
		} catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		// 发送短信

		switch(smsResult) {
			case -1: return ErShouFangResult.getResult(ErrorCode.NETWORK_ERROR);
			case 1:  break;// 成功
			case 2: return ErShouFangResult.getResult(ErrorCode.TIME_LIMIT_1001);
			case 3:
			case 4:
			case 5:
				return ErShouFangResult.getResult(ErrorCode.REQUEST_TOO_MANY_1001);
			default:
				return ErShouFangResult.getResult(ErrorCode.UNKNOWN);
		}
        ObjectMapper mapper = com.bjlx.ErShouFang.core.formatter.misc.ValidationCodeFormatter.getMapper();
        return ErShouFangResult.ok(mapper.valueToTree(result));
    }

	/**
	 * 是否验证码，验证码必须是6位的数字
	 * @param code 验证码
	 * @return 是否验证码
	 * @throws PatternSyntaxException 模式匹配异常
	 */
	public static boolean isCode(String code) throws PatternSyntaxException {
		String regExp = "^\\d{6}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(code);
		return m.matches();
	}

	/**
	 * 检验验证码的合法性，并返回合法的令牌
	 * @param tel 手机号
	 * @param code 验证码
	 * @return 令牌
	 * @throws Exception 运行时异常
	 */
    public static boolean checkValidationCode(String tel, String code) throws Exception {
		Query<ValidationCode> query = ds.createQuery(ValidationCode.class);

		long currentTime = System.currentTimeMillis();
		/**
		 * 符合条件的验证码
		 * 1、没有过期
		 * 2、没有被使用过
		 * 3、验证错误次数没有超过10次
		 * 4、账户和验证码正确
		 */
		query.field(ValidationCode.fd_tel).equal(tel).field(ValidationCode.fd_expireTime).greaterThanOrEq(currentTime).field(ValidationCode.fd_used).equal(false)
				.field(ValidationCode.fd_code).equal(code).field(ValidationCode.fd_failCnt).lessThanOrEq(10);

		UpdateOperations<ValidationCode> ops = ds.createUpdateOperations(ValidationCode.class);
		try {
			if (query.get() == null) {
				// 验证错误次数增加一次
				ops.inc(ValidationCode.fd_failCnt);
				ds.updateFirst(query, ops);
				return false;
			} else {
				// 将验证码设置为已使用
				ops.set(ValidationCode.fd_used, true);
				ds.updateFirst(query, ops);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
    }


	/**
	 * 添加用户联系信息
	 * @param name 用户姓名
	 * @param weixin 微信号
	 * @param tel 手机号
	 * @return 结果信息
	 * @throws Exception 异常
	 */
	public static String addContact(String name, String weixin, String tel) throws Exception {
		return null;
	}

	/**
	 * 取得数字或者大写字母
	 * @return 数字或者大写字母的字符
	 */
	private static char getChar() {
		int r =  (int)(Math.random() * 36);
		int asciiCode = 0;
		if (r < 10)
			asciiCode = r + 48;
		else
			asciiCode = r - 10 + 65;
		return (char)asciiCode;
	}

	/**
	 * 取得邀请码
	 * @param length 邀请码长度
	 * @return 邀请码
	 */
	private static String getPromotionCode(int length) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < length; i++) {
			sb.append(getChar());
		}
		return sb.toString();
	}

	/**
	 * 第三方登录
	 * @param provider 第三方平台名称
	 * @param oauthId 第三方平台的用户id
	 * @param nickName 第三方平台的用户昵称
	 * @param avatar 第三方平台的用户头像
	 * @param token 第三方平台的用户令牌
	 * @return 用户信息
	 */
	public static String oauthlogin(String provider, String oauthId, String nickName, String avatar, String token, Integer promotionCodeSize) throws Exception {
		// 查询用户是否存在
		Query<UserInfo> query = ds.createQuery(UserInfo.class).field(UserInfo.fd_oauthId).equal(oauthId).field(UserInfo.fd_provider).equal(provider);

		int defaultPromotionCodeSize = 8;
		try {
			UserInfo userInfo = query.get();
			if(userInfo == null) {
				/**
				 * 创建新用户
 				 */
				// 生成邀请码
				String promotionCode = getPromotionCode(promotionCodeSize == null ? defaultPromotionCodeSize : promotionCodeSize);
				userInfo = new UserInfo(provider, oauthId, nickName, avatar, token, promotionCode);
				SecretKey secretKey = new SecretKey();
				Credential credential = new Credential(oauthId, secretKey);

				ds.save(userInfo);
				ds.save(credential);

				// 授权码
				userInfo.setKey(secretKey.getKey());
			} else {
				// 授权码
				Query<Credential> queryCredential = ds.createQuery(Credential.class).field(Credential.fd_oauthId).equal(userInfo.getOauthId());
				SecretKey secretKey = new SecretKey();
				UpdateOperations<Credential> opsCredential = ds.createUpdateOperations(Credential.class).set(Credential.fd_secretKey, secretKey);
				ds.updateFirst(queryCredential, opsCredential);
				userInfo.setKey(secretKey.getKey());
			}

			return ErShouFangResult.ok(UserInfoFormatter.getMapper().valueToTree(userInfo));
		} catch(Exception e1) {
			e1.printStackTrace();
			throw e1;
		}
	}

	/**
	 * 不羁旅行令牌是否合法
	 * @param oauthId 用户id
	 * @param key 不羁旅行令牌
	 * @return 是否合法
	 * @throws Exception 异常
	 */
	public static boolean delKey(String oauthId, String key) throws Exception {
		Query<Credential> query = ds.createQuery(Credential.class).field(Credential.fd_oauthId).equal(oauthId).field(Credential.fd_key).equal(key);
		UpdateOperations<Credential> ops = ds.createUpdateOperations(Credential.class)
				.unset(Credential.fd_secretKey);
		try {
			return ds.findAndModify(query, ops, true) != null;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 登出
	 * @param key 不羁旅行令牌
	 * @return 结果
	 */
	public static String logout(String oauthId, String key) throws Exception {
		try {
			if(delKey(oauthId, key)) {
				return ErShouFangResult.ok();
			} else {
				return ErShouFangResult.getResult(ErrorCode.UNLOGIN_1006);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
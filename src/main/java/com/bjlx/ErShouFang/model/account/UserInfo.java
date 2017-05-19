package com.bjlx.ErShouFang.model.account;

import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.NotBlank;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Transient;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 用户数据
 * @author xiaozhi
 *
 */
@Entity
public class UserInfo {

	@Transient
	public final static String fd_id = "id";
	@Transient
	public final static String fd_provider = "provider";
	@Transient
	public final static String fd_oauthId = "oauthId";
	@Transient
	public final static String fd_nickName = "nickName";
	@Transient
	public final static String fd_avatar = "avatar";
	@Transient
	public final static String fd_token = "token";
	@Transient
	public final static String fd_createTime = "createTime";
	@Transient
	public final static String fd_loginTime = "loginTime";
	@Transient
	public final static String fd_realName = "realName";
	@Transient
	public final static String fd_weixin = "weixin";
	@Transient
	public final static String fd_tel = "tel";
	@Transient
	public final static String fd_key = "key";
	@Transient
	public final static String fd_number = "number";

	/**
	 * 主键
	 */
	@NotBlank
	@Id
	private ObjectId id = null;

	/**
	 * 第三方账号体系的名称。比如：weixin表示这是微信账号
	 */
	@NotNull
	private String provider;

	/**
	 * 用户在第三方账号体系中的id
	 */
	@NotNull
	private String oauthId;

	/**
	 * 用户在第三方账号的昵称
	 */
	@NotNull
	private String nickName;

	/**
	 * 用户在第三方账号的头像
	 */
	private String avatar;

	/**
	 * 用户在第三方账号的token
	 */
	private String token;

	/**
	 * 创建时间
	 */
	private Long createTime;

	/**
	 * 登录时间
	 */
	private Long loginTime;

	/**
	 * 身份证号码（15位或者18位）
	 */
	@NotBlank
	@Pattern(regexp = "([\\d]{17}[\\dX]|[\\d]{15})")
	private String number;

	/**
	 * 真实姓名
	 */
	private String realName;

	/**
	 * 手机号
	 */
	private String tel;

	/**
	 * 微信号
	 */
	private String weixin;

	/**
	 * 授权码
	 */
	@Transient
	private String key;

	/**
	 * 邀请码
	 */
	private String promotionCode;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public String getOauthId() {
		return oauthId;
	}

	public void setOauthId(String oauthId) {
		this.oauthId = oauthId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Long loginTime) {
		this.loginTime = loginTime;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public UserInfo() {}

	public UserInfo(String provider, String oauthId, String nickName, String avatar, String token, String promotionCode) {
		this.id = new ObjectId();
		this.provider = provider;
		this.oauthId = oauthId;
		this.nickName = nickName;
		this.avatar = avatar;
		this.token = token;
		this.createTime = System.currentTimeMillis();
		this.promotionCode = promotionCode;
	}
}

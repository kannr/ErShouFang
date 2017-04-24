package com.bjlx.ErShouFang.utils;

/**
 * 错误码
 * 所有接口的错误码都在这里，除了通用错误，其他的错误码都是按照接口来定的
 * 错误码由6位数字构成，每1位的含义如下：
 * 第1位：表示产品编号
 * 第2~4位：表示接口编号
 * 第5~6位：表示指定接口的错误编号
 * 比如100101表示第一个产品的第一个接口的第一个错误编号，这样可以根据错误码，定位到是哪个接口出现了什么问题
 *
 * 错误名称定义是由错误描述加上接口编号，比如：TEL_FORMAT_1001表示手机号格式错误，是1001接口抛出来的
 */
public enum ErrorCode {

	/**
	 * 通用错误
	 */
	OK(0, "Normal"),
	INVALID_ARGUMENTS(100, "Invalid arguments"),
	FORBIDDEN(403, "Forbidden"), 
	NOT_FOUND(404, "Resource not found"),
	SERVER_EXCEPTION(500, "Server Exception"),
	ALIPAY_REFUND(901, "Ali pay refund need manual operation"),
	UNKNOWN(999, "Unknown error"),
	NETWORK_ERROR(601, "网络异常"),

	// 图片
	IMAGE_KEY_NULL(100001, "图片key为空"),
	IMAGE_BUCKET_NULL(100002, "图片bucket为空"),
	IMAGE_URL_NULL(100003, "图片url为空"),
	IMAGE_WIDTH_NULL(100004, "图片width为空"),
	IMAGE_HEIGHT_NULL(100005, "图片height为空"),
	IMAGE_FMT_NULL(100006, "图片fmt为空"),
	IMAGE_HASH_NULL(100007, "图片hash为空"),
	IMAGE_SIZE_NULL(100008, "图片size为空"),

	/**
	 * 验证码
 	 */

	// 发送验证码
	TEL_NULL_1001(100101, "参数账户为空"),
	TEL_FORMAT_1001(100102, "账户格式不正确"),
	ACTION_NULL_1001(100103, "参数action为空"),
	ACTION_LIMIT_1001(100104, "参数action的值不合法"),
	TIME_LIMIT_1001(100105, "请求过于频繁，请稍后再试"),
	REQUEST_TOO_MANY_1001(100106, "请求次数过多"),

	// 检验验证码
	TEL_NULL_1002(100201, "参数账户为空"),
	TEL_FORMAT_1002(100202, "账户格式不正确"),
	CODE_NULL_1002(100203, "参数验证码为空"),
	CODE_INVALID_1002(100204, "验证码不合法"),
	VALIDATION_FAIL_1002(100205, "验证失败"),

	/**
	 * 注册登录
	 */
	// 用户第三方登录
	PROVIDER_NULL_1005(100501, "参数provider为空"),
	OAUTHID_NULL_1005(100502, "参数oauthId为空"),
	TOKEN_NULL_1005(100503, "参数token为空"),
	CLIENTID_NULL_1005(100504, "参数clientId为空"),
	PROVIDER_INVALID_1005(100505, "参数provider不合法"),

	// 登出
	UNLOGIN_1006(100601, "用户未登录"),

	/**
	 * 其他模块
	 */
	// 用户反馈
	CONTENT_NULL_1014(101401, "参数content为空"),
	UNLOGIN_1014(101402, "用户未登录"),


	/**
	 * 收藏
	 */
	// 添加收藏
	FAVORITETYPE_NULL_1098(109801, "收藏类型不可为空"),
	ITEMID_NULL_1098(109802, "收藏对象id不可为空"),
	TITLE_NULL_1098(109803, "收藏标题不可为空"),
	UNLOGIN_1098(109804, "用户未登录"),
	FAVORITETYPE_INVALID_1098(109805, "favoriteType不合法"),
	ITEMID_INVALID_1098(109806, "itemId不合法"),

	// 取消收藏
	FAVORITETYPE_NULL_1099(109901, "收藏类型不可为空"),
	FAVORITETYPE_INVALID_1099(109902, "favoriteType不合法"),
	UNLOGIN_1099(109903, "用户未登录"),

	// 取得收藏列表
	UNLOGIN_1100(110001, "用户未登录"),


	/**
	 * 搜索
	 */
	// 用户搜索
	QUERY_NULL_1107(110701, "query不可为空"),
	UNLOGIN_1107(110702, "用户未登录"),
	QUERY_INVALID_1107(110703, "query不合法"),
	USER_NOT_EXIST_1107(110704, "用户不存在"),

	// 群组搜索
	QUERY_NULL_1108(110801, "query不可为空"),
	UNLOGIN_1108(110802, "用户未登录"),

	// 全站搜索
	QUERY_NULL_1109(110901, "query不可为空"),

	;









	public String msg;

	public int code;
	
	private ErrorCode(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public String getMsg() {
		return this.msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public int getCode() {
		return this.code;
	}
	
	public void setCode(int code) {
		this.code = code;
	}
}

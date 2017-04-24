package com.bjlx.ErShouFang.model.account;

import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.NotBlank;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Transient;

import javax.validation.constraints.NotNull;

/**
 * 用户收藏
 * @author xiaozhi
 *
 */
@Entity
public class Favorite {

	@Transient
	public final static String fd_id = "id";
	@Transient
	public final static String fd_oauthId = "oauthId";
	@Transient
	public final static String fd_favoriteType = "favoriteType";
	@Transient
	public final static String fd_itemId = "itemId";
	@Transient
	public final static String fd_title = "title";
	@Transient
	public final static String fd_favoriteTime = "favoriteTime";
	/**
	 * 主键
	 */
	@NotBlank
	@Id
	private ObjectId id = null;
	
	/**
	 * 用户ID
	 */
	@NotNull
	private String oauthId;

	/**
	 * 收藏的类型。1表示房源信息
	 */
	private Integer favoriteType;

	/**
	 * 收藏的对象id
	 */
	private ObjectId itemId;

	/**
	 * 标题
	 */
	private String title;

	/**
	 * 收藏时间
	 */
	private Long favoriteTime;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getOauthId() {
		return oauthId;
	}

	public void setOauthId(String oauthId) {
		this.oauthId = oauthId;
	}

	public Integer getFavoriteType() {
		return favoriteType;
	}

	public void setFavoriteType(Integer favoriteType) {
		this.favoriteType = favoriteType;
	}

	public ObjectId getItemId() {
		return itemId;
	}

	public void setItemId(ObjectId itemId) {
		this.itemId = itemId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getFavoriteTime() {
		return favoriteTime;
	}

	public void setFavoriteTime(Long favoriteTime) {
		this.favoriteTime = favoriteTime;
	}

	public Favorite() {

	}

	public Favorite(String oauthId, Integer favoriteType, ObjectId itemId, String title) {
		this.id = new ObjectId();
		this.oauthId = oauthId;
		this.favoriteType = favoriteType;
		this.itemId = itemId;
		this.title = title;
		this.favoriteTime = System.currentTimeMillis();
	}
}

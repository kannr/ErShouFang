package com.bjlx.ErShouFang.model.account;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;

import javax.validation.constraints.NotNull;

/**
 * Created by xiaozhi on 2016/7/24.
 * 盐，密码的密文
 */
@Entity
public class Credential {

    /**
     * 域名称，便于查询，以免输入有误
     */
    @Transient
    public final static String fd_oauthId = "oauthId";
    
    @Transient
    public final static String fd_secretKey = "secretKey";

    @Transient
    public final static String fd_key = "secretKey.key";
    
    /**
     * 主键
     */
    @Id
    private ObjectId id;

    /**
     * 用户id
     */
    @NotNull
    @Indexed(options = @IndexOptions(unique = true))
    private String oauthId;

    /**
     * 用户的授权码
     */
    @Embedded
    @NotNull
    private SecretKey secretKey;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }



    public SecretKey getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(SecretKey secretKey) {
        this.secretKey = secretKey;
    }
    public Credential() {
    	
    }

    public Credential(String oauthId, SecretKey secretKey) {
        this.id = new ObjectId();
        this.oauthId = oauthId;
        this.secretKey = secretKey;
    }
}

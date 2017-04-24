package com.bjlx.ErShouFang.utils;

//import java.util.Arrays;
//import java.util.List;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.slf4j.LoggerFactory;

//import com.mongodb.MongoCredential;

/**
 * 单例实现数据库实例工厂
 * 懒加载
 */
public class MorphiaFactory {

	/**
	 * 懒加载MorphiaFactory对象
	 * @author xiaozhi
	 *
	 */
	private static class MorphiaFactoryHolder {
		private static final MorphiaFactory morphiaFactory = new MorphiaFactory();
	}

	/**
	 * 默认构造方法
	 */
	private MorphiaFactory(){}

	/**
	 * 取得数据库
	 * @return 数据库实例
	 */
	public static Datastore getInstance() {
		return MorphiaFactoryHolder.morphiaFactory.getDatastore();
	}


    /**
     * 取得Morphia
     * @return Morphia实例
     */
    private Morphia getMorphia() {
    	final Morphia morphia = new Morphia();    
    	morphia.map( 
    			com.bjlx.ErShouFang.model.account.UserInfo.class, com.bjlx.ErShouFang.model.account.Favorite.class);
        return morphia;
    }

    /**
     * 取得Mongo客户端
     * @return mongo客户端
     */
    private MongoClient getClient() {
    	// 主机地址
    	String host = "192.168.1.128";
//		String host = "127.0.0.1";
    	// 端口
    	int port = 27017;
    	// 服务器地址
    	ServerAddress serverAddress;
    	serverAddress = new ServerAddress(host, port);
    	// 数据库名称
    	String db_name = "QinShihuang";
//    	// 用户名
//    	String username = "xiaoyao";
//    	// 密码
//    	String password = "iloveyou";
    	// 建立凭证
//    	List<MongoCredential> credentials = Arrays.asList();
    	//MongoCredential.createCredential(username, db_name, password.toCharArray()));

    	// 打印日志
    	LoggerFactory.getLogger(MorphiaFactory.class).info(String.format("Connected to MongoDB: dbName=%s", db_name));
    	MongoClientOptions mongoClientOptions = new MongoClientOptions.Builder().connectTimeout(60000).socketTimeout(10000).connectionsPerHost(50).threadsAllowedToBlockForConnectionMultiplier(50).build();

    	// 构建mongo客户端
//    	if(credentials.isEmpty())
    		return new MongoClient(serverAddress, mongoClientOptions);
//    	else
//    		return new MongoClient(serverAddress, credentials, mongoClientOptions);
    }

    /**
     * 取得数据库
     * @return 数据库实例
     */
    private Datastore getDatastore() {
    	String db_name = "QinShihuang";
    	final Datastore datastore = getMorphia().createDatastore(getClient(), db_name);
    	datastore.ensureIndexes();
    	datastore.ensureCaps();
    	return datastore;
    }
}

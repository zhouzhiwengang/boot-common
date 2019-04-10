package com.zzg.mongodb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.zzg.mongodb.config.entity.MongODBConfigEntity;

@Configuration
public class MongODBConfig {
	@Autowired
	private MongODBConfigEntity entity;

	// 获取MongoDbFactory 对象
	@Bean
	public MongoDbFactory getMongDbFactory() {
		MongoClientOptions.Builder builder = MongoClientOptions.builder()
				.socketTimeout(Integer.valueOf(entity.getSocketTimeout().trim()))
				.connectTimeout(Integer.valueOf(entity.getConnectTimeout().trim()))
				.connectionsPerHost(Integer.valueOf(entity.getConnectionsPerHost().trim()));
		MongoClientURI mongoClientURI = new MongoClientURI(entity.getURI(), builder);
		return new SimpleMongoDbFactory(mongoClientURI);
	}
	
	// 获取MongoTemplate 对象
	@Bean
	public MongoTemplate getMongoTemplate(){
		return new MongoTemplate(getMongDbFactory());
	}

}

package com.zzg.mongodb.config.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Configuration
@ConfigurationProperties(prefix = "com.zzg.mongodb") 
@PropertySource("classpath:mongodb.properties")
public class MongODBConfigEntity {
	private String host;
	private String port;
	private String database;
	private String username;
	private String password;
	private String socketTimeout;
	private String connectTimeout;
	private String connectionsPerHost;
	
	// set 和 get 方法
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getDatabase() {
		return database;
	}
	public void setDatabase(String database) {
		this.database = database;
	}
	public String getSocketTimeout() {
		return socketTimeout;
	}
	public void setSocketTimeout(String socketTimeout) {
		this.socketTimeout = socketTimeout;
	}
	public String getConnectTimeout() {
		return connectTimeout;
	}
	public void setConnectTimeout(String connectTimeout) {
		this.connectTimeout = connectTimeout;
	}
	public String getConnectionsPerHost() {
		return connectionsPerHost;
	}
	public void setConnectionsPerHost(String connectionsPerHost) {
		this.connectionsPerHost = connectionsPerHost;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	// 自定义方法
	public String getURI(){
		// 标准个数 =  mongodb://userName:passWord@127.0.0.1:27017/DBname
		StringBuilder builder = new StringBuilder();
		builder.append("mongodb://").append(this.username).append(":").append(this.password).append("@")
			.append(this.host).append(":").append(this.port).append("/").append(this.database);
		return builder.toString().trim();
	}
	
	
	


}

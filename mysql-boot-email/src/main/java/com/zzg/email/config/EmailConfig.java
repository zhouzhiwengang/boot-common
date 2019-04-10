package com.zzg.email.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.zzg.email.config.entity.EmailConfigEntity;
import com.zzg.email.util.EmailUtil;

@Configuration
public class EmailConfig {
	@Autowired
	private EmailConfigEntity entity;
	
	//获取JavaMailSenderImpl 对象
	@Bean
	public JavaMailSenderImpl getJavaMailSender(){
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		sender.setHost(entity.getHost().trim());
		sender.setUsername(entity.getUsername().trim());
		sender.setPassword(entity.getPassword().trim());
		
		Properties properties = new Properties();
		properties.setProperty("mail.smtp.auth", entity.getAuth().trim());
		properties.setProperty("mail.smtp.starttls.enable", entity.getEnable().trim());
		properties.setProperty("mail.smtp.starttls.required", entity.getRequired().trim());
		sender.setJavaMailProperties(properties);
		return sender;
	}
	
	// 实列化 email 工具
	@Bean
	public EmailUtil getEmailUtil(){
		EmailUtil emailUtil = new EmailUtil();
		emailUtil.setSender(getJavaMailSender());
		return emailUtil;
	}
	
	

}

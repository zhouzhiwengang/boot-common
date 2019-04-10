package com.zzg.email.util;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import com.zzg.email.entity.EmailBean;

public class EmailUtil{
	// 日志记录
	private final static Logger logger = LoggerFactory.getLogger(EmailUtil.class);
	
	private JavaMailSenderImpl sender;

	public JavaMailSenderImpl getSender() {
		return sender;
	}

	public void setSender(JavaMailSenderImpl sender) {
		this.sender = sender;
	}
	
	public void send(EmailBean bean){
		try{
			switch(bean.getType()){
			case 1:
				// 简单文本邮件
				SimpleMailMessage text = new SimpleMailMessage();
				text.setFrom(bean.getFrom());
				text.setTo(bean.getTo());
				text.setSubject(bean.getSubject());
				// 简单文本核心代码
				text.setText(bean.getText());
				
				sender.send(text);
				break;
			case 2:
				// HTML邮件
				MimeMessage  html = sender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(html, true);
				helper.setFrom(bean.getFrom());
				helper.setTo(bean.getTo());
				helper.setSubject(bean.getSubject());
				// HTML 核心代码
				helper.setText(bean.getText(),true);
				
				sender.send(html);
				break;
			case 3:
				// 附件邮件
				MimeMessage attach = sender.createMimeMessage();
				MimeMessageHelper attachHelper = new MimeMessageHelper(attach, true);
				attachHelper.setFrom(bean.getFrom());
				attachHelper.setTo(bean.getTo());
				attachHelper.setSubject(bean.getSubject());
				attachHelper.setText(bean.getText());
				// 附件核心代码
				attachHelper.addAttachment(bean.getAttachmentFilename(), bean.getFile());
				
				sender.send(attach);
				break;
			}
		}catch (Exception e){
			logger.error(e.getMessage());
		}
	}
}

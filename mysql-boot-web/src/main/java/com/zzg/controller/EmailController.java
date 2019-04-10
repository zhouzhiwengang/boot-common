package com.zzg.controller;

import java.io.File;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.zzg.api.EmailService;
import com.zzg.email.entity.EmailBean;
import com.zzg.email.util.EmailUtil;
import com.zzg.entity.Email;
import com.zzg.freemarker.util.EmailFreeMarkerUtil;

@Controller
@RequestMapping("/email")
public class EmailController {
	// 引入日志
	private final static Logger logger = LoggerFactory.getLogger(MongodbControlller.class);
	// 引入邮件模板 数据库服务
	@Autowired
	private EmailService service;
	
	@Autowired
	private EmailUtil util;
	
	@RequestMapping(value="/sendText", method=RequestMethod.GET)
	@ResponseBody
	public void sendText(){
		// 简单文本邮件发送
		EmailBean text = new EmailBean();
		text.setFrom("zhouzhiwengang@163.com");
		text.setTo("zhouzhigang@digipower.cn");
		text.setSubject("简单文本邮件发送");
		text.setText("简单文本邮件发送");
		text.setType(Integer.valueOf(1));
		util.send(text);
		logger.error("简单文本邮件发送成功");
	}
	
	@RequestMapping(value="/sendHTML", method=RequestMethod.GET)
	@ResponseBody
	public void sendHTML(){
		// 简单文本邮件发送
		EmailBean text = new EmailBean();
		text.setFrom("zhouzhiwengang@163.com");
		text.setTo("zhouzhigang@digipower.cn");
		text.setSubject("HTML邮件发送");
		StringBuffer sb = new StringBuffer();
        sb.append("<h1>HTML邮件发送</h1>")
                .append("<p style='color:#F00'>红色字</p>")
                .append("<p style='text-align:right'>右对齐</p>");
        
		text.setText(sb.toString());
		text.setType(Integer.valueOf(2));
		util.send(text);
		logger.error("HTML邮件发送成功");
	}
	
	@RequestMapping(value="/sendAttach", method=RequestMethod.GET)
	@ResponseBody
	public void sendAttach(){
		// 简单文本邮件发送
		EmailBean text = new EmailBean();
		text.setFrom("zhouzhiwengang@163.com");
		text.setTo("zhouzhigang@digipower.cn");
		text.setSubject("邮件附件发送");
		text.setText("邮件附件发送");
		FileSystemResource file = new FileSystemResource(new File("C:\\image\\1.png"));
		text.setFile(file);
		text.setAttachmentFilename("1.png");
		text.setType(Integer.valueOf(3));
		util.send(text);
		logger.error("HTML邮件发送成功");
	}
	
	
	@RequestMapping(value="/sendTemplate", method=RequestMethod.GET)
	@ResponseBody
	public void sendTemplate(){
		List<Email> list = service.getEmails();
		if(list != null && list.size() > 0){
			list.stream().forEach(item ->{
				// 简单文本邮件发送
				EmailBean text = new EmailBean();
				text.setFrom("zhouzhiwengang@163.com");
				text.setTo("zhouzhigang@digipower.cn");
				text.setSubject("邮件模板发送");
				
				Map<String,Object> parameter = new HashMap<String,Object>();
				parameter.put("name", "周志刚");
				parameter.put("money", 1000089.155);
				parameter.put("point", 16568);
				
				try {
					// 声明模板和模板参数写入(核心代码)
					String context = EmailFreeMarkerUtil.transform(parameter, item.getContext());
					if(StringUtils.isEmpty(context)){
						// 判断freemarker 模板解析是否正确解析
						logger.error("freemarker 模板解析错误");
						return;
					}
					text.setText(context);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				text.setType(Integer.valueOf(1));
				util.send(text);
				logger.error("HTML邮件发送成功");
			});
		}
		
	}
	
	
	
}

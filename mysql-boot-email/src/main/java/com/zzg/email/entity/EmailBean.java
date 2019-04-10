package com.zzg.email.entity;

import org.springframework.core.io.FileSystemResource;

/**
 * 
 * @ClassName:  EmailBean   
 * @Description: 邮件实体对象 
 * @author: *** -zzg
 * @date:   2019年4月10日 上午9:11:33   
 *     
 * @Copyright: 2019 www.digipower.cn 
 * 注意：本内容仅限于***开发有限公司内部使用，禁止用于其他的商业目的
 */
public class EmailBean {
	// 发送人
	private String from;
	// 接收人
	private String to;
	// 主题
	private String subject;
	// 内容
	private String text;
	// 附件
	private FileSystemResource file;
	// 附件名称
	private String attachmentFilename;
	// 邮件类型
	private Integer type;
	
	// set 和  get 方法
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public FileSystemResource getFile() {
		return file;
	}
	public void setFile(FileSystemResource file) {
		this.file = file;
	}
	public String getAttachmentFilename() {
		return attachmentFilename;
	}
	public void setAttachmentFilename(String attachmentFilename) {
		this.attachmentFilename = attachmentFilename;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
}

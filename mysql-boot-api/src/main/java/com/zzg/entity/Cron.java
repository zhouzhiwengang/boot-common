package com.zzg.entity;

import java.io.Serializable;

public class Cron implements Serializable {

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String cron_name;
	
	private String cron_time;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCron_name() {
		return cron_name;
	}

	public void setCron_name(String cron_name) {
		this.cron_name = cron_name;
	}

	public String getCron_time() {
		return cron_time;
	}

	public void setCron_time(String cron_time) {
		this.cron_time = cron_time;
	}
	
	

}

package com.zzg.mybatis.entity;

import java.io.Serializable;

/**
 * 
 * @ClassName:  AbstractEntity   
 * @Description: 实体对象基类   
 * @author: 世纪伟图 -zzg
 * @date:   2019年4月18日 上午10:02:53   
 *     
 * @Copyright: 2019 www.digipower.cn 
 * 注意：本内容仅限于深圳市世纪伟图科技开发有限公司内部使用，禁止用于其他的商业目的
 */
public class AbstractEntity implements Serializable {

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1L;
	
	 /** 序列主键. */
    private Long sid;

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}
    
    

}

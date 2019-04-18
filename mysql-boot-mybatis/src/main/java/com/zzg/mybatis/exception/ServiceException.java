package com.zzg.mybatis.exception;

/**
 * 
 * @ClassName:  ServiceException   
 * @Description: MyBatis 单表查询 服务异常定义   
 * @author: 世纪伟图 -zzg
 * @date:   2019年4月18日 上午10:24:21   
 *     
 * @Copyright: 2019 www.digipower.cn 
 * 注意：本内容仅限于深圳市世纪伟图科技开发有限公司内部使用，禁止用于其他的商业目的
 */
public class ServiceException extends BaseRuntimeException {
	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1L;

	public ServiceException(String code,String[] params) {
		this.setCode(code);
		this.setParams(params);
	}
	
	public ServiceException(String code,String[] params,Exception e) {
		super(code,e);
		this.setCode(code);
		this.setParams(params);
	}
}

package com.zzg.mybatis.exception;

/**
 * 
 * @ClassName:  DaoException   
 * @Description: dao层 异常定义  
 * @author: 世纪伟图 -zzg
 * @date:   2019年4月18日 上午11:59:51   
 *     
 * @Copyright: 2019 www.digipower.cn 
 * 注意：本内容仅限于深圳市世纪伟图科技开发有限公司内部使用，禁止用于其他的商业目的
 */
public class DaoException extends BaseRuntimeException {

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1L;
	
	public DaoException(String code,String[] params,Exception e) {
		super(code,e);
		this.setCode(code);
		this.setParams(params);
	}

}

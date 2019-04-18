package com.zzg.mybatis.exception;

/**
 * 
 * @ClassName:  GlobalRuntimeException   
 * @Description: MyBatis 单表查询服务  bean 未发现异常   
 * @author: 世纪伟图 -zzg
 * @date:   2019年4月18日 上午10:26:07   
 *     
 * @Copyright: 2019 www.digipower.cn 
 * 注意：本内容仅限于深圳市世纪伟图科技开发有限公司内部使用，禁止用于其他的商业目的
 */
public class GlobalRuntimeException extends BaseRuntimeException {
	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1L;


	/**
	 * Instantiates a new global runtime exception.
	 *
	 * @param message the message
	 * @param e the e
	 */
	public GlobalRuntimeException(String message,Exception e){
		super(message,e);
	}
	
	/**
	 * Instantiates a new global runtime exception.
	 *
	 * @param code the code
	 * @param params the params
	 */
	public GlobalRuntimeException(String code,String[] params){
		super(code,params);
	}
	

	public GlobalRuntimeException(String code,String[] params,Exception e){
		super(code,params,e);
	}
}

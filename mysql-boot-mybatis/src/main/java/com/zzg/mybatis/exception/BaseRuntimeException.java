package com.zzg.mybatis.exception;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @ClassName:  BaseRuntimeException   
 * @Description: mybatis 单表查询   基础异常封装
 * @author: 世纪伟图 -zzg
 * @date:   2019年4月18日 上午10:21:16   
 *     
 * @Copyright: 2019 www.digipower.cn 
 * 注意：本内容仅限于深圳市世纪伟图科技开发有限公司内部使用，禁止用于其他的商业目的
 */
public class BaseRuntimeException extends RuntimeException {

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1L;
	
	 /** The code. */
    private String code;

    /** The params. */
    private String[] params;

    /**
     * Gets the code.
     * 
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the code.
     * 
     * @param code
     *            the new code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets the params.
     * 
     * @return the params
     */
    public String[] getParams() {
        return params;
    }

    /**
     * Sets the params.
     * 
     * @param params
     *            the new params
     */
    public void setParams(String[] params) {
        this.params = params;
    }

    /**
     * Instantiates a new base runtime exception.
     */
    public BaseRuntimeException() {
        super();
    }

    /**
     * Instantiates a new base runtime exception.
     * 
     * @param message
     *            the message
     * @param e
     *            the e
     */
    public BaseRuntimeException(String message, Exception e) {
        super(message, e);
    }

    /**
     * Instantiates a new base runtime exception.
     * 
     * @param message
     *            the message
     */
    public BaseRuntimeException(String message) {
        super(message);
    }

    /**
     * Instantiates a new base runtime exception.
     * 
     * @param e
     *            the e
     */
    public BaseRuntimeException(Exception e) {
        super(e);
    }

    /**
     * Instantiates a new base runtime exception.
     * 
     * @param code
     *            the code
     * @param params
     *            the params
     */
    public BaseRuntimeException(String code, String[] params) {
        super(code);
        this.setCode(code);
        this.setParams(params);
    }

    /**
     * Instantiates a new base runtime exception.
     * 
     * @param code
     *            the code
     * @param params
     *            the params
     * @param e
     *            the e
     */
    public BaseRuntimeException(String code, String[] params, Exception e) {
        super(e);
        this.setCode(code);
        this.setParams(params);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Throwable#getMessage()
     */
    @Override
    public String getMessage() {
        if (code == null || code.length() == 0) {
            return super.getMessage();
        }
//        String i18n = AppServiceHelper.getMessage(code, params);
//        if (StringUtils.isNotEmpty(i18n)) {
//            return i18n;
//        }
        String paramsStr = "NA";
        if (params != null) {
            paramsStr = StringUtils.join(params, ",");
        }
        String codeMessage = "code:" + code + ";parameters:" + paramsStr;
        return codeMessage;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Throwable#toString()
     */
    @Override
    public String toString() {
        String s = getClass().getName();
        String message = this.getMessage();
        return (message != null) ? (s + ": " + message) : s;
    }

}

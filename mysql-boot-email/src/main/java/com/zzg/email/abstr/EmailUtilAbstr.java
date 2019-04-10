package com.zzg.email.abstr;

import com.zzg.email.util.EmailUtil;

/**
 * 
 * @ClassName:  EmailUtilAbstr   
 * @Description: 邮件发送功能拓展   
 * @author: **** -zzg
 * @date:   2019年4月10日 上午10:12:31   
 *     
 * @Copyright: 2019 www.digipower.cn 
 * 注意：本内容仅限于****开发有限公司内部使用，禁止用于其他的商业目的
 */
public abstract class EmailUtilAbstr {
	
	private EmailUtil util;
	
	public EmailUtil getUtil() {
		return util;
	}
	
	public void setUtil(EmailUtil util) {
		this.util = util;
	}

	public EmailUtilAbstr(EmailUtil util){
		this.util = util;
	}
	public abstract void customSend();
}

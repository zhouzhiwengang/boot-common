package com.zzg.freemarker.util;

import java.io.StringWriter;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 
 * @ClassName:  FreeMarkerUtil   
 * @Description: freemarker 工具类   
 * @author: **** -zzg
 * @date:   2019年4月10日 下午2:17:56   
 *     
 * @Copyright: 2019 www.digipower.cn 
 * 注意：本内容仅限于****开发有限公司内部使用，禁止用于其他的商业目的
 */
public class EmailFreeMarkerUtil {
	
	// 引入日志
	private final static Logger logger = LoggerFactory.getLogger(EmailFreeMarkerUtil.class);
	
	public static String transform(Map map, String context){
		try {
			//定义StringTemplateLoader
			StringTemplateLoader loader = new StringTemplateLoader();
			loader.putTemplate("template", context);
			
			//定义Configuration
			Configuration configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
			configuration.setTemplateLoader(loader);
			
			//定义Template
			Template tpl = configuration.getTemplate("template");
			
			StringWriter writer = new StringWriter();
			tpl.process(map, writer);
			return writer.toString();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

}

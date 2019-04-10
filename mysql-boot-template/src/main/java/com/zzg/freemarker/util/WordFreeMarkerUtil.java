package com.zzg.freemarker.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 
 * @ClassName: WordFreeMarkerUtil
 * @Description: Word 模板工具类
 * @author: **** -zzg
 * @date: 2019年4月10日 下午4:11:48
 * 
 * @Copyright: 2019 www.digipower.cn 注意：本内容仅限于****开发有限公司内部使用，禁止用于其他的商业目的
 */
public class WordFreeMarkerUtil {
	// 日志记录
	private final static Logger logger = LoggerFactory.getLogger(WordFreeMarkerUtil.class);
	
	// ftl 文件路径
	private String ftlFilePath;
	// ftl 文件名称
	private String ftlFileName;

	private Configuration configuration = null;
	private Template template = null;

	// get 和 set 方法
	public String getFtlFileName() {
		return ftlFileName;
	}

	public String getFtlFilePath() {
		return ftlFilePath;
	}

	public void setFtlFilePath(String ftlFilePath) {
		this.ftlFilePath = ftlFilePath;
	}

	public void setFtlFileName(String ftlFileName) {
		this.ftlFileName = ftlFileName;
	}

	public WordFreeMarkerUtil(String ftlFilePath,String ftlFileName) {
			this.ftlFilePath = ftlFilePath;
			this.ftlFileName = ftlFileName;
			// freemarker 模板配置对象
			configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
			// 设置编码格式: utf-8
			configuration.setDefaultEncoding("utf-8");
			// 设置ftl 文件目录
			File baseDir = new File(this.ftlFilePath);
			// 判断ftl 文件目录是否存在
			try {
				if(baseDir.exists() && baseDir.isDirectory()){
					FileTemplateLoader loader = new FileTemplateLoader(baseDir);
					configuration.setTemplateLoader(loader);
					// 设置ftl 文件
					template = configuration.getTemplate(this.ftlFileName);
				} else {
					logger.error("ftl 文件目录不存在");
					return;
				}
			} catch (IOException e) {
				logger.error(e.getMessage());
				return;
			}
		}
	
	public void createTemplate(Map data, String docFileName){
		File file = new File(docFileName);
		if(file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.error("word 文件创建失败");
				return;
			}
		}
		try {
			Writer writer = new OutputStreamWriter(new FileOutputStream(file),"utf-8");
			template.process(data, writer);
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			return;
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.getMessage());
			return;
		}
		
	}


}

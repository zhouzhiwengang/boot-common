package com.zzg.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzg.encrypt.util.Base64Util;
import com.zzg.freemarker.util.WordFreeMarkerUtil;


@Controller
@RequestMapping("/template")
public class FreeMarkerController {
	
	@RequestMapping(value = "/index", method = { RequestMethod.GET })
	public String index(ModelMap map) {
		map.put("name", "SpringBoot 集成模板引擎freemarker");
		return "index";
	}
	
	@RequestMapping(value = "/word", method = { RequestMethod.GET })
	@ResponseBody
	public void word() {
		String imageContent = Base64Util.getEncodeValue("C:\\image\\1.png");
		// 数据组装
		Map<String,Object> map = new HashMap<String,Object>();
		StringBuilder builder = new StringBuilder();
		builder.append("data:image/png;base64,");
		builder.append(imageContent);
		map.put("imageurl", builder.toString());
		map.put("name", "zhouzhiwengang");
		map.put("time", "2019-04-03");
		map.put("factory", "深圳富士康");
		map.put("product", "苹果电脑");
		map.put("price", "8999");
		map.put("num", "十万台");
		
		// 构建模板引擎实体对象
		WordFreeMarkerUtil util = new WordFreeMarkerUtil("C:\\image\\ftl","template.ftl");
		String docFileName = "C:\\image\\ftl\\测试模板.doc";
		util.createTemplate(map, docFileName);
	}

}

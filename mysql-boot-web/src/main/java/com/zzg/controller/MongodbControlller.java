package com.zzg.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.zzg.api.BookService;
import com.zzg.entity.Book;
import com.zzg.mongodb.repository.BookRepository;

@Controller
@RequestMapping("/mongodb")
public class MongodbControlller {
	
	// 引入日志
	private final static Logger logger = LoggerFactory.getLogger(MongodbControlller.class);
	
	@Autowired
	private BookService service;
	@Autowired
	private BookRepository repository;

	
	@RequestMapping(value = "/inser", method = { RequestMethod.GET })
	@ResponseBody
	public void getBooks() {
		List<Book> list = service.getBooks();
		if(list != null && list.size() > 0){
			list.stream().forEach(item ->{
				repository.save(item);
				logger.error("mongodb 数据插入成功");
			});
		}
		
		
	}

}

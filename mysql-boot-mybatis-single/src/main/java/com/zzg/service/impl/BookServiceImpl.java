package com.zzg.service.impl;

import org.springframework.stereotype.Service;

import com.zzg.entity.Book;
import com.zzg.mybatis.service.impl.BaseCRUDService;
import com.zzg.service.BookService;

@Service
public class BookServiceImpl extends BaseCRUDService<Book> implements BookService {

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = 1L;

}

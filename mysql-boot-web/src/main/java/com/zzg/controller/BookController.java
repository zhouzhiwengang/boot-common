package com.zzg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zzg.api.BookService;
import com.zzg.entity.Book;

@Controller
@RequestMapping
public class BookController {
	@Autowired
	private BookService service;
	
	@RequestMapping(value = "/books", method = { RequestMethod.GET })
	@ResponseBody
	public List<Book> getBooks() {
		List<Book> result = service.getBooks();
		return result;
	}
}

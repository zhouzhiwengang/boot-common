package com.zzg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzg.api.BookService;
import com.zzg.entity.Book;
import com.zzg.mapper.BookMapper;

@Service
public class BookServiceImpl implements BookService{
	@Autowired
	private BookMapper mapper;

	@Override
	public List<Book> getBooks() {
		// TODO Auto-generated method stub
		return mapper.getBooks();
	}

}

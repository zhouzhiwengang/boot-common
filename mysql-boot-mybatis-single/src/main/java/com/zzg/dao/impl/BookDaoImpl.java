package com.zzg.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zzg.dao.BookDao;
import com.zzg.entity.Book;
import com.zzg.mybatis.dao.impl.IBatisGenericDaoImpl;

@Component(value="bookDao")
public class BookDaoImpl extends IBatisGenericDaoImpl<Book> implements BookDao {
	@Autowired
	@Override
	public void setSession(SqlSessionTemplate session) {
		// TODO Auto-generated method stub
		this.session = session;
	}
}

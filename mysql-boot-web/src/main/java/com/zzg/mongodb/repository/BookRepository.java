package com.zzg.mongodb.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import com.zzg.entity.Book;
import com.zzg.mongodb.common.BaseMongoDaoImpl;

@Repository
public class BookRepository extends BaseMongoDaoImpl<Book>{
	@Autowired
	@Override
	protected void setMongoTemplate(MongoTemplate template) {
		// TODO Auto-generated method stub
		this.template = template;
	}
}

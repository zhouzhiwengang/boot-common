package com.zzg.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Select;
import com.zzg.entity.Book;

public interface BookMapper {
	@Select("select * from book where 1=1")
	List<Book> getBooks();
}

package com.zzg.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import com.zzg.entity.Email;

public interface EmailMapper {
	@Select("select * from email where 1=1")
	List<Email> getEmails();
}

package com.zzg.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.zzg.entity.Cron;

public interface CronMapper {
	@Select("select * from cron where 1=1")
	List<Cron> getCrons();
}

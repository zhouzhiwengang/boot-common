package com.zzg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzg.api.CronService;
import com.zzg.entity.Cron;
import com.zzg.mapper.CronMapper;

@Service
public class CronServiceImpl implements CronService {

	@Autowired
	private CronMapper mapper;
	@Override
	public List<Cron> getCrons() {
		// TODO Auto-generated method stub
		return mapper.getCrons();
	}

}

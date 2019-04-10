package com.zzg.cron.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.zzg.cron.abstr.CronAbstract;

@Component("cronObject")
public class CronObject extends CronAbstract {
	// 引入日志
	private final static Logger logger = LoggerFactory.getLogger(CronObject.class);

	@Override
	public void execute() {
		//记录日志信息
		logger.error("CronObject 定时任务执行");
		System.out.println("CronObject 对象执行输出任务");
	}

}

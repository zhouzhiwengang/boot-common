package com.zzg.cron.component;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.zzg.cron.abstr.CronAbstract;

@Component("cronThreadPoolObject")
public class CronThreadPoolObject extends CronAbstract {

	@Override
	@Async(value = "threadPoolTaskExecutor")
	public void execute() {
		// TODO Auto-generated method stub
		System.out.println("CronThreadPoolObject 对象执行输出任务:" + Thread.currentThread().getName());
	}

}

package com.zzg.cron.config;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import com.zzg.api.CronService;
import com.zzg.application.SpringBeanUtil;
import com.zzg.entity.Cron;

@Configuration
@EnableScheduling
public class CronScheduleConfig implements SchedulingConfigurer{
	@Autowired
	private CronService service;
	@Autowired
	private SpringBeanUtil container;
	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		// TODO Auto-generated method stub
		List<Cron> list = service.getCrons();
		if(list != null && list.size() > 0){
			list.stream().forEach(item ->{
				// 获取task 对象
				String cronName = item.getCron_name();
				Runnable task = (Runnable) container.getBean(cronName);
				Trigger trigger = new Trigger() {
					@Override
					public Date nextExecutionTime(TriggerContext triggerContext) {
						// TODO Auto-generated method stub
						CronTrigger trigger = new CronTrigger(item.getCron_time());
						Date nextExec = trigger.nextExecutionTime(triggerContext);
						return nextExec;
					}
				};
				// 添加cron 任务
				taskRegistrar.addTriggerTask(task, trigger);
				
			});
		}
		
	}

}

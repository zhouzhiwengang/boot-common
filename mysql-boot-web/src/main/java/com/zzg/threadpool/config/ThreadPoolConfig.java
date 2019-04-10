package com.zzg.threadpool.config;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
/**
 * 
 * @ClassName:  ThreadPoolConfig   
 * @Description: 线程池配置类   
 * @author: **** -zzg
 * @date:   2019年4月9日 上午10:31:49   
 *     
 * @Copyright: 2019 www.digipower.cn 
 * 注意：本内容仅限于*****科技开发有限公司内部使用，禁止用于其他的商业目的
 */
@Configuration
public class ThreadPoolConfig {
	/**
	 * 
	 * @Title: threadPoolTaskExecutor @Description: TODO
	 * 节点创建默认工作线程池。 @param: @return @return: Executor @throws
	 */
	@Bean(name = "threadPoolTaskExecutor")
	public Executor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(5);
		executor.setMaxPoolSize(10);
		executor.setQueueCapacity(200);
		executor.setKeepAliveSeconds(60);
		executor.setThreadNamePrefix("threadpool-");
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		return executor;
	}

}

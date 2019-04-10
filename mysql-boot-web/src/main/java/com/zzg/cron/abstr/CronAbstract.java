package com.zzg.cron.abstr;

/**
 * 
 * @ClassName:  CronAbstract   
 * @Description: 定时任务抽象类
 * @author: 世纪伟图 -zzg
 * @date:   2019年4月9日 上午9:39:47   
 *     
 * @Copyright: 2019 www.digipower.cn 
 * 注意：本内容仅限于深圳市世纪伟图科技开发有限公司内部使用，禁止用于其他的商业目的
 */
public abstract class CronAbstract implements Runnable {
	public abstract void execute();

	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.execute();
	}
	
	
}

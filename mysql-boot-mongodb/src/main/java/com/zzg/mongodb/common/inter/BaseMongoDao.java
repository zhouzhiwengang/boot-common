package com.zzg.mongodb.common.inter;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

/**
 * 
 * @ClassName:  BaseMongoDao   
 * @Description: 基础公共接口定义  
 * @author: ** -zzg
 * @date:   2019年4月9日 下午4:45:38   
 *   
 * @param <T>  
 * @Copyright: 2019 www.digipower.cn 
 * 注意：本内容仅限于****技开发有限公司内部使用，禁止用于其他的商业目的
 */
public interface BaseMongoDao<T> {
	
	 public List<T> find(Query query);
	 
	 public T findOne(Query query);
	 
	 public void update(Query query, Update update);
	 
	 public T save(T entity);
	 
	 public T findById(String id);
	 
	 public T findById(String id, String collectionName);
	 
	 public long count(Query query);
	 
	 public void remove(Query query);
	 
	 

}

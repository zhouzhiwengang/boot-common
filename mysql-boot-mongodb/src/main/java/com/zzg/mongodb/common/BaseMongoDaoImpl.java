package com.zzg.mongodb.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import com.zzg.mongodb.common.inter.BaseMongoDao;
import com.zzg.mongodb.reflect.ReflectionUtils;

/**
 * 
 * @ClassName:  BaseMongoDaoImpl   
 * @Description: mongodb 抽象类组件  
 * @author: *** -zzg
 * @date:   2019年4月9日 下午4:51:59   
 *   
 * @param <T>  
 * @Copyright: 2019 www.digipower.cn 
 * 注意：本内容仅限于****科技开发有限公司内部使用，禁止用于其他的商业目的
 */

@Component
public abstract class BaseMongoDaoImpl<T> implements BaseMongoDao<T> {
//	@Autowired
//	private MongoTemplate template;
	
	/**
     * spring mongodb　集成操作类
     */
    protected MongoTemplate template;
    
    /**
     * 注入mongodbTemplate
     *
     * @param mongoTemplate
     */
    protected abstract void setMongoTemplate(MongoTemplate template);
    

	@Override
	public List<T> find(Query query) {
		// TODO Auto-generated method stub
		return template.find(query, this.getEntityClass());
	}

	@Override
	public T findOne(Query query) {
		// TODO Auto-generated method stub
		return template.findOne(query, this.getEntityClass());
	}

	@Override
	public void update(Query query, Update update) {
		// TODO Auto-generated method stub
		template.findAndModify(query, update, this.getEntityClass());
	}

	@Override
	public T save(T entity) {
		// TODO Auto-generated method stub
		template.insert(entity);
		return entity;
	}

	@Override
	public T findById(String id) {
		// TODO Auto-generated method stub
		return template.findById(id, this.getEntityClass());
	}

	@Override
	public T findById(String id, String collectionName) {
		// TODO Auto-generated method stub
		return template.findById(id, this.getEntityClass(), collectionName);
	}

	@Override
	public long count(Query query) {
		// TODO Auto-generated method stub
		return template.count(query, this.getEntityClass());
	}

	@Override
	public void remove(Query query) {
		// TODO Auto-generated method stub
		template.remove(query, this.getEntityClass());
	}
	
	/**
     * 获取需要操作的实体类class
     *
     * @return
     */
    private Class<T> getEntityClass() {
        return ReflectionUtils.getSuperClassGenricType(getClass());
    }
}

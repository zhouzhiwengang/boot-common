package com.zzg.mybatis.service.impl;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zzg.mybatis.application.AppServiceHelper;
import com.zzg.mybatis.constant.PageConstant;
import com.zzg.mybatis.dao.BaseDao;
import com.zzg.mybatis.entity.AbstractEntity;
import com.zzg.mybatis.page.Page;
import com.zzg.mybatis.service.BaseService;

/**
 * 
 * @ClassName: BaseCRUDService
 * @Description:mybatis 单表查询 BaseService 接口实现
 * @author: 世纪伟图 -zzg
 * @date: 2019年4月18日 上午10:04:58
 * 
 * @param <T>
 * @Copyright: 2019 www.digipower.cn 注意：本内容仅限于深圳市世纪伟图科技开发有限公司内部使用，禁止用于其他的商业目的
 */

public class BaseCRUDService<T extends AbstractEntity> implements BaseService<T> {
	// 引入日志
	private final static Logger logger = LoggerFactory.getLogger(BaseCRUDService.class);
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

	// 缺少日志记录 待补充

	// model's Class
	protected Class<T> entityClass;

	// model's ClassName
	protected String entityClassName;

	// 基础公共dao 层
	private BaseDao<T> baseDao;

	// 构造函数
	@SuppressWarnings("unchecked")
	public BaseCRUDService() {
		try {
			Object genericClz = getClass().getGenericSuperclass();
			if (genericClz instanceof ParameterizedType) {
				entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
						.getActualTypeArguments()[0];
				entityClassName = entityClass.getSimpleName();
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	// 获取dao
	@SuppressWarnings("unchecked")
	protected BaseDao<T> getBaseDao() {
		if (baseDao == null && this.entityClass != null) {
			String pname = entityClass.getPackage().getName();
			pname = pname.substring(0, pname.lastIndexOf("."));
			// 实体dao 层的命名规则：包名.dao.实体类名Dao
			baseDao = (BaseDao<T>) AppServiceHelper.findBean(pname + ".dao." + entityClassName + "Dao");
			// 待补充日志
			if (logger.isDebugEnabled()) {
				logger.debug("init baseDao=" + baseDao);
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("baseDao=" + baseDao);
		}
		return baseDao;
	}

	@Override
	public Page<T> findByPage(Map<String, Object> params, Integer pageNo, Integer limit) {
		Date start, end;
		start = new Date();
		Page<T> page = getBaseDao().findByPage(params, pageNo, limit);
		end = new Date();
		if (logger.isDebugEnabled()) {
			logger.debug("findByPage from service cost time=" + (end.getTime() - start.getTime()));
		}
		return page;
	}

	@Override
	public Page<T> findByPage(Map<String, Object> params) {
		// TODO Auto-generated method stub
		Integer pageNo = (Integer) params.get(PageConstant.PARAMS_KEY_PAGE_NO);
        Integer limit = (Integer) params.get(PageConstant.PARAMS_KEY_PAGE_LIMIT);
        if (pageNo == null) {
            throw new IllegalArgumentException("pageNo");
        }
        if (limit == null) {
            throw new IllegalArgumentException("limit");
        }
        return findByPage(params, pageNo, limit);
	}

	@Override
	public Long insert(T entity) {
		// TODO Auto-generated method stub
		Long sid = getBaseDao().insert(entity);
		return sid;
	}

	@Override
	public int insertAll(Collection<T> collection) {
		// TODO Auto-generated method stub
		int insertCount = getBaseDao().insertAll(collection);
		return insertCount;
	}

	@Override
	public int update(T entity) {
		// TODO Auto-generated method stub
    	int updateCount = getBaseDao().update(entity);
		return updateCount;
	}

	@Override
	public int updateAll(Collection<T> collection) {
		// TODO Auto-generated method stub
		int count = getBaseDao().updateAll(collection);
		return count;
	}

	@Override
	public List<T> findAll() {
		// TODO Auto-generated method stub
		return getBaseDao().findAll();
	}

	@Override
	public int delete(T entity) {
		// TODO Auto-generated method stub
		int count = getBaseDao().delete(entity);
		return count;
	}

	@Override
	public int deleteAll(Collection<T> collection) {
		// TODO Auto-generated method stub
		int count =  getBaseDao().deleteAll(collection);
		return count;
	}

	@Override
	public T findByPrimaryKey(Long sid) {
		// TODO Auto-generated method stub
		return getBaseDao().findByPrimaryKey(sid);
	}

	@Override
	public List<T> findByPrimaryKeys(Long[] sids) {
		// TODO Auto-generated method stub
		return getBaseDao().findByPrimaryKeys(sids);
	}

	@Override
	public T findUniqueByParams(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return getBaseDao().findUniqueByParams(params);
	}

	@Override
	public List<T> findByParams(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return getBaseDao().findByParams(params);
	}

	@Override
	public Object findObjectBySqlID(String sqlID, Map<String, Object> params) {
		// TODO Auto-generated method stub
		return getBaseDao().findObjectBySqlID(sqlID, params);
	}

	@Override
	public List findListBySqlID(String sqlID, Map<String, Object> params) {
		// TODO Auto-generated method stub
		return getBaseDao().findListBySqlID(sqlID, params);
	}

	@Override
	public int executeDelete(String sqlID, Object params) {
		// TODO Auto-generated method stub
		return getBaseDao().executeDelete(sqlID, params);
	}

	@Override
	public int executeUpdate(String sqlID, Object params) {
		// TODO Auto-generated method stub
		return getBaseDao().executeUpdate(sqlID, params);
	}

	@Override
	public void insertHistory(Map<String, Object> params) {
		// TODO Auto-generated method stub
		getBaseDao().insertHistory(params);
	}

	@Override
	public Page<T> findPageBySqlID(String sqlID, Map<String, Object> params, int pageNo, int limit) {
		// TODO Auto-generated method stub
		 if (sqlID == null) {
	            throw new IllegalArgumentException("sqlID");
	        }
	        Date start, end;
	        start = new Date();
	        Page<T> page = getBaseDao().findPageBySqlID(sqlID, params, pageNo, limit);
	        end = new Date();
	        if (logger.isDebugEnabled()) {
	            logger.debug("findPageBySqlID from service cost time="
	                    + (end.getTime() - start.getTime()));
	        }
	        return page;
	}

}

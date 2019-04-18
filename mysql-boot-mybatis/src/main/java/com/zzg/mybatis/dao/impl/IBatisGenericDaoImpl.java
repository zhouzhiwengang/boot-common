package com.zzg.mybatis.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.zzg.mybatis.application.AppServiceHelper;
import com.zzg.mybatis.dao.BaseDao;
import com.zzg.mybatis.entity.AbstractEntity;
import com.zzg.mybatis.exception.DaoException;
import com.zzg.mybatis.page.Page;

@Component
public abstract class IBatisGenericDaoImpl<T extends AbstractEntity> implements BaseDao<T> {
	// 引入日志
	private final static Logger logger = LoggerFactory.getLogger(IBatisGenericDaoImpl.class);

	// 查询
	public static final String PRE_FIND = "find";
	// 插入
	public static final String PRE_INSERT = "insert";
	// 更新
	public static final String PRE_UPDATE = "update";
	// 删除
	public static final String PRE_DELETE = "delete";
	// 删除参数
	public static final String PRE_DELETE_BY_PARAMS = "deleteByParams";
	// 插入历史记录
	public static final String PRE_INSERT_HISTORY = "insertHistory";

	// 全局常量值
	public static final Integer PAGE_TOTAL_DEFAULT = 0;

	// model's Class
	protected Class<T> entityClass;

	// model's ClassName
	protected String entityClassName;
	
	// 数据库连接
	protected SqlSessionTemplate session;
	
	public SqlSessionTemplate getSession() {
		return session;
	}

	public abstract void setSession(SqlSessionTemplate session);

	@SuppressWarnings("unchecked")
	public IBatisGenericDaoImpl() {
		try {
			Object genericClz = getClass().getGenericSuperclass();

			if (genericClz instanceof ParameterizedType) {
				entityClass = (Class<T>) ((ParameterizedType) genericClz).getActualTypeArguments()[0];
				entityClassName = entityClass.getSimpleName();
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	protected String getEntityName() {
		return this.entityClassName;
	}

	@Override
	public Page<T> findByPage(Map<String, Object> params, int pageNo, int limit) {
		Page<T> page = new Page<T>();
		page.setPageNo(pageNo);
		page.setLimit(limit);
		String findSqlMapId = getEntityName() + "." + PRE_FIND;
		try {

			RowBounds rowBounds = new RowBounds(page.getOffset(), limit);
//			if(this.session == null){
//				SqlSessionTemplate template = (SqlSessionTemplate) AppServiceHelper.findBean("sessionTemplate");
//				setSession(template);				
//			}
			List<T> resultList = this.session.selectList(findSqlMapId, params, rowBounds);
			page.setResultList(resultList);
			if (resultList != null && resultList.size() > 0) {
				page.setTotalRows(resultList.size());
			} else {
				page.setTotalRows(PAGE_TOTAL_DEFAULT);
			}

		} catch (DataAccessException e) {
			throw new DaoException("error.dao.findByPage", new String[] { entityClass.getName() }, e);
		}

		return page;
	}

	@Override
	public Long insert(T entity) {
		// TODO Auto-generated method stub
		Assert.notNull(entity);
		String insertSqlMapId = getEntityName() + "." + PRE_INSERT;
		if (logger.isDebugEnabled()) {
			logger.debug("insertSqlMapId=" + insertSqlMapId);
		}
		Long sid = null;
		try {
//			if(this.session == null){
//				SqlSessionTemplate template = (SqlSessionTemplate) AppServiceHelper.findBean("sessionTemplate");
//				setSession(template);				
//			}
			sid = (long) this.session.insert(insertSqlMapId, entity);
		} catch (DataAccessException e) {
			throw new DaoException("error.dao.insert", new String[] { entityClass.getName() }, e);
		}

		return sid;
	}

	@Override
	public int insertAll(Collection<T> collection) {
		// TODO Auto-generated method stub
		if (collection == null || collection.isEmpty()) {
			return 0;
		}
		for (T entity : collection) {
			this.insert(entity);
		}
		return collection.size();
	}

	@Override
	public int insertAllByBatch(Collection<T> collection) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(T entity) {
		// TODO Auto-generated method stub
		String updateSqlMapId = getEntityName() + "." + PRE_UPDATE;
		if (logger.isDebugEnabled()) {
			logger.debug("updateSqlMapId=" + updateSqlMapId);
		}
		try {
//			if(this.session == null){
//				SqlSessionTemplate template = (SqlSessionTemplate) AppServiceHelper.findBean("sessionTemplate");
//				setSession(template);				
//			}
			int effectCnt = this.session.update(updateSqlMapId, entity);
			return effectCnt;
		} catch (DataAccessException e) {
			logger.error(e.getMessage());
			throw new DaoException("error.dao.update",
					new String[] { entityClass.getName(), entity.getSid().toString() }, e);
		}
	}

	@Override
	public int updateAll(Collection<T> collection) {
		// TODO Auto-generated method stub
		if (collection == null || collection.isEmpty()) {
			return 0;
		}
		for (T entity : collection) {
			this.update(entity);
		}
		return collection.size();
	}

	@Override
	public List<T> findAll() {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		String findSqlMapId = getEntityName() + "." + PRE_FIND;
		if (logger.isDebugEnabled()) {
			logger.debug("findSqlMapId=" + findSqlMapId);
		}
		try {
//			if(this.session == null){
//				SqlSessionTemplate template = (SqlSessionTemplate) AppServiceHelper.findBean("sessionTemplate");
//				setSession(template);				
//			}
			return this.session.selectList(findSqlMapId, params);
		} catch (DataAccessException e) {
			throw new DaoException("error.dao.findByParams", new String[] { entityClass.getName(), params.toString() },
					e);
		}
	}

	@Override
	public int delete(T entity) {
		// TODO Auto-generated method stub
		String deleteSqlMapId = getEntityName() + "." + PRE_DELETE;
		if (logger.isDebugEnabled()) {
			logger.debug("deleteSqlMapId=" + deleteSqlMapId);
		}
		try {
//			if(this.session == null){
//				SqlSessionTemplate template = (SqlSessionTemplate) AppServiceHelper.findBean("sessionTemplate");
//				setSession(template);				
//			}
			return this.session.delete(deleteSqlMapId, entity);
		} catch (DataAccessException e) {
			throw new DaoException("error.dao.delete",
					new String[] { entityClass.getName(), entity.getSid().toString() }, e);
		}
	}

	@Override
	public int deleteAll(Collection<T> collection) {
		// TODO Auto-generated method stub
		if (collection == null || collection.isEmpty()) {
			return 0;
		}
		for (T entity : collection) {
			this.delete(entity);
		}
		return collection.size();
	}

	@Override
	public int deleteByParams(Map<String, Object> params) {
		// TODO Auto-generated method stub
		if (!checkParams(params)) {
			throw new DaoException("error.dao.invalid.MapParams",
					new String[] { entityClass.getName(), params.toString() }, null);
		}

		String deleteSqlMapId = getEntityName() + "." + PRE_DELETE_BY_PARAMS;
		if (logger.isDebugEnabled()) {
			logger.debug("deleteSqlMapId=" + deleteSqlMapId);
		}
		try {
//			if(this.session == null){
//				SqlSessionTemplate template = (SqlSessionTemplate) AppServiceHelper.findBean("sessionTemplate");
//				setSession(template);				
//			}
			return this.session.delete(deleteSqlMapId, params);
		} catch (DataAccessException e) {
			throw new DaoException("error.dao.delete", new String[] { entityClass.getName(), params.toString() }, e);
		}
	}

	@Override
	public T findByPrimaryKey(Long sid) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sid", sid);
		return findUniqueByParams(params);

	}

	@Override
	public List<T> findByPrimaryKeys(Long[] sids) {
		// TODO Auto-generated method stub
		 Map<String, Object> params = new HashMap<String, Object>();
	        if (sids == null || sids.length == 0) {
	            params.put("sids", new Long(-1));
	        } else {
	            StringBuffer sb = new StringBuffer();
	            for (Long sid : sids) {
	                sb.append("," + sid);
	            }
	            params.put("sids", sb.deleteCharAt(0).toString());
	        }
	        return findByParams(params);
	}

	@Override
	public T findUniqueByParams(Map<String, Object> params) {
		// TODO Auto-generated method stub
		if (!checkParams(params)) {
            throw new DaoException("error.dao.invalid.MapParams", new String[] {
                    entityClass.getName(), params.toString() }, null);
        }
        String findAllSqlMapId = getEntityName() + "." + PRE_FIND;
        if (logger.isDebugEnabled()) {
            logger.debug("findAllSqlMapId=" + findAllSqlMapId);
        }
        try {
//        	if(this.session == null){
//				SqlSessionTemplate template = (SqlSessionTemplate) AppServiceHelper.findBean("sessionTemplate");
//				setSession(template);				
//			}
            List<T> list = this.session.selectList(findAllSqlMapId, params);
            if (list == null || list.size() == 0) {
                return null;
            } else if (list.size() == 1) {
                return list.get(0);
            } else {
                throw new DaoException("error.dao.findUniqueByParams.notUnique", new String[] {
                        entityClass.getName(), params.toString() }, null);
            }
        } catch (DataAccessException e) {
            throw new DaoException("error.dao.findUniqueByParams", new String[] {
                    entityClass.getName(), params.toString() }, e);
        }
	}

	@Override
	public List<T> findByParams(Map<String, Object> params) {
		// TODO Auto-generated method stub
		 String findSqlMapId = getEntityName() + "." + PRE_FIND;
	        if (logger.isDebugEnabled()) {
	            logger.debug("findSqlMapId=" + findSqlMapId);
	        }
	        try {
//	        	if(this.session == null){
//					SqlSessionTemplate template = (SqlSessionTemplate) AppServiceHelper.findBean("sessionTemplate");
//					setSession(template);				
//				}
	            return this.session.selectList(findSqlMapId, params);
	        } catch (DataAccessException e) {
	            throw new DaoException("error.dao.findByParams", new String[] { entityClass.getName(),
	                    params.toString() }, e);
	        }
	}

	@Override
	public Object findObjectBySqlID(String sqlID, Map<String, Object> params) {
		// TODO Auto-generated method stub
		try {
//			if(this.session == null){
//				SqlSessionTemplate template = (SqlSessionTemplate) AppServiceHelper.findBean("sessionTemplate");
//				setSession(template);				
//			}
            return this.session.selectOne(sqlID, params);
        } catch (DataAccessException e) {
            throw new DaoException("error.dao.findObjectBySqlID", new String[] { sqlID,
                    params.toString() }, e);
        }
	}

	@Override
	public List findListBySqlID(String sqlID, Map<String, Object> params) {
		// TODO Auto-generated method stub
		 try {
//				if(this.session == null){
//					SqlSessionTemplate template = (SqlSessionTemplate) AppServiceHelper.findBean("sessionTemplate");
//					setSession(template);				
//				}
	            return this.session.selectList(sqlID, params);
	        } catch (DataAccessException e) {
	            throw new DaoException("error.dao.findListBySqlID", new String[] { sqlID,
	                    params.toString() }, e);
	        }
	}

	@Override
	public Page<T> findPageBySqlID(String sqlID, Map<String, Object> params, int pageNo, int limit) {
		// TODO Auto-generated method stub
		Page<T> page = new Page<T>();
        page.setPageNo(pageNo);
        page.setLimit(limit);
        String findSqlMapId = sqlID;
        if (logger.isDebugEnabled()) {
            logger.debug("findSqlMapId=" + findSqlMapId);
            logger.debug("pageNo=" + pageNo);
            logger.debug("limit=" + limit);
            logger.debug("Order By=" + params.get("order"));
        }
        try {
        	RowBounds rowBounds = new RowBounds(page.getOffset(), limit);
//        	if(this.session == null){
//				SqlSessionTemplate template = (SqlSessionTemplate) AppServiceHelper.findBean("sessionTemplate");
//				setSession(template);				
//			}
            List<T> resultList = this.session.selectList(findSqlMapId, params, rowBounds);
            page.setResultList(resultList);
            if (resultList != null && resultList.size() > 0) {
				page.setTotalRows(resultList.size());
			} else {
				page.setTotalRows(PAGE_TOTAL_DEFAULT);
			}
            
        } catch (DataAccessException e) {
            throw new DaoException("error.dao.searchByPage",
                    new String[] { entityClass.getName() }, e);
        }
		return page;
	}

	@Override
	public int executeDelete(String sqlID, Object params) {
		// TODO Auto-generated method stub
		  try {
//				if(this.session == null){
//					SqlSessionTemplate template = (SqlSessionTemplate) AppServiceHelper.findBean("sessionTemplate");
//					setSession(template);				
//				}
	            return this.session.delete(sqlID, params);
	        } catch (DataAccessException e) {
	            throw new DaoException("error.dao.executeDelete", new String[] { sqlID,
	                    params.toString() }, e);
	        }
	}

	@Override
	public int executeUpdate(String sqlID, Object params) {
		// TODO Auto-generated method stub
		 try {
//				if(this.session == null){
//					SqlSessionTemplate template = (SqlSessionTemplate) AppServiceHelper.findBean("sessionTemplate");
//					setSession(template);				
//				}
	            return this.session.update(sqlID, params);
	        } catch (DataAccessException e) {
	            throw new DaoException("error.dao.executeUpdate", new String[] { sqlID,
	                    params.toString() }, e);
	        }
	}

	@Override
	public void insertHistory(Map<String, Object> params) {
		// TODO Auto-generated method stub
		String insertSqlMapId = getEntityName() + "." + PRE_INSERT_HISTORY;
		 try {
//				if(this.session == null){
//					SqlSessionTemplate template = (SqlSessionTemplate) AppServiceHelper.findBean("sessionTemplate");
//					setSession(template);				
//				}
			 this.session.insert(insertSqlMapId, params);
	        } catch (DataAccessException e) {
	            throw new DaoException("jdbc.error.code.Common.insertHistory", new String[] {
	                    entityClass.getName(), params.toString() }, e);
	        }
	}

	/**
	 * 判断输入参数是否全空，TRUE/输入参数有效;FALSE/输入参数全部为空
	 * 
	 * @param params
	 * @return
	 * @author chaoming.yang
	 * @since 2009-12-21
	 */
	private Boolean checkParams(Map<String, Object> params) {
		if (null == params) {
			return false;
		}

		Iterator<String> iter = params.keySet().iterator();
		while (iter.hasNext()) {
			Object key = iter.next();
			if (params.get(key) != null && StringUtils.isNotBlank(params.get(key).toString())) {
				return true;
			}
		}
		return false;
	}

}

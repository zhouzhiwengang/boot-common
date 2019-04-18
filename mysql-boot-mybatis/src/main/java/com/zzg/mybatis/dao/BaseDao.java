package com.zzg.mybatis.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.zzg.mybatis.page.Page;

/**
 * 
 * @ClassName: BaseDao
 * @Description: mybatis 单表查询dao 层封装
 * @author: 世纪伟图 -zzg
 * @date: 2019年4月18日 上午9:46:53
 * 
 * @param <T>
 * @Copyright: 2019 www.digipower.cn 注意：本内容仅限于深圳市世纪伟图科技开发有限公司内部使用，禁止用于其他的商业目的
 */
public interface BaseDao<T> {
//	public static final String PRE_INSERT = "insert";
//
//	public static final String PRE_UPDATE = "update";
//
//	public static final String PRE_DELETE = "delete";
//
//	public static final String PRE_DELETE_BY_PARAMS = "deleteByParams";
//
//	public static final String PRE_FIND = "find";
//
//	public static final String PARAMS_KEY_ORDER = "order";
//
//	/** The Constant PRE_INSERT_HISTORY. */
//	public static final String PRE_INSERT_HISTORY = "insertHistory";

	/**
	 * 分页查询
	 * 
	 * @param params
	 *            放置参数的Map对象，key=参数名，value=参数值
	 * @param pageNo
	 *            查询页码
	 * @param limit
	 *            每页限定记录数
	 * @return 组装好的Page对象
	 */
	public Page<T> findByPage(Map<String, Object> params, int pageNo, int limit);

	/**
	 * 创建实体对象
	 * 
	 * @param entity
	 * @return TODO
	 */
	public Long insert(T entity);

	/**
	 * 批量创建实体对象，采用Btach批量处理机制
	 * 
	 * @param collection
	 */
	public int insertAll(final Collection<T> collection);

	public int insertAllByBatch(final Collection<T> collection);

	/**
	 * 更新实体对象
	 * 
	 * @param entity
	 */
	public int update(T entity);

	/**
	 * 批量更新实体对象，采用Btach批量处理机制
	 * 
	 * @param collection
	 */
	public int updateAll(final Collection<T> collection);

	/**
	 * 查询全部记录
	 * 
	 * @return 记录集合
	 */
	public List<T> findAll();

	/**
	 * 删除实体对象
	 * 
	 * @param entity
	 */
	public int delete(T entity);

	/**
	 * 批量删除实体对象，采用Btach批量处理机制
	 * 
	 * @param collection
	 */
	public int deleteAll(final Collection<T> collection);

	/**
	 * 按照Map参数进行条件删除
	 * 
	 * @param params
	 */
	public int deleteByParams(Map<String, Object> params);

	/**
	 * 根据主键返回唯一对象
	 * 
	 * @param sid
	 *            序列主键
	 * @return 实体对象
	 */
	public T findByPrimaryKey(Long sid);

	/**
	 * 根据主键数组返回一批对象
	 * 
	 * @param sids
	 *            序列主键数组
	 * @return 实体对象链表
	 */
	public List<T> findByPrimaryKeys(Long[] sids);

	/**
	 * 根据参数返回一个实体对象，一般用于查询具有唯一约束条件的记录 必须保证传入的参数确实能定位唯一一个对象，否则将iBatis会抛出运行异常
	 * 
	 * @param params
	 *            参数Map对象
	 * @return 实体对象
	 */
	public T findUniqueByParams(Map<String, Object> params);

	/**
	 * 根据参数返回实体对象集合
	 * 
	 * @param params
	 *            参数Map对象
	 * @return 实体对象链表
	 */
	public List<T> findByParams(Map<String, Object> params);

	/**
	 * 直接传入SQL Mapping ID和Map参数查询对象 主要用于一些帮助方法调用，调用者根据负责自行进行类型转型
	 * 
	 * @param sqlID
	 *            SQL Mapping ID
	 * @param params
	 *            参数Map对象
	 * @return
	 */
	public Object findObjectBySqlID(String sqlID, Map<String, Object> params);

	/**
	 * 直接传入SQL Mapping ID和Map参数查询集合 主要用于一些帮助方法调用，调用者根据负责自行进行类型转型
	 * 
	 * @param sqlID
	 *            SQL Mapping ID
	 * @param params
	 *            参数Map对象
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List findListBySqlID(String sqlID, Map<String, Object> params);

	/**
	 * 直接传入SQL Mapping ID和Map参数查询集合 主要用于一些特殊查询又需要分页
	 * 
	 * @param sqlID
	 *            SQL Mapping ID
	 * @param params
	 *            参数Map对象
	 * @return 分页对象
	 *
	 * @author zhongjingquan
	 *
	 * @see 2011-06-08
	 */
	public Page<T> findPageBySqlID(String sqlID, Map<String, Object> params, int pageNo, int limit);

	/**
	 * 根据指定SQL语句和参数调用数据删除
	 * 
	 * @param sqlID
	 *            SQL Mapping ID
	 * @param params
	 *            参数Map对象
	 * @return
	 */
	int executeDelete(String sqlID, Object params);

	/**
	 * 根据指定SQL语句和参数调用数据update
	 * 
	 * @param sqlID
	 *            SQL Mapping ID
	 * @param params
	 *            参数Map对象
	 * @return
	 */
	int executeUpdate(String sqlID, Object params);

	/**
	 * Insert entity object into history table.
	 * 
	 * @param params
	 *            defualt:params.put("TRANSACTION_BY",
	 *            LoginUtil.getUpcUserPin()); Default:
	 *            params.put("TRANSACTION_TIMESTAMP", new Date());
	 *            TRANSACTION_ID : operation type: insert, delete, update. The
	 *            detail info please see: @see
	 *            com.hp.common.constant.HistoryTransitionId EVENT_ID: according
	 *            to business rule, input the event_id.Event Id can be defined
	 *            as constant in business moduel Other parameters can also add
	 *            into this map
	 */
	public void insertHistory(Map<String, Object> params);

}

package com.zzg.mybatis.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.zzg.mybatis.page.Page;

/**
 * 
 * @ClassName:  BaseService   
 * @Description: mybatis 单表查询  service 层封装
 * @author: 世纪伟图 -zzg
 * @date:   2019年4月18日 上午9:51:23   
 *   
 * @param <T>  
 * @Copyright: 2019 www.digipower.cn 
 * 注意：本内容仅限于深圳市世纪伟图科技开发有限公司内部使用，禁止用于其他的商业目的
 */
public interface BaseService<T> extends Serializable {
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
    public Page<T> findByPage(Map<String, Object> params, Integer pageNo, Integer limit);

    /**
     * 分页查询(要求params必须含有必要的“查询页码”和“每页限定记录数”参数)
     * 
     * @param params
     *            放置参数的Map对象，key=参数名，value=参数值
     * @return 组装好的Page对象
     */
    public Page<T> findByPage(Map<String, Object> params);

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
     * insert current entity object to history record table.
     * 
     * @param params
     *            the parameters
     */
    public void insertHistory(Map<String, Object> params);
    
    /**
     * 直接传入SQL Mapping ID和Map参数查询集合 主要用于一些特殊查询又需要分页
     * 
     * @param sqlID
     *            SQL Mapping ID
     * @param params
     *            参数Map对象
     * @return
     *     分页对象
     *
     * @author zhongjingquan
     *
     * @see 2011-06-08
     */
    public Page<T> findPageBySqlID(String sqlID, Map<String, Object> params,int pageNo,int limit);

}

package com.zzg.mybatis.page;

import java.util.List;

/**
 * 
 * @ClassName:  Page   
 * @Description: mybatis 单表查询  page层封装
 * @author: 世纪伟图 -zzg
 * @date:   2019年4月18日 上午9:48:06   
 *   
 * @param <T>  
 * @Copyright: 2019 www.digipower.cn 
 * 注意：本内容仅限于深圳市世纪伟图科技开发有限公司内部使用，禁止用于其他的商业目的
 */
public class Page<T> {
	/** The Constant PAGE_NO. */
    public static final String PAGE_NO = "qm.pn";

    /** The Constant PAGE_LIMIT. */
    public static final String PAGE_LIMIT = "qm.limit";

    // 一页显示的记录数
    /** The limit. */
    private int limit = 200;
    // 记录总数
    /** The total rows. */
    private int totalRows;
    // 当前页码
    /** The page no. */
    private int pageNo;
    // 结果集存放List
    /** The result list. */
    private List<T> resultList;

    /**
     * Gets the limit.
     *
     * @return the limit
     */
    public int getLimit() {
        return limit;
    }

    /**
     * Sets the limit.
     *
     * @param limit the new limit
     */
    public void setLimit(int limit) {
        this.limit = limit;
    }

    /**
     * Gets the result list.
     *
     * @return the result list
     */
    public List<T> getResultList() {
        return resultList;
    }

    /**
     * Sets the result list.
     *
     * @param resultList the new result list
     */
    public void setResultList(List<T> resultList) {
        this.resultList = resultList;
    }

    // 计算总页数
    /**
     * Gets the total pages.
     *
     * @return the total pages
     */
    public int getTotalPages() {
        int totalPages;
        if (totalRows % limit == 0) {
            totalPages = totalRows / limit;
        } else {
            totalPages = (totalRows / limit) + 1;
        }
        return totalPages;
    }

    /**
     * Gets the total rows.
     *
     * @return the total rows
     */
    public int getTotalRows() {
        return totalRows;
    }

    /**
     * Sets the total rows.
     *
     * @param totalRows the new total rows
     */
    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    /**
     * Gets the offset.
     *
     * @return the offset
     */
    public int getOffset() {
        return (pageNo - 1) * limit;
    }

    /**
     * Gets the end index.
     *
     * @return the end index
     */
    public int getEndIndex() {
        if (getOffset() + limit > totalRows) {
            return totalRows;
        } else {
            return getOffset() + limit;
        }
    }

    /**
     * Gets the page no.
     *
     * @return the page no
     */
    public int getPageNo() {
        return pageNo;
    }

    /**
     * Sets the page no.
     *
     * @param pageNo the new page no
     */
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

}

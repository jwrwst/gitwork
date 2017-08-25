/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.platform.rp.framework.mvcface.vo;

import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 */
@XmlRootElement
public class Page {
    //分页参数

    private int pageNo = 1;
    private int pageSize = 20;
    //返回结果
    private List result = Collections.emptyList();
	private int totalPages = -1;
	private int totalCount=0;
//    private RestfulResult restResult;
	
	private int start ;
	private int end;
	
	protected String orderBy = null;
	protected String order = null;

	public Page(int pageSize,int totalCount, int pageNo){ 
	   if(pageSize>0)
		   this.pageSize = pageSize; 
	   else
		   this.pageSize =20;
	   
       this.totalCount = totalCount; 
       setPageNo(pageNo); 
       this.totalPages=getTotalPages();
       this.start =this.pageSize*(this.pageNo-1);

    } 
	 
    // 构造函数
    public Page() {
        super();
    }

    public Page(int pageSize) {
        setPageSize(pageSize);
    }

    //查询参数函数
    /**
     * 获得当前页的页号,序号从1开始,默认为1.
     */
    public int getPageNo() {
        return pageNo;
    }

    /**
     * 设置当前页的页号,序号从1开始,低于1时自动调整为1.
     */
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;

        if (pageNo < 1) {
            this.pageNo = 1;
        }
    }

    /**
     * 获得每页的记录数量,默认为10.
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 设置每页的记录数量.
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    // 查询结果函数
    /**
     * 取得页内的记录列表.
     */
    public List getResult() {
        return result;
    }

    public void setResult(final List result) {
        this.result = result;
    }

    public int getTotalPages() {
    	int size = totalCount/pageSize;//总条数/每页显示的条数=总页数 
        int mod = totalCount % pageSize;//最后一页的条数 
        if(mod != 0) 
            size++; 
        return totalCount == 0 ? 1 : size; 
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

}

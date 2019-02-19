package com.ali.leb.base.orm;

import com.alibaba.fastjson.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <Strong>分页通用包装类</Strong><br>
 * 统一封装一个通用的面向表示层的综合对象，包含有查询对象的结果结合列表，以及该集合在对象所处的分页位置等等信息 注意所有序号从1开始. Page<User>
 * page = new Page<User>(5); dao.findPage(page, "from User user");
 * 
 * @param <T>
 * @author zoey
 * @version 1.0
 */
@SuppressWarnings("serial")
public class PageExtend<T> implements Serializable {

	/**  */
	public static final String ASC = "asc";

	/**  */
	public static final String DESC = "desc";

	/**
	 * <strong>public</strong> 默认每页记录数
	 */
	public static int DEFAULT_PAGE_SIZE = 10;
	/**
	 * <strong>public</strong> 最大页数
	 */
	public static int MAX_PAGE_SIZE = 100;

	// 分页参数 //
	/**
	 * <strong>public</strong> 当前页码数
	 */
	protected int currentPageNo = 1;
	/**
	 * <strong>public</strong> 每页记录数
	 */
	protected int pageSize = DEFAULT_PAGE_SIZE;
	/**
	 * <strong>public</strong> 当前返回的分页集合对象List<T>
	 */
	protected List<T> result = Collections.emptyList();
	/**
	 * <strong>public</strong> 总记录数。默认为-1（非自动统计）
	 */
	protected long totalCount = -1;
	/**
	 * <strong>public</strong> 是否自动统计总记录数
	 */
	protected boolean autoCount = true;
	/**
	 * <strong>public</strong> 该分页集合所使用的URL
	 */
	protected String pageUrl = "errorPage.jsp";

	/**  */
	protected String formName;

	/**  */
	protected String orderBy;

	/**  */
	protected String order;

	// TODO 以下属性待验证是否需要

	/**
	 * 该页从那一行开始
	 * 
	 */
	private long start;

	/**
	 * 查询条件
	 */
	protected String condition = "";
	/**
	 * 参数
	 */
	protected Object[] values = new Object[] {};

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public Object[] getValues() {
		if(null!=values){
			return Arrays.copyOf(values, values.length);
		}
		return null;
	}

	public void setValues(Object[] values) {
		if(null!=values){
			this.values=Arrays.copyOf(values,values.length);
		}else{
			this.values=null;
		}
	}

	/**
	 * 
	 * @param pageSize
	 */
	public PageExtend(final int pageSize) {
		setPageSize(pageSize);
	}

	/**
	 * 
	 * @param pageSize
	 * @param autoCount
	 */
	public PageExtend(final int pageSize, final boolean autoCount) {
		setPageSize(pageSize);
		setAutoCount(autoCount);
	}

	/**
	 * 空分页记录对象构造函数
	 */
	public PageExtend() {
		this(DEFAULT_PAGE_SIZE);
	}

	/**
	 * 自定义分页对象构造函数
	 * 
	 * @param start
	 * @param totalSize
	 * @param pageSize
	 * @param data
	 */
	public PageExtend(long start, long totalSize, int pageSize, List<T> data) {
		this.pageSize = pageSize;
		this.start = start;
		this.totalCount = totalSize;
		this.result = data;

	}

	/**
	 * 获取总页数
	 * 
	 * @return long
	 */
	public long getTotalPageCount() {
		Assert.isTrue(pageSize > 0);
		if (totalCount % pageSize == 0) {
			return totalCount / pageSize;
		}
		return totalCount / pageSize + 1;
	}

	/**
	 * 是否已设置排序字段,无默认值.
	 * 
	 * @return boolean
	 */
	public boolean isOrderBySetted() {
		return (StringUtils.isNotBlank(orderBy) && StringUtils
				.isNotBlank(order));
	}

	/**
	 * 根据pageNo和pageSize计算当前页第一条记录在总结果集中的位置,序号从1开始.
	 * 
	 * @return int
	 */
	public int getFirstOfPage() {
		return ((currentPageNo - 1) * pageSize) + 1;
	}

	/**
	 * 
	 * @return int
	 */
	public int getLastOfPage() {
		return currentPageNo * pageSize;
	}

	/**
	 * 
	 * @return int
	 */
	public static int getDEFAULT_PAGE_SIZE() {
		return DEFAULT_PAGE_SIZE;
	}

	/**
	 * 
	 * @param dEFAULTPAGESIZE
	 */
	public static void setDEFAULT_PAGE_SIZE(int dEFAULTPAGESIZE) {
		DEFAULT_PAGE_SIZE = dEFAULTPAGESIZE;
	}

	/**
	 * 
	 * @return int
	 */
	public int getCurrentPageNo() {
		return currentPageNo;
	}

	/**
	 * 
	 * @param currentPageNo
	 */
	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}

	/**
	 * 
	 * @return int
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 
	 * @param pageSize
	 */
	public void setPageSize(int pageSize) {
		if (pageSize > MAX_PAGE_SIZE) {
			this.pageSize = MAX_PAGE_SIZE;
		} else {
			this.pageSize = pageSize;
		}
	}

	/**
	 * 
	 * @return List<T>
	 */
	public List<T> getResult() {
		return result;
	}

	/**
	 * 
	 * @param result
	 */
	public void setResult(List<T> result) {
		this.result = result;
	}

	/**
	 * 
	 * @return long
	 */
	public long getTotalCount() {
		return totalCount;
	}

	/**
	 * 
	 * @param totalCount
	 */
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * 
	 * @return boolean
	 */
	public boolean isAutoCount() {
		return autoCount;
	}

	/**
	 * 
	 * @param autoCount
	 */
	public void setAutoCount(boolean autoCount) {
		this.autoCount = autoCount;
	}

	/**
	 * 
	 * @return String
	 */
	public String getPageUrl() {
		return pageUrl;
	}

	/**
	 * 
	 * @param pageUrl
	 */
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	/**
	 * 
	 * @return long
	 */
	public long getStart() {
		return start;
	}

	/**
	 * 
	 * @param start
	 */
	public void setStart(long start) {
		this.start = start;
	}

	/**
	 * 
	 * @return String
	 */
	public String getOrderBy() {
		return orderBy;
	}

	/**
	 * 
	 * @param orderBy
	 */
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	/**
	 * 
	 * @return String
	 */
	public String getOrder() {
		return order;
	}

	/**
	 * 
	 * @param order
	 */
	public void setOrder(String order) {
		this.order = order;
	}

	/**
	 * 
	 * @return String
	 */
	public static String getAsc() {
		return ASC;
	}

	/**
	 * 
	 * @return String
	 */
	public static String getDesc() {
		return DESC;
	}

	/**
	 * 
	 * @return String
	 */
	public String getFormName() {
		return formName;
	}

	/**
	 * 
	 * @param formName
	 */
	public void setFormName(String formName) {
		this.formName = formName;
	}

	/**
	 * 返回当前页面的JArt 调用的JSON值
	 * 
	 * @return String
	 */
	public String getJartJsonResult() {
		return "{total:" + this.getTotalCount() + ",rows:"
				+ JSONArray.toJSONString(this.getResult()) + "}";
	}

}
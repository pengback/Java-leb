/**	
 * <br>
 * Copyright 2011 IFlyTek. All rights reserved.<br>
 * <br>			 
 * Package: com.iflytek.ywq.base.utils.dto <br>
 * FileName: ResultDto.java <br>
 * <br>
 * @version
 * @author hyzha
 * @created 2014年9月2日
 * @last Modified 
 * @history
 */

package com.ali.leb.base.utils.dto;

/**
 * 结果DTO
 *
 */

public class ResultDto {
	
	/**
	 * 结果标示
	 */
	private String flag;
	
	/**
	 * 结果详细
	 */
	private String result;
	
	/**
	 * 需要传回页面的数据
	 */
	private Object data;
	
	public ResultDto()
	{
		
	}
	
	public ResultDto(String flag, String result)
	{
		this.flag = flag;
		this.result = result;
	}
	public ResultDto(String flag, String result, Object data)
	{
		this.flag = flag;
		this.result = result;
		this.data = data;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
}

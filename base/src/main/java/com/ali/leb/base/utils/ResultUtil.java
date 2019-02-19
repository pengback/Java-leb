
package com.ali.leb.base.utils;

import com.ali.leb.base.orm.PageExtend;
import com.ali.leb.base.utils.dto.ResultDto;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 结果构造工具
 * 
 */

public class ResultUtil {
	
	/**
	 * 返回的标志为布尔值
	 */
	public static final Boolean YES = true;
	
	/**
	 * 返回的标志不为布尔值
	 */
	public static final Boolean NO = false;

	/**
	 * 成功
	 */
	public static final String TRUE = "true";

	/**
	 * 失败
	 */
	public static final String FALSE = "false";

	/**
	 * 
	 * 封装成功消息
	 * 
	 * @param result
	 *            消息
	 */
	public static ResultDto success(String result) {
		ResultDto dto = new ResultDto();
		dto.setFlag(TRUE);
		dto.setResult(result);
		return dto;
	}

	/**
	 * 
	 * 封装失败消息
	 * 
	 * @param result
	 *            消息
	 * @return
	 */
	public static ResultDto fail(String result) {
		ResultDto dto = new ResultDto();
		dto.setFlag(FALSE);
		dto.setResult(result);
		return dto;
	}

	/**
	 * 封装成功消息
	 * 
	 * @param result
	 *            消息结果
	 * @param data
	 *            需要返回的数据
	 * @return 结果对象
	 */
	public static ResultDto success(String result, Object data) {
		return new ResultDto(TRUE, result, data);
	}

	/**
	 * 封装失败消息
	 * 
	 * @param result
	 *            消息结果
	 * @param data
	 *            需要返回的数据
	 * @return 结果对象
	 */
	public static ResultDto fail(String result, Object data) {
		return new ResultDto(FALSE, result, data);
	}

	/**
	 * 
	 * 返回json格式
	 * 
	 * @param flag
	 *            返回结果状态
	 * @param result
	 *            结果
	 * @return json str
	 */
	private static String jsonResult(String flag, String result) {
		return "{\"returnFlag\":" + flag + ",\"data\":\"" + result + "\"}";
	}

	/**
	 * 
	 * 返回json格式
	 * 
	 * @param result
	 * @return
	 */
	private static String jsonResult(ResultDto result) {
		return "{\"returnFlag\":" + result.getFlag() + ",\"data\":\""
				+ result.getResult() + "\",\"returnData\":\""
				+ result.getData() + "\"}";
	}

	/**
	 * 
	 * 构造空page
	 * 
	 * @param <T>
	 *            模板
	 * @param page
	 *            分页实体
	 * @return 构造后page
	 */
	public static <T> PageExtend<T> emptyPage(PageExtend<T> page) {
		page.setResult(new ArrayList<T>(0));
		page.setTotalCount(0l);
		return page;
	}

	/**
	 * 
	 * 回复结果到页面ajax请求
	 * 
	 * @param response
	 *            回执消息
	 * @param result
	 *            结果
	 */
	public static void sendResult(HttpServletResponse response, String flag,
			String result) {
		WebUtils.sendDirectToClient(response, WebUtils.CONTENTTYPE_TEXTHTML,
				WebUtils.CONTENT_CHARSET_UTF8, jsonResult(flag, result));
	}

	/**
	 * 
	 * 回复结果到页面ajax请求
	 * 
	 * @param response
	 * @param result
	 */
	public static void sendResult(HttpServletResponse response, ResultDto result) {
		WebUtils.sendDirectToClient(response, WebUtils.CONTENTTYPE_TEXTHTML,
				WebUtils.CONTENT_CHARSET_UTF8, jsonResult(result));
	}

	/**
	 * 
	 * 回复结果到页面ajax请求
	 * 
	 * @param response
	 *            回执消息
	 * @param result
	 *            结果
	 */
	public static void sendString(HttpServletResponse response, String result) {
		WebUtils.sendDirectToClient(response, WebUtils.CONTENTTYPE_TEXTHTML,
				WebUtils.CONTENT_CHARSET_UTF8, result);
	}

	/**
	 * 
	 * 回复消息到页面ajax请求
	 * 
	 * @param response
	 *            回执对象
	 * @param msg
	 *            消息
	 */
	public static void sendMsg(HttpServletResponse response, String flag,
			String msg) {
		WebUtils.sendDirectToClient(response, WebUtils.CONTENTTYPE_TEXTJSON,
				WebUtils.CONTENT_CHARSET_UTF8, jsonResult(flag, msg));
	}

	/**
	 * 
	 * 回复消息到页面ajax请求
	 * 
	 * @param response
	 * @param dto
	 */
	public static void sendMsg(HttpServletResponse response, ResultDto dto) {
		WebUtils.sendDirectToClient(response, WebUtils.CONTENTTYPE_TEXTJSON,
				WebUtils.CONTENT_CHARSET_UTF8, jsonResult(dto));
	}

	/**
	 * 
	 * 回复列表
	 * 
	 * @param response
	 *            回执对象
	 * @param list
	 *            列表
	 */
	@SuppressWarnings("rawtypes")
	public static void sendList(HttpServletResponse response, List list) {
		WebUtils.sendDirectToClient(response, WebUtils.CONTENTTYPE_TEXTJSON,
				WebUtils.CONTENT_CHARSET_UTF8, JSONObjectUtils.fromArrayObjectForBigDecimal(list));
	}

	/**
	 * 
	 * 回复列表
	 * 
	 * @param response
	 *            回执对象
	 * @param list
	 *            列表
	 */
	@SuppressWarnings("rawtypes")
	public static void sendList(HttpServletResponse response, List list,
			String... ignore) {
		JsonConfig config = new JsonConfig();
		config.setExcludes(ignore);
		WebUtils.sendDirectToClient(response, WebUtils.CONTENTTYPE_TEXTJSON,
				WebUtils.CONTENT_CHARSET_UTF8,
				JSONArray.fromObject(list, config).toString());
	}

	/**
	 * 
	 * 回复列表
	 * 
	 * @param response
	 *            回执对象
	 * @param list
	 *            列表
	 */
	@SuppressWarnings("rawtypes")
	public static void sendListInclude(HttpServletResponse response, List list,
			String... includes) {
		WebUtils.sendDirectToClient(response, WebUtils.CONTENTTYPE_TEXTJSON,
				WebUtils.CONTENT_CHARSET_UTF8,
				JSONUtils.toJSONInclude(list, includes));
	}

	/**
	 * 
	 * 回复对象
	 * 
	 * @param response
	 *            回执对象
	 * @param obj
	 *            对象
	 */
	public static void sendObject(HttpServletResponse response, Object obj) {
		
		WebUtils.sendDirectToClient(response, WebUtils.CONTENTTYPE_TEXTJSON,
				WebUtils.CONTENT_CHARSET_UTF8, JSONObjectUtils.fromObjectForBigDecimal(obj));
	}

	/**
	 * 
	 * @param response
	 *            回执对象
	 * @param boolen
	 *            标识是否是bool值
	 */
	public static void sendObject(HttpServletResponse response, ResultDto dto,
			Boolean boolen) {
		if(boolen){
			String json = "{\"data\":" + dto.getData() + ",\"flag\":"
					+ dto.getFlag() + ",\"result\":\"" + dto.getResult() + "\"}";
			WebUtils.sendDirectToClient(response, WebUtils.CONTENTTYPE_TEXTJSON,
					WebUtils.CONTENT_CHARSET_UTF8, json);
		}else{
			sendObject(response,dto);
		}
	}

	/**
	 * 
	 * 回复对象
	 * 
	 * @param response
	 *            回执对象
	 * @param obj
	 *            对象
	 */
	public static void sendObjectInclude(HttpServletResponse response,
                                         Object obj, String... includes) {
		WebUtils.sendDirectToClient(response, WebUtils.CONTENTTYPE_TEXTJSON,
				WebUtils.CONTENT_CHARSET_UTF8,
				JSONUtils.toJSONInclude(obj, includes));
	}

	/**
	 * 
	 * 回复对象
	 * 
	 * @param response
	 *            回执对象
	 * @param obj
	 *            对象
	 * @param ignore
	 *            忽略的属性集合
	 */
	public static void sendObject(HttpServletResponse response, Object obj,
			String... ignore) {
		JsonConfig config = new JsonConfig();
		config.setExcludes(ignore);
		WebUtils.sendDirectToClient(response, WebUtils.CONTENTTYPE_TEXTJSON,
				WebUtils.CONTENT_CHARSET_UTF8,
				JSONObject.fromObject(obj, config).toString());
	}

	/**
	 * 
	 * 回复对象
	 * 
	 * @param response
	 *            回执对象
	 * @param obj
	 *            对象
	 */
	public static void sendObjectText(HttpServletResponse response, Object obj) {
		
		WebUtils.sendDirectToClient(response, WebUtils.CONTENTTYPE_TEXTHTML,
				WebUtils.CONTENT_CHARSET_UTF8, JSONObjectUtils.fromObjectForBigDecimal(obj));
	}

	/**
	 * 
	 * 回复分页
	 * 
	 * @param response
	 *            回执对象
	 * @param page
	 *            分页对象
	 */
	@SuppressWarnings("rawtypes")
	public static void sendPage(HttpServletResponse response, PageExtend page) {
		String pageJson = (new StringBuilder("{\"total\":"))
				.append(page.getTotalCount()).append(",\"rows\":")
				.append(JSONObjectUtils.fromArrayObjectForBigDecimal(page.getResult())).append("}")
				.toString();
		WebUtils.sendDirectToClient(response, WebUtils.CONTENTTYPE_TEXTJSON,
				WebUtils.CONTENT_CHARSET_UTF8, pageJson);
	}

	/**
	 * 
	 * 回复分页
	 * 
	 * @param response
	 *            回执对象
	 * @param page
	 *            分页对象
	 */
	@SuppressWarnings("rawtypes")
	public static void sendPage(HttpServletResponse response, PageExtend page,
			String... ignore) {
		JsonConfig config = new JsonConfig();
		config.setExcludes(ignore);
		String pageJson = (new StringBuilder("{\"total\":"))
				.append(page.getTotalCount()).append(",\"rows\":")
				.append(JSONArray.fromObject(page.getResult(), config))
				.append("}").toString();
		WebUtils.sendDirectToClient(response, WebUtils.CONTENTTYPE_TEXTJSON,
				WebUtils.CONTENT_CHARSET_UTF8, pageJson);
	}

	/**
	 * 
	 * 回复分页
	 * 
	 * @param response
	 *            回执对象
	 * @param page
	 *            分页对象
	 */
	@SuppressWarnings("rawtypes")
	public static void sendPageInclude(HttpServletResponse response, PageExtend page,
			String... includes) {
		String pageJson = (new StringBuilder("{\"total\":"))
				.append(page.getTotalCount()).append(",\"rows\":")
				.append(JSONUtils.toJSONInclude(page.getResult(), includes))
				.append("}").toString();
		WebUtils.sendDirectToClient(response, WebUtils.CONTENTTYPE_TEXTJSON,
				WebUtils.CONTENT_CHARSET_UTF8, pageJson);
	}

	/**
	 * 
	 * 回复分页
	 * 
	 * @param response
	 *            回执对象
	 * @param page
	 *            分页对象
	 */
	@SuppressWarnings("rawtypes")
	public static void sendHTMLPage(HttpServletResponse response, PageExtend page) {
		WebUtils.sendDirectToClient(response, WebUtils.CONTENTTYPE_TEXTHTML,
				WebUtils.CONTENT_CHARSET_UTF8, page.getJartJsonResult());
	}

	/**
	 * 
	 * 回复MAP
	 * 
	 * @param response
	 * @param map
	 */
	@SuppressWarnings("rawtypes")
	public static void sendMap(HttpServletResponse response, Map map) {
		
		WebUtils.sendDirectToClient(response, WebUtils.CONTENTTYPE_TEXTJSON,
				WebUtils.CONTENT_CHARSET_UTF8, JSONObjectUtils.fromObjectForBigDecimal(map));
	}
}

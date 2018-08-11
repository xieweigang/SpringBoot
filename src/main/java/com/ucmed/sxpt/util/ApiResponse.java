package com.ucmed.sxpt.util;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;

/**
 * RESTful Controller接口返回实体
 * 
 * @author Administrator
 *
 */
public class ApiResponse implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6208609635357844334L;

	private static Logger LOG = Logger.getLogger(ApiResponse.class);

	/**
	 * 成功返回
	 */
	public static int SUCCESS = 200;
	public static String SUCCESS_MESSAGE = "成功";

	/**
	 * 条件错误
	 */
	public static int ERROR = 400;

	/**
	 * 根据域名无法取到医院id
	 */
	public static int DOMAIN_ERROR = 401;

	/**
	 * 页面信息获取错误，直接弹窗返回上一页
	 */
	public static int PAGE_ERROR = 402;

	/**
	 * 代码异常下失败
	 */
	public static int EXCEPTION = 500;

	/**
	 * token失效
	 */
	public static int TOKEN_INVALID = 505;

	/**
	 * 用户无权限
	 */
	public static int PERMISSION_INVALID = 506;

	/**
	 * 接口响应码
	 */
	private int returnCode;

	/**
	 * 接口响应信息
	 */
	private String returnMsg;

	/**
	 * 响应数据
	 */
	private Object returnData;

	/**
	 * 后台接口异常返回
	 */
	public static ResponseEntity<ApiResponse> responseException() {
		return new ResponseEntity<>(new ApiResponse(EXCEPTION, "您的网络状况不佳，请稍后再试", null), HttpStatus.OK);
	}

	/**
	 * 根据域名无法获取医院id
	 */
	public static ResponseEntity<ApiResponse> responseDomainError() {
		return new ResponseEntity<>(new ApiResponse(DOMAIN_ERROR, "根据域名获取医院id失败", null), HttpStatus.OK);
	}

	/**
	 * 页面信息获取错误，直接弹窗返回上一页
	 */
	public static ResponseEntity<ApiResponse> responsePageError() {
		return new ResponseEntity<>(new ApiResponse(PAGE_ERROR, "页面飞走了，请稍候再试", null), HttpStatus.OK);
	}

	/**
	 * token失效 异常返回
	 */
	public static ResponseEntity<ApiResponse> responseTokenInvalid() {
		LOG.warn("========================token失效========================");
		return new ResponseEntity<>(new ApiResponse(TOKEN_INVALID, "登录过期，请重新登录", null), HttpStatus.OK);
	}

	/**
	 * 用户无权限 异常返回
	 */
	public static ResponseEntity<ApiResponse> responsePermissionInvalid() {
		LOG.warn("========================用户无权限========================");
		return new ResponseEntity<>(new ApiResponse(PERMISSION_INVALID, "用户无权限", null), HttpStatus.OK);
	}

	/**
	 * 自定义成功返回
	 */
	public static ResponseEntity<ApiResponse> response(ApiResponse apiResponse) {
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
	}

	public static ResponseEntity<ApiResponse> responseSuccess() {
		return response(SUCCESS, SUCCESS_MESSAGE, null);
	}

	public static ResponseEntity<ApiResponse> responseSuccess(Object returnData) {
		return response(SUCCESS, SUCCESS_MESSAGE, returnData);
	}

	public static ResponseEntity<ApiResponse> responseError(String returnMsg) {
		return response(ERROR, returnMsg, null);
	}

	public static ResponseEntity<ApiResponse> response(int returnCode, String returnMsg) {
		return response(returnCode, returnMsg, null);
	}

	public static ResponseEntity<ApiResponse> response(int returnCode, String returnMsg, Object returnData) {
		ApiResponse res = new ApiResponse();
		res.setReturnCode(returnCode);
		res.setReturnMsg(returnMsg);
		res.setReturnData(returnData);
		return new ResponseEntity<ApiResponse>(res, HttpStatus.OK);
	}

	public ApiResponse() {
		super();
	}

	public ApiResponse(int returnCode, String returnMsg, Object returnData) {
		super();
		this.returnCode = returnCode;
		this.returnMsg = returnMsg;
		this.returnData = returnData;
	}

	public int getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(int returnCode) {
		this.returnCode = returnCode;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

	public Object getReturnData() {
		return returnData;
	}

	public void setReturnData(Object returnData) {
		this.returnData = returnData;
	}

}

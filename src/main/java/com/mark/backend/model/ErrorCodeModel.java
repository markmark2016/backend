package com.mark.backend.model;

/**
 * 微信平台全局返回码
 * 
 * @Title:ErrorCodeModel
 * @Description:TODO
 * @author YangTianxiao
 * @date 2016年3月15日
 * 
 */
public class ErrorCodeModel {
	/**
	 * 全局返回码
	 */
	private String errcode;
	/**
	 * 返回说明
	 */
	private String errmsg;

	public String getErrcode() {
		return errcode;
	}

	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

}

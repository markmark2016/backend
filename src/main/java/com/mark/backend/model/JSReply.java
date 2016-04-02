package com.mark.backend.model;

import com.mark.backend.utils.Constans;
//jsticket返回对象
public class JSReply {
	private String noncestr = "a";
	private String signature;
	private Long timestamp = 1459611621L;
	private String appid = Constans.APPID;

	public String getNoncestr() {
		return noncestr;
	}

	public void setNoncestr(String noncestr) {
		this.noncestr = noncestr;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

}

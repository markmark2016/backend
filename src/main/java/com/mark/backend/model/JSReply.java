package com.mark.backend.model;

import com.mark.backend.utils.Constans;

//jsticket返回对象
public class JSReply {
	private String nonceStr = "a";
	private String signature;
	private Long timestamp = 1459611621L;
	private String appId = Constans.APPID;

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
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

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

}

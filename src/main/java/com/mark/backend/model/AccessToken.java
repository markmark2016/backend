package com.mark.backend.model;

/**
 * 
 * @Title:AccessToken
 * @Description:TODO
 * @author YangTianxiao
 * @date 2016年3月15日
 * 
 */
public class AccessToken {
	// 获取到的凭证
	private String accessToken;
	// 凭证有效时间，单位：s
	private int expiresIn;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
}

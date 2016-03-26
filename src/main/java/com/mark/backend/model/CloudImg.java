package com.mark.backend.model;


public class CloudImg {

	/** 密钥 **/
	private String ACCESS_KEY;
	private String SECRET_KEY;
	/** 要上传到的仓库名称 **/
	private String bucketName;
	/** 云地址域名 **/
	private String imgPath;
	/** 上传图片所属的父文件夹 **/
	private String prefix;

	public String getACCESS_KEY() {
		return ACCESS_KEY;
	}

	public void setACCESS_KEY(String aCCESS_KEY) {
		ACCESS_KEY = aCCESS_KEY;
	}

	public String getSECRET_KEY() {
		return SECRET_KEY;
	}

	public void setSECRET_KEY(String sECRET_KEY) {
		SECRET_KEY = sECRET_KEY;
	}

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

}

package com.mark.backend.utils;

public class Constans {
	/**
	 * APPID
	 */
	public static String APPID = "wx4610ff3b6e3b4370";
	/**
	 * SECRET
	 */
	public static String SECRET = "f4f07054119aae7193f89ebd1c83ddac";
	/**
	 * 获取ACCESS_TOKEN接口
	 */
	public static String GET_ACCESSTOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
			+ APPID + "&secret=" + SECRET;
	/**
	 * ACCESS_TOKEN有效时间(单位：ms)
	 */
	public static int EFFECTIVE_TIME = 700000;
	/**
	 * conf.properties文件路径
	 */
	public static String CONF_PROPERTIES_PATH = "src/conf/config.properties";
	/**
	 * 微信接入token ，用于验证微信接口
	 */
	public static String TOKEN = "iMark";
}

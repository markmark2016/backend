package com.mark.backend.utils;

public class Constans {

    /**
     * APPID
     */
    // sub.markeveryday  wx1ee69033f7a8a6f3
    public static String APPID = "wx1ee69033f7a8a6f3";
    //public static String APPID = "wxdac023cef2cea008";
    /**
     * SECRET
     */
    public static String SECRET = "4b3ec1f5dbb9141459bcdaf4582362e9";
    //public static String SECRET = "6c1b7da82cc38eb2a0377e303e131dd7";
    /**
     * 获取ACCESS_TOKEN接口
     */
    public static String GET_ACCESSTOKEN_URL =
            "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
                    + APPID + "&secret=" + SECRET;
    public static String GET_ACUTH_ACCESSTOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
            + APPID
            + "&secret="
            + SECRET
            + "&grant_type=authorization_code&code=";

    public static String GET_USERINFO = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=";

    public static String GET_SNS_USERINFO = "https://api.weixin.qq.com/sns/userinfo?access_token=";

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
    /**
     * 回复书评
     */
    public static String REPLY_REMARK = "1";
    /**
     * 赞 书评
     */
    public static String LIKE_REMARK = "2";
    /**
     * 未读
     */
    public static String NOT_CHECK = "0";
    /**
     * 已读
     */
    public static String CHECK = "1";
    /**
     * 一天的毫秒数
     */
    public static Long DAY = 86400000L;
    /**
     * 日期格式 一
     */
    public static String DATE_TYPE_ONE = "yyyy-MM-dd H:m:s";
    /**
     * 豆瓣书籍接口
     */
    public static String DOUBAN_BOOK_URL = "https://api.douban.com/v2/book/search";
    /**
     * 发送模板消息接口
     */
    public static String TEMPLEATE_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";
    /**
     * 通知的模板id
     */
    public static final String TEMPLEATE_NOTIFICE_ID = "NVXx4dXcNB3ZAamlDiUtrtFPJ9BPLq07fMHM3Op8ba0";
    /**
     * js接口
     */
    public static final String JS_TOKEN = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?type=jsapi&access_token=";

}

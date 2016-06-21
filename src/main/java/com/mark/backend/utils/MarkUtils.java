package com.mark.backend.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mark.backend.model.AccessToken;
import com.mark.backend.model.CheckModel;
import com.mark.backend.model.JSReply;
import com.mark.backend.model.UserInfoAccessToken;
import com.mark.backend.mysql.po.Book;
import com.mark.backend.service.impl.WeixinService;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MarkUtils {
    private final static Logger LOGGER = LoggerFactory
            .getLogger(MarkUtils.class);
    private static final String wxTokenStr = Constans.TOKEN;

    private static final String ALGORITHM = "MD5";

    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * 获取当前时间
     */
    public static Date getCurrentTime() {
        return new Date();

    }


    /**
     * 获取当前时间
     */
    public static List<Date> getDatesBwtween(Date start, Date end) {
        List<Date> dates = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        cal.setTime(start);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        dates.add(start);
        while (cal.getTime().before(end)) {
            cal.add(Calendar.DATE, 1);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            dates.add(cal.getTime());
        }
        return dates;
    }

    /**
     * 获取当前时间
     */
    public static List<String> getDatesBwtweenInString(Date start, Date end) {
        List<Date> dates = getDatesBwtween(start, end);
        List<String> datesInString = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (Date d : dates) {

            datesInString.add(simpleDateFormat.format(d));
        }
        return datesInString;

    }

    /**
     * 获得当日0时0分0秒时间
     */
    public static Date getZeroTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getCurrentTime());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获得指定日期0时0分0秒时间
     */
    public static Date getSomeDayZeroTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date getYestarday() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int day = calendar.get(Calendar.DATE);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.DATE, day - 1);
        return calendar.getTime();
    }

    /**
     * 返回日期map
     */
    public static Map<String, String> getMonthStartAndEnd() {
        Map<String, String> dateMap = new HashMap<String, String>();
        // 当前月的第一天
        Calendar startDate = Calendar.getInstance();
        startDate.setTime(getCurrentTime());
        startDate.set(Calendar.HOUR_OF_DAY, 0);
        startDate.set(Calendar.MINUTE, 0);
        startDate.set(Calendar.SECOND, 0);
        startDate.set(Calendar.MILLISECOND, 0);
        startDate.set(Calendar.DAY_OF_MONTH, 1);
        Calendar endDate = Calendar.getInstance();
        // 下个月的第一天
        endDate.setTime(getCurrentTime());
        endDate.set(Calendar.HOUR_OF_DAY, 0);
        endDate.set(Calendar.MINUTE, 0);
        endDate.set(Calendar.SECOND, 0);
        endDate.set(Calendar.MILLISECOND, 0);
        endDate.set(Calendar.MONTH, endDate.get(Calendar.MONTH) + 1);
        endDate.set(Calendar.DAY_OF_MONTH, 1);
        dateMap.put("startDate",
                formatDate(Constans.DATE_TYPE_ONE, startDate.getTime()));
        dateMap.put("endDate",
                formatDate(Constans.DATE_TYPE_ONE, endDate.getTime()));
        return dateMap;
    }

    /**
     * 验证wx信息
     */
    public static String validateWechatInfo(CheckModel tokenModel) {
        String wxToken = wxTokenStr;
        String signature = tokenModel.getSignature();
        Long timestamp = tokenModel.getTimestamp();
        Long nonce = tokenModel.getNonce();
        String echoStr = tokenModel.getEchostr();
        if (StringUtils.isEmpty(signature)
                || StringUtils.isEmpty(timestamp.toString())
                || StringUtils.isEmpty(nonce.toString())) {
            return "error";
        } else {
            List<String> params = new ArrayList<String>();
            params.add(wxToken);
            params.add(timestamp.toString());
            params.add(nonce.toString());
            Collections.sort(params, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.compareTo(o2);
                }
            });
            String bigStr = params.get(0) + params.get(1) + params.get(2);
            // SHA1加密
            String digest = MarkUtils.encode("SHA1", bigStr);
            // 确认请求来至微信
            if (digest.equals(signature)) {
                return echoStr;
            }
        }
        return "error";

    }

    /**
     * 根据指定算法编码
     */
    public static String encode(String algorithm, String str) {
        if (str == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.update(str.getBytes());
            return getFormattedText(messageDigest.digest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        // 把密文转换成十六进制的字符串形式
        for (int j = 0; j < len; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }

    public static String encodeByMD5(String str) {
        if (str == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);
            messageDigest.update(str.getBytes());
            return getFormattedText(messageDigest.digest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static AccessToken getAccessToken() {
        String url = Constans.GET_ACCESSTOKEN_URL;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        CloseableHttpResponse response = null;

        try {
            response = httpClient.execute(get);
            HttpEntity entity = response.getEntity();
            int statusCode = response.getStatusLine().getStatusCode();
            if (200 != statusCode) {
                LOGGER.error("response error, status:{}", statusCode);
                return null;
            }
            if (entity != null) {
                long contentLength = entity.getContentLength();
                LOGGER.debug("getPage , status code:{}, length:{}", statusCode,
                        contentLength);
                // 响应内容
                String content = EntityUtils.toString(entity);
                System.out.println(content);
                AccessToken token = JSONObject.parseObject(content,
                        AccessToken.class);
                return token;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

/*    public static String getAuthJsonobject(String code) {
        String url = Constans.GET_ACUTH_ACCESSTOKEN_URL + code;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        CloseableHttpResponse response = null;

        try {
            response = httpClient.execute(get);
            HttpEntity entity = response.getEntity();
            int statusCode = response.getStatusLine().getStatusCode();
            if (200 != statusCode) {
                LOGGER.error("response error, status:{}", statusCode);
                return null;
            }
            if (entity != null) {
                // 响应内容
                String content = EntityUtils.toString(entity);
                JSONObject json = JSONObject.parseObject(content);
                String openId = json.getString("openid");
                return openId;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }*/

    public static UserInfoAccessToken getAuthJsonobject(String code) {
        String url = Constans.GET_ACUTH_ACCESSTOKEN_URL + code;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        CloseableHttpResponse response = null;

        try {
            response = httpClient.execute(get);
            HttpEntity entity = response.getEntity();
            int statusCode = response.getStatusLine().getStatusCode();
            if (200 != statusCode) {
                LOGGER.error("response error, status:{}", statusCode);
                return null;
            }
            if (entity != null) {
                // 响应内容
                String content = EntityUtils.toString(entity);
                JSONObject json = JSONObject.parseObject(content);

                UserInfoAccessToken userInfoAccessToken = JSONObject.toJavaObject(json, UserInfoAccessToken.class);
                System.out.println(userInfoAccessToken);
                return userInfoAccessToken;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JSONObject getSNSUserInfo(String accessToken, String openId) {
        System.out.println("Getting SNS User Info, access_token:" + accessToken);
        System.out.println("Getting SNS User Info, openid:" + openId);
        System.out.println();
        String url = Constans.GET_SNS_USERINFO + accessToken + "&openid=" + openId + "&lang=zh_CN";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(get);
            HttpEntity entity = response.getEntity();
            int statusCode = response.getStatusLine().getStatusCode();
            if (200 != statusCode) {
                LOGGER.error("response error, status:{}", statusCode);
                return null;
            }
            if (entity != null) {
                // 响应内容
                String content = EntityUtils.toString(entity, "UTF-8");
                JSONObject json = JSONObject.parseObject(content);
                return json;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JSONObject getUserInfo(String accessToken, String openId) {
        System.out.println("Getting User Info, access_token:" + accessToken);
        System.out.println("Getting User Info, openid:" + openId);
        System.out.println();
        String url = Constans.GET_USERINFO + accessToken + "&openid=" + openId + "&lang=zh_CN";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(get);
            HttpEntity entity = response.getEntity();
            int statusCode = response.getStatusLine().getStatusCode();
            if (200 != statusCode) {
                LOGGER.error("response error, status:{}", statusCode);
                return null;
            }
            if (entity != null) {
                // 响应内容
                String content = EntityUtils.toString(entity, "UTF-8");
                JSONObject json = JSONObject.parseObject(content);
                return json;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 格式化日期
     */
    public static String formatDate(String formate, Date date) {
        SimpleDateFormat format = new SimpleDateFormat(formate);
        return format.format(date);
    }

    public static List<Book> getDoubanBookList(String queryInfo, Integer count) {
        String url = Constans.DOUBAN_BOOK_URL + "?q=" + queryInfo + "&count="
                + count;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(get);
            HttpEntity entity = response.getEntity();
            int statusCode = response.getStatusLine().getStatusCode();
            if (200 != statusCode) {
                LOGGER.error("response error, status:{}", statusCode);
                return null;
            }
            if (entity != null) {
                // 响应内容
                String content = EntityUtils.toString(entity);
                JSONArray bookArray = JSONObject.parseObject(content)
                        .getJSONArray("books");
                List<Book> bookliList = transferJsonToBook(bookArray);
                return bookliList;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Book> transferJsonToBook(JSONArray bookArray) {
        List<Book> bookList = new ArrayList<Book>();
        JSONObject[] bookJson = bookArray.toArray(new JSONObject[0]);
        for (JSONObject json : bookJson) {
            Book book = new Book();
            book.setTitle(json.getString("title"));
            JSONArray authorArray = json.getJSONArray("author");
            if (authorArray.size() > 0) {
                book.setAuthor(authorArray.getString(0));
            } else {
                book.setAuthor("");
            }
            book.setSummary(json.getString("summary"));
            book.setImage(json.getString("image"));
            bookList.add(book);
        }
        return bookList;
    }

    public static String getJSTicket(String access_token) {
        String url = Constans.JS_TOKEN + access_token;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(get);
            HttpEntity entity = response.getEntity();
            int statusCode = response.getStatusLine().getStatusCode();
            if (200 != statusCode) {
                return null;
            }
            if (entity != null) {
                // 响应内容
                String content = EntityUtils.toString(entity, "UTF-8");
                JSONObject json = JSONObject.parseObject(content);
                String ticket = json.getString("ticket");
                return ticket;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JSReply createReply(String url) {
        try {
            url = URLDecoder.decode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String s = "jsapi_ticket=" + WeixinService.ticket
                + "&noncestr=a&timestamp=1459611621&url=" + url;
        String digest = MarkUtils.encode("SHA1", s);
        JSReply r = new JSReply();
        r.setSignature(digest);
        return r;
    }

    public static void main(String[] args) {
        //getAccessToken();
        // System.out.println(getZeroTime().getTime());
        // getMonthStartAndEnd();
        // getDoubanBookList("嫌疑人", 5);


        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat dateFormat = SimpleDateFormat.getDateInstance();
        try {
            Date s = dateFormat.parse("2016-03-01 00:00:00");
            Date e = dateFormat.parse("2016-04-01 00:00:00");

            List<Date> dates = getDatesBwtween(s, e);
            for (Date d : dates) {
                System.out.println(d.toString());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

}

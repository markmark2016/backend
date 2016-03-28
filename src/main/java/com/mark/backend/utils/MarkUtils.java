package com.mark.backend.utils;

import java.io.IOException;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.sasl.AuthorizeCallback;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mark.backend.model.AccessToken;
import com.mark.backend.model.CheckModel;
import com.mark.backend.mysql.po.Book;

public class MarkUtils {
	private final static Logger LOGGER = LoggerFactory
			.getLogger(MarkUtils.class);
	private static final String wxTokenStr = Constans.TOKEN;

	private static final String ALGORITHM = "MD5";

	private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	/**
	 * 获取当前时间
	 * 
	 * @return
	 */
	public static Date getCurrentTime() {
		return new Date();

	}

	/**
	 * 获得当日0时0分0秒时间
	 * 
	 * @return
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
	 * 返回日期map
	 * 
	 * @return
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
	 * 
	 * @param wxToken
	 * @param tokenModel
	 * @return
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
	 * 
	 * @param algorithm
	 * @param str
	 * @return
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

	public static String getAuthJsonobject(String code) {
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
	}

	public static JSONObject getUserInfo(String accessToken, String openId) {
		String url = Constans.GET_USERINFO + accessToken + "&openid=" + openId;
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
	 * 
	 * @param formate
	 * @param date
	 * @return
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

	public static void main(String[] args) {
		// getAccessToken();
		// System.out.println(getZeroTime().getTime());
		// getMonthStartAndEnd();
		getDoubanBookList("嫌疑人", 5);
	}
}

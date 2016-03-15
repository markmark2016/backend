package com.mark.backend.utils;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Date;

import org.springframework.util.StringUtils;

import com.mark.backend.model.CheckModel;

public class MarkUtils {

	private static final String wxTokenStr = "yangtianxiao";

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
			String[] str = { wxToken, timestamp + "", nonce + "" };
			Arrays.sort(str); // 字典序排序
			String bigStr = str[0] + str[1] + str[2];
			// SHA1加密
			String digest = MarkUtils.encode("SHA1", bigStr).toLowerCase();
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

}

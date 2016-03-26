package com.mark.backend.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mark.backend.model.CloudImg;
import com.mark.backend.service.ICloudUploadService;
import com.qiniu.api.auth.AuthException;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.config.Config;
import com.qiniu.api.io.IoApi;
import com.qiniu.api.io.PutExtra;
import com.qiniu.api.io.PutRet;
import com.qiniu.api.rs.PutPolicy;

@Service
public class CloudUploadServiceImpl implements ICloudUploadService {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(CloudUploadServiceImpl.class);
	/** 云端图片对象 */
	@Resource
	private CloudImg cloudImg;

	/**
	 * 
	 * @param file
	 * @param prefix
	 * @return 成功返回应存在数据库得路径，失败返回空null
	 */
	@Override
	public String upload(MultipartFile file, String style) {

		// 获取密钥
		Config.ACCESS_KEY = cloudImg.getACCESS_KEY();
		Config.SECRET_KEY = cloudImg.getSECRET_KEY();
		Mac mac = new Mac(Config.ACCESS_KEY, Config.SECRET_KEY);
		// 请确保该bucket已经存在
		String bucketName = cloudImg.getBucketName();
		PutPolicy putPolicy = new PutPolicy(bucketName);
		// 生成uptoken
		String uptoken = null;
		try {
			uptoken = putPolicy.token(mac);
		} catch (AuthException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (org.json.JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PutExtra extra = new PutExtra();
		// key=存在云端的文件名，prefix为文件的前缀,加时间戳这样可以上传同名图片
		String key = "";
		try {
			key = URLEncoder.encode(cloudImg.getPrefix(), "UTF-8")
					+ file.getOriginalFilename() + System.currentTimeMillis();
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PutRet ret = null;
		try {
			ret = IoApi.Put(uptoken, key, file.getInputStream(), extra);
		} catch (Exception e) {
			LOGGER.error("上传到七牛失败", e);
		}
		if (ret.ok()) {
			return cloudImg.getImgPath() + key + style;
		} else {
			return null;
		}
	}
}

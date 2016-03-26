package com.mark.backend.service;


import org.springframework.web.multipart.MultipartFile;

public interface ICloudUploadService {
	/**
	 * 上传到七牛云
	 * 
	 * @param file
	 * @param style
	 * @return
	 */
	String upload(MultipartFile file, String style);

}

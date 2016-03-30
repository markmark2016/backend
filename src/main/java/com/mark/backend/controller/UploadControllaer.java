package com.mark.backend.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mark.backend.service.ICloudUploadService;

@Controller
@RequestMapping(value = "/upload")
public class UploadControllaer {

	@Resource
	private ICloudUploadService uploadService;

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody
	Object upload(MultipartFile pictureUrl) {
		Map<String, Object> map = new HashMap<String, Object>();
		String picUrl = "";
		if (pictureUrl != null) {
			picUrl = uploadService.upload(pictureUrl, "");
		}
		if (picUrl != null) {
			map.put("status", "true");
			map.put("pictureUrl", picUrl);
		} else {
			map.put("status", "false");
		}
		return map;
	}
}

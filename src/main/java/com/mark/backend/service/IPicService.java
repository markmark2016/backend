package com.mark.backend.service;

import java.util.Map;

public interface IPicService {
	/**
	 * 根据参数存储图片信息
	 * 
	 * @param params
	 * @return
	 */
	Map<String, Object> savePic(Map<String, Object> params);

	/**
	 * 根据idfk获得图片url，图片url，格式的字符串
	 * 
	 * @param idFk
	 * @return
	 */
	String getPicByIdFk(Long idFk, String type);

}

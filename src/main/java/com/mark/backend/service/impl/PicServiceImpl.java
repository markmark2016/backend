package com.mark.backend.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mark.backend.mysql.mapper.PictureMapper;
import com.mark.backend.mysql.po.Picture;
import com.mark.backend.mysql.po.PictureExample;
import com.mark.backend.service.IPicService;

@Service
public class PicServiceImpl implements IPicService {

	@Resource
	private PictureMapper picMapper;

	@Override
	public Map<String, Object> savePic(Map<String, Object> params) {
		String type = params.get("type").toString();
		Long idFk = (Long) params.get("idFk");
		String[] picArray = (String[]) params.get("picArray");
		for (String url : picArray) {
			Picture pic = new Picture();
			pic.setCreateTime(new Date());
			pic.setUpdateTime(pic.getCreateTime());
			pic.setIdFk(idFk);
			pic.setType(type);
			pic.setUrl(url);
			picMapper.insert(pic);
		}
		return params;
	}

	@Override
	public String getPicByIdFk(Long idFk, String type) {
		// url,url,url,的格式
		String pictureUrl = "";
		PictureExample ex = new PictureExample();
		ex.createCriteria().andIdFkEqualTo(idFk).andTypeEqualTo(type);
		List<Picture> picList = picMapper.selectByExample(ex);
		int i = 0;
		for (Picture picture : picList) {
			if (i == 0) {
				pictureUrl += picture.getUrl();
				i++;
			} else {
				pictureUrl += ("," + picture.getUrl());
				i++;
			}

		}
		return pictureUrl;
	}
}

package com.mark.backend.service;

import java.util.List;

import com.mark.backend.dto.RemarkDto;
import com.mark.backend.mysql.po.Remark;

public interface IRemarkService {
	/**
	 * 获得用户打卡页列表
	 * 
	 * @param userId
	 * @return
	 */
	public List<RemarkDto> getPunchList(Long userId);

	/**
	 * 插入新书评
	 * 
	 * @param remark
	 * @return
	 */
	public Long createRemark(Remark remark);

}

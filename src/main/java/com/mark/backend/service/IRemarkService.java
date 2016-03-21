package com.mark.backend.service;

import java.util.List;
import java.util.Map;

import com.mark.backend.dto.RemarkDto;
import com.mark.backend.mysql.po.RemarkWithBLOBs;

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
	public Long createRemark(RemarkWithBLOBs remark);

	/**
	 * 
	 * @param userId
	 * @param groupId
	 * @return
	 */
	public Map<String, Object> getUserInGroupTodayRemark(Long userId, Long groupId);

}

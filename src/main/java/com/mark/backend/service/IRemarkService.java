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
	public Map<String, Object> getUserInGroupTodayRemark(Long userId,
			Long groupId);

	/**
	 * 获得小组评论列表：最热和按时间
	 * 
	 * @param groupId
	 * @return
	 */
	public Map<String, Object> getGroupRemark(Long groupId);

	/**
	 * 获得书评详细内容
	 * 
	 * @param remarkId
	 * @return
	 */
	public Map<String, Object> getRemarkById(Long remarkId);

	/**
	 * 用户点赞书评
	 * 
	 * @param remarkId
	 * @param userId
	 * @return
	 */
	public Integer InteractWithRemark(Long remarkId, Long userId,
			String content, String type);

}

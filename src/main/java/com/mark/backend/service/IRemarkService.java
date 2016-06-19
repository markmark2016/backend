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
	public Long createRemark(RemarkWithBLOBs remark, String picUrl);

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
			Long authorId, String content, String type);

	/**
	 * 获得连续打卡信息
	 * 
	 * @param params
	 * @return
	 */
	public Map<String, Object> getContinuePunchInfo(Map<String, Object> params);

	/**
	 * 获取某个用户指定时间段内的连续打卡次数
	 * @param params
	 * @return
     */
	int getContinuePunchInfoBetween(Map<String, Object> params);


	List<java.util.Date> getNotPunchDates(Map<String, Object> params);

	/**
	 * 获得用户的书评列表
	 * 
	 * @param params
	 * @return
	 */
	public Map<String, Object> getUserRemarkList(Map<String, Object> params);

	public Integer updateRemark(RemarkWithBLOBs remark, String pictureUrl);
}

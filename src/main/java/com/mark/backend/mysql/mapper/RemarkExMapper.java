package com.mark.backend.mysql.mapper;

import java.util.List;
import java.util.Map;

import com.mark.backend.dto.RemarkDto;
import com.mark.backend.mysql.po.Remark;

public interface RemarkExMapper {
	/**
	 * 获得用户打卡页列表
	 * 
	 * @param userId
	 * @return
	 */
	public List<RemarkDto> getUserPunchList(Long userId);

	/**
	 * 获得小组最近评论
	 * 
	 * @param groupId
	 * @return
	 */
	public List<RemarkDto> getGroupRemarkListRecentlyList(Long groupId);

	/**
	 * 获得小组中最热门的评论
	 * 
	 * @param groupId
	 * @return
	 */
	public List<RemarkDto> getGroupRemarkHotList(Long groupId);

	/**
	 * 获得评论的id-like列表 和id-reply 列表
	 * 
	 * @param params
	 * @return
	 */
	public List<RemarkDto> getGroupRemarkInteractList(Map<String, Object> params);

	/**
	 * 获得用户在每个小组的书评信息
	 * 
	 * @param userId
	 * @return
	 */
	public List<RemarkDto> getRemarkInGroupkByUserId(Long userId);

	/**
	 * 获得用户在该小组的所有书评信息
	 * 
	 * @param userId
	 * @param groupId
	 * @return
	 */
	public List<RemarkDto> getUserInGroupRemarkList(Map<String, Object> params);

	/**
	 * 获得连续打卡天数，以及连续打卡列表
	 */
	public List<Remark> getContinuePunch(Map<String, Object> params);

	public List<RemarkDto> getPunchList(Map<String, Object> params);

	public Integer getLastReadPage(Map<String, Object> params);
}

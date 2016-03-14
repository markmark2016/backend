package com.mark.backend.service;

import java.util.List;

import com.mark.backend.vo.GroupVO;

/**
 * 小组service
 * 
 * @Title:IGroupService
 * @Description:TODO
 * @author YangTianxiao
 * @date 2016年3月14日
 * 
 */
public interface IGroupService {
	List<GroupVO> getAllGroup();
}

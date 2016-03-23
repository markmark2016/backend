package com.mark.backend.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mark.backend.mysql.mapper.RemarkInteractMapper;
import com.mark.backend.mysql.mapper.userMessageMapper;
import com.mark.backend.mysql.po.userMessage;
import com.mark.backend.utils.MarkUtils;

@Service
public class MessageService {

	@Resource
	private RemarkInteractMapper riMapper;
	@Resource
	private userMessageMapper userMessageMapper;

	public Integer getUnreadedMessageCount(Map<String, Object> params) {
		return null;
	}

	/**
	 * 插入新消息
	 * 
	 * @param params
	 * @return
	 */
	public Integer insertNewMessage(Map<String, Object> params) {
		String type = params.get("type").toString();
		String title = params.get("title").toString();
		Long interactId = (Long) params.get("interactId");
		Long toUserId = (Long) params.get("toUserId");
		// Long fromUserId = (Long) params.get("fromUserId");
		// User fromUser = WeixinService.userMap.get(fromUserId);
		String content = "";
		if ("1".equals(type)) {
			content = "评论了你的书评" + title;
		} else {
			content = "攒了你的书评" + title;
		}
		userMessage um = new userMessage();
		um.setCreateTime(MarkUtils.getCurrentTime());
		um.setUpdateTime(um.getCreateTime());
		um.setUserIdFk(toUserId);
		um.setInteractIdFk(interactId);
		um.setType(type);
		um.setContent(content);
		um.setIsCheck("0");
		return userMessageMapper.insert(um);
	}
}

package com.mark.backend.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.mark.backend.dto.MessageDto;
import com.mark.backend.dto.RemarkDto;
import com.mark.backend.mysql.mapper.RemarkInteractMapper;
import com.mark.backend.mysql.mapper.UserMessageExMapper;
import com.mark.backend.mysql.mapper.UserMessageMapper;
import com.mark.backend.mysql.po.User;
import com.mark.backend.mysql.po.UserMessage;
import com.mark.backend.utils.MarkUtils;

@Service
public class MessageService {

	@Resource
	private RemarkInteractMapper riMapper;
	@Resource
	private UserMessageMapper userMessageMapper;
	@Resource
	private UserMessageExMapper umexMapper;

	ScheduledExecutorService executor = Executors
			.newSingleThreadScheduledExecutor();

	/**
	 * 获得总未读信息
	 * 
	 * @param params
	 * @return
	 */
	public Map<String, Object> getUnreadedMessageCount(
			Map<String, Object> params) {
		// 未读系统信息
		Map<String, Object> sysMsgMap = getUnreadSysMap(params);
		// 未读回复信息
		Map<String, Object> replyMsgMap = getUnreadReplyMap(params);
		// 未读赞信息
		Map<String, Object> likeMsgMap = getUnreadLikeMap(params);
		// 未读总数
		Integer unreadCount = (Integer) sysMsgMap.get("unreadsyscount")
				+ (Integer) replyMsgMap.get("unreadreplycount")
				+ (Integer) likeMsgMap.get("unreadlikecount");
		params.put("unreadcount", unreadCount);
		return params;
	}

	/**
	 * 获得未读的回复列表
	 * 
	 * @param params
	 * @return
	 */
	public Map<String, Object> getUnreadReplyMap(Map<String, Object> params) {
		final Long userId = (Long) params.get("userId");
		params.put("type", "1");
		params.put("ischeck", "0");
		List<MessageDto> replyMsgList = umexMapper.getMessageList(params);
		for (MessageDto messageDto : replyMsgList) {
			User user = WeixinService.userMap.get(messageDto.getUserId());
			messageDto.setUserName(user.getNickname());
			messageDto.setHeadImage(user.getHeadImgUrl());
		}
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("unreadreplylist", replyMsgList);
		resultMap.put("unreadreplycount", replyMsgList.size());

		umexMapper.updateUserReplyMessage(userId);

		// executor.schedule(new Runnable() {
		// @Override
		// public void run() {
		// UserMessageExample ex = new UserMessageExample();
		// ex.createCriteria().andUserIdFkEqualTo(userId)
		// .andTypeEqualTo("1");
		// UserMessage um = new UserMessage();
		// um.setIsCheck("1");
		// userMessageMapper.updateByExampleSelective(um, ex);
		// }
		// }, 3, TimeUnit.SECONDS);
		return resultMap;
	}

	/**
	 * 获得未读的赞列表
	 * 
	 * @param params
	 * @return
	 */
	public Map<String, Object> getUnreadLikeMap(Map<String, Object> params) {
		final Long userId = (Long) params.get("userId");

		params.put("type", "2");
		params.put("ischeck", 0);
		List<MessageDto> likeMsgList = umexMapper.getMessageList(params);
		for (MessageDto messageDto : likeMsgList) {
			User user = WeixinService.userMap.get(messageDto.getUserId());
			messageDto.setUserName(user.getNickname());
			messageDto.setHeadImage(user.getHeadImgUrl());
		}
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("unreadlikelist", likeMsgList);
		resultMap.put("unreadlikecount", likeMsgList.size());

		umexMapper.updateUserLikeMessage(userId);

		// executor.schedule(new Runnable() {
		// @Override
		// public void run() {
		// UserMessageExample ex = new UserMessageExample();
		// ex.createCriteria().andUserIdFkEqualTo(userId)
		// .andTypeEqualTo("2");
		// UserMessage um = new UserMessage();
		// um.setIsCheck("1");
		// userMessageMapper.updateByExampleSelective(um, ex);
		// }
		// }, 3, TimeUnit.SECONDS);
		return resultMap;
	}

	/**
	 * 获得未读的系统回复列表
	 * 
	 * @param params
	 * @return
	 */
	public Map<String, Object> getUnreadSysMap(Map<String, Object> params) {
		// Long userId = (Long) params.get("userId");
		params.put("type", "3");
		params.put("ischeck", "0");
		List<MessageDto> sysMsgList = umexMapper.getMessageList(params);
		for (MessageDto messageDto : sysMsgList) {
			User user = WeixinService.userMap.get(messageDto.getUserId());
			messageDto.setUserName(user.getNickname());
			messageDto.setHeadImage(user.getHeadImgUrl());
		}
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("unreadsyslist", sysMsgList);
		resultMap.put("unreadsyscount", sysMsgList.size());
		return resultMap;
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
			if (StringUtils.isEmpty(title)) {
				content = "评论了你的书评";
			} else {
				content = "评论了你的书评《" + title + "》";
			}
		} else {
			if (StringUtils.isEmpty(title)) {
				content = "赞了你的书评";
			} else {
				content = "赞了你的书评《" + title + "》";
			}
		}
		UserMessage um = new UserMessage();
		um.setCreateTime(MarkUtils.getCurrentTime());
		um.setUpdateTime(um.getCreateTime());
		um.setUserIdFk(toUserId);
		um.setInteractIdFk(interactId);
		um.setType(type);
		um.setContent(content);
		um.setIsCheck("0");
		return userMessageMapper.insert(um);
	}

	public Map<String, Object> getMsgIndexInfo(Map<String, Object> params) {
		// 未读系统信息
		Map<String, Object> sysMsgMap = getUnreadSysMap(params);
		// // 未读回复信息
		// Map<String, Object> replyMsgMap = getUnreadReplyMap(params);
		// // 未读赞信息
		// Map<String, Object> likeMsgMap = getUnreadLikeMap(params);
		Long userId = (Long) params.get("userId");
		RemarkDto dto = umexMapper.getCount(userId);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("unreadsyscount", sysMsgMap.get("unreadsyscount"));
		resultMap.put("unreadreplycount", dto.getTotalReply());
		resultMap.put("unreadlikecount", dto.getTotalLike());
		return resultMap;
	}
}

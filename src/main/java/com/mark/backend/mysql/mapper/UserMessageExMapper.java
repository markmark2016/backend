package com.mark.backend.mysql.mapper;

import java.util.List;
import java.util.Map;

import com.mark.backend.dto.MessageDto;

public interface UserMessageExMapper {
	/**
	 * 获得消息列表
	 * 
	 * @param params
	 * @return
	 */
	List<MessageDto> getMessageList(Map<String, Object> params);
}

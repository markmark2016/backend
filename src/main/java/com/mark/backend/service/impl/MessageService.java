package com.mark.backend.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mark.backend.mysql.mapper.RemarkInteractMapper;

@Service
public class MessageService {

	@Resource
	private RemarkInteractMapper riMapper;

	public Integer getUnreadedMessageCount(Map<String, Object> params) {
		return null;
	}

}

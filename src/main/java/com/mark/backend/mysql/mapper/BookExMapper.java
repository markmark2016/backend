package com.mark.backend.mysql.mapper;

import java.util.List;
import java.util.Map;

import com.mark.backend.dto.BookDto;

public interface BookExMapper {
	/**
	 * 得到用户喜欢看和想看的书列表
	 * 
	 * @param id
	 * @return
	 */
	List<BookDto> queryUserLikeAndWantList(Map<String, Object> params);
}

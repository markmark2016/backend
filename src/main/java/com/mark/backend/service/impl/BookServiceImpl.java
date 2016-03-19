package com.mark.backend.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mark.backend.dto.BookDto;
import com.mark.backend.mysql.mapper.BookExMapper;
import com.mark.backend.mysql.mapper.BookMapper;
import com.mark.backend.mysql.po.Book;
import com.mark.backend.mysql.po.BookExample;
import com.mark.backend.service.IBookService;

@Service
public class BookServiceImpl implements IBookService {

	@Resource
	private BookMapper bookMapper;
	@Resource
	private BookExMapper bookExMapper;

	@Override
	public List<Book> getBookList() {
		BookExample bex = new BookExample();
		// bex.createCriteria().andStatusNotEqualTo("0");
		List<Book> bookList = bookMapper.selectByExample(bex);
		return bookList;
	}

	@Override
	public Map<String, Object> getUserBookList(Map<String, Object> params) {
		List<BookDto> dtoList = bookExMapper.queryUserLikeAndWantList(params);
		List<BookDto> likeList = new ArrayList<BookDto>();
		List<BookDto> wantList = new ArrayList<BookDto>();
		for (BookDto bookDto : dtoList) {
			if ("1".equals(bookDto.getType())) {
				likeList.add(bookDto);
			} else {
				wantList.add(bookDto);
			}
		}
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("likeList", likeList);
		resultMap.put("wantList", wantList);
		return resultMap;
	}
}

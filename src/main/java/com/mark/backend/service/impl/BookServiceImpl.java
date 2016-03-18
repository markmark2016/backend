package com.mark.backend.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mark.backend.mysql.mapper.BookMapper;
import com.mark.backend.mysql.po.Book;
import com.mark.backend.mysql.po.BookExample;
import com.mark.backend.service.IBookService;

@Service
public class BookServiceImpl implements IBookService {

	@Resource
	private BookMapper bookMapper;

	@Override
	public List<Book> getBookList() {
		BookExample bex = new BookExample();
		// bex.createCriteria().andStatusNotEqualTo("0");
		List<Book> bookList = bookMapper.selectByExample(bex);
		return bookList;
	}

}

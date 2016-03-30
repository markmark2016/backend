package com.mark.backend.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.mark.backend.dto.BookDto;
import com.mark.backend.mysql.mapper.BookExMapper;
import com.mark.backend.mysql.mapper.BookMapper;
import com.mark.backend.mysql.po.Book;
import com.mark.backend.mysql.po.BookExample;
import com.mark.backend.service.IBookService;
import com.mark.backend.utils.MarkUtils;

@Service
public class BookServiceImpl implements IBookService {

	@Resource
	private BookMapper bookMapper;
	@Resource
	private BookExMapper bookExMapper;

	@Override
	public List<Book> getBookList(String bookName) {
		BookExample bex = new BookExample();
		if (!StringUtils.isEmpty(bookName)) {
			bex.createCriteria().andTitleLike("%" + bookName + "%");
		}
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

	@Override
	public Book getBookById(Long bookId) {
		BookExample ex = new BookExample();
		ex.createCriteria().andIdEqualTo(bookId);
		Book book = bookMapper.selectByPrimaryKey(bookId);
		return book;
	}

	@Override
	public Long saveBook(Book book) {
		Long i = 0L;
		if (book.getId() != null) {
			book.setUpdateTime(MarkUtils.getCurrentTime());
			i = (long) bookMapper.updateByPrimaryKeySelective(book);
		} else {
			book.setCreateTime(MarkUtils.getCurrentTime());
			book.setUpdateTime(book.getCreateTime());
			bookMapper.insert(book);
			i = book.getId();
		}
		return i;
	}

	@Override
	public Integer deleteByBookId(Long bookId) {
		int i = bookMapper.deleteByPrimaryKey(bookId);
		return i;
	}
}

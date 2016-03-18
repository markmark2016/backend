package com.mark.backend.service;

import java.util.List;

import com.mark.backend.mysql.po.Book;

/**
 * 书籍service
 * 
 * @Title:IBookService
 * @Description:TODO
 * @author YangTianxiao
 * @date 2016年3月14日
 * 
 */
public interface IBookService {
	List<Book> getBookList();
}

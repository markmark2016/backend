package com.mark.backend.service;

import java.util.List;
import java.util.Map;

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
	/**
	 * 获取图书列表，可按照name模糊查询
	 * 
	 * @param bookName
	 * @return
	 */
	List<Book> getBookList(String bookName);

	/**
	 * 得到用户最喜欢和最想看的图书列表
	 * 
	 * @param openId
	 * @return
	 */
	Map<String, Object> getUserBookList(Map<String, Object> params);

	/**
	 * 按照id取得图书信息
	 * 
	 * @param bookId
	 * @return
	 */
	Book getBookById(Long bookId);
	/**
	 * 存储book
	 * @param book
	 * @return
	 */
	Integer saveBook(Book book);
}

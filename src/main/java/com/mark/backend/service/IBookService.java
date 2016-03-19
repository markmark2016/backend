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
	List<Book> getBookList();

	/**
	 * 得到用户最喜欢和最想看的图书列表
	 * 
	 * @param openId
	 * @return
	 */
	Map<String, Object> getUserBookList(Map<String, Object> params);
}

package com.mark.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mark.backend.mysql.po.Remark;

@Controller
@RequestMapping(value = "/punch")
public class PunchController {
	/**
	 * 打卡页列表
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	Object punchIndex(@RequestParam(required = true) String openId) {
		return null;
	}

	/**
	 * 提交书评
	 * 
	 * @param remark
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody
	Object punch(@RequestParam(required = true) Remark remark) {
		return null;
	}

	/**
	 * 读完了
	 * 
	 * @param remark
	 * @return
	 */
	@RequestMapping(value = "/readed", method = RequestMethod.POST)
	public @ResponseBody
	Object readed(@RequestParam(required = true) Remark remark) {
		return null;
	}

}

package com.mark.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mark.backend.model.CheckModel;
import com.mark.backend.utils.MarkUtils;

@Controller
@RequestMapping(value = "/wechat")
public class WechatController {

	/**
	 * 微信验证
	 * 
	 * @param checkModel
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/check", method = RequestMethod.GET)
	public @ResponseBody
	String validate(CheckModel checkModel, Model model) {
		String result = MarkUtils.validateWechatInfo(checkModel);
		return result;
	}

}

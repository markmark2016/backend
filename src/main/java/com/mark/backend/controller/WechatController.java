package com.mark.backend.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mark.backend.model.CheckModel;
import com.mark.backend.service.impl.WeixinService;
import com.mark.backend.utils.MarkUtils;

@Controller
@RequestMapping(value = "/wechat")
public class WechatController {
    private final static Logger LOGGER = LoggerFactory
            .getLogger(WechatController.class);
    @Resource
    private WeixinService wxService;

    /**
     * 微信验证
     *
     * @param checkModel
     * @param model
     *
     * @return
     */
    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public
    @ResponseBody
    String validate(CheckModel checkModel, Model model) {
        String result = MarkUtils.validateWechatInfo(checkModel);
        System.out.println(result.equals(checkModel.getEchostr()));
        return result;
    }

    @RequestMapping(value = "/authorize", method = RequestMethod.GET)
    public Object authorize(@RequestParam(required = true) String code,
                            @RequestParam(required = false) String state,
                            HttpServletRequest request, HttpServletResponse response)
            throws UnsupportedEncodingException {

        System.out.println("Authorizing, code:" + code + ", state:" + state);

        Object userIdObj = wxService.getUserInfo(code, state);

        System.out.println("Authorizing userId:" + userIdObj);

        String toUrl = URLDecoder.decode(state, "UTF-8");

        // 获取用户信息失败,定向到用户页面并让他关注公众号
        if (userIdObj == null) {
            try {
                System.out.println("Not Found the userId!");
                response.sendRedirect("http://sub.markeveryday.com/app/#" + toUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        } else {
            String userId = userIdObj.toString();

            LOGGER.info("用户:" + userId + "登录了");
            System.out.println("Authorized, userId:" + userId);

            Cookie c = new Cookie("userId", userId);
            //c.setDomain("*");
            //c.setPath("/");
            response.addCookie(c);
            response.setHeader("markUserId", userId);

            try {
                response.sendRedirect("http://sub.markeveryday.com/app/" + "?userId="
                        + userId + "#" + toUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

    }

    @RequestMapping(value = "/js", method = RequestMethod.GET)
    public
    @ResponseBody
    Object validate(String url, Model model) {

        return MarkUtils.createReply(url);
    }

    @RequestMapping(value = "/redirect", method = RequestMethod.GET)
    public Object redirect(String url, Model model) {

        return null;
    }

}

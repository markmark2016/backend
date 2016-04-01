package com.mark.backend.model;

import java.util.Map;

/**
 * 微信模板对象
 * 
 * @Title:WxTemplate
 * @Description:TODO
 * @author YangTianxiao
 * @date 2016年4月1日
 * 
 */
public class WxTemplate {
	/**
	 * 模板ID
	 */
	private String template_id;
	/**
	 * 发送到某一个用户
	 */
	private String touser;
	/**
	 * 点击url
	 */
	private String url;
	/**
	 * 颜色
	 */
	private String topcolor;
	/**
	 * 数据包
	 */
	private Map<String, TemplateData> data;

	public String getTemplate_id() {
		return template_id;
	}

	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}

	public String getTouser() {
		return touser;
	}

	public void setTouser(String touser) {
		this.touser = touser;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTopcolor() {
		return topcolor;
	}

	public void setTopcolor(String topcolor) {
		this.topcolor = topcolor;
	}

	public Map<String, TemplateData> getData() {
		return data;
	}

	public void setData(Map<String, TemplateData> data) {
		this.data = data;
	}

}

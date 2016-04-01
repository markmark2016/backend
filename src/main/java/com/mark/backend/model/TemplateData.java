package com.mark.backend.model;

/**
 * 模板数据
 * 
 * @Title:TemplateData
 * @Description:TODO
 * @author YangTianxiao
 * @date 2016年4月1日
 * 
 */
public class TemplateData {
	/**
	 * 值
	 */
	private String value;
	/**
	 * 颜色
	 */
	private String color = "#173177";

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}

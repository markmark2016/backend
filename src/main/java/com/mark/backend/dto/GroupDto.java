package com.mark.backend.dto;

/**
 * 小组显示相关
 * 
 * @Title:GroupDto
 * @Description:TODO
 * @author YangTianxiao
 * @date 2016年3月15日
 * 
 */
public class GroupDto {
	private Long id;
	private String groupName;
	private String bookName;
	private String groupDesc;
	private String groupImage;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getGroupDesc() {
		return groupDesc;
	}

	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}

	public String getGroupImage() {
		return groupImage;
	}

	public void setGroupImage(String groupImage) {
		this.groupImage = groupImage;
	}

}

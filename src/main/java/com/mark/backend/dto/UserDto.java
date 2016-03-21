package com.mark.backend.dto;

/**
 * 用户丰富传输对象
 * 
 * @Title:UserDto
 * @Description:TODO
 * @author YangTianxiao
 * @date 2016年3月16日
 * 
 */
public class UserDto {
	/**
	 * 排名
	 */
	private Integer num;
	/**
	 * 用户id
	 */
	private Long id;
	/**
	 * 微信openId
	 */
	private String openid;
	/**
	 * 昵称
	 */
	private String nickName;
	/**
	 * 头像地址
	 */
	private String headImage;
	/**
	 * 简介
	 */
	private String intro;
	/**
	 * 加入小组数
	 */
	private String totalGroup;
	/**
	 * 全部书评数
	 */
	private Integer totalRemark;
	/**
	 * 累计读书数
	 */
	private Integer totalRead;
	/**
	 * 
	 */
	private Integer totalPunch;
	/**
	 * 是否是领读人
	 */
	private boolean isLeader;
	/**
	 * 积分
	 */
	private Integer score;

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getTotalGroup() {
		return totalGroup;
	}

	public void setTotalGroup(String totalGroup) {
		this.totalGroup = totalGroup;
	}

	public Integer getTotalRemark() {
		return totalRemark;
	}

	public void setTotalRemark(Integer totalRemark) {
		this.totalRemark = totalRemark;
	}

	public Integer getTotalRead() {
		return totalRead;
	}

	public void setTotalRead(Integer totalRead) {
		this.totalRead = totalRead;
	}

	public Integer getTotalPunch() {
		return totalPunch;
	}

	public void setTotalPunch(Integer totalPunch) {
		this.totalPunch = totalPunch;
	}

	public boolean isLeader() {
		return isLeader;
	}

	public void setLeader(boolean isLeader) {
		this.isLeader = isLeader;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

}

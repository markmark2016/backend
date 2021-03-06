package com.mark.backend.worker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.alibaba.fastjson.JSONObject;
import com.mark.backend.dto.RemarkDto;
import com.mark.backend.model.TemplateData;
import com.mark.backend.model.WxTemplate;
import com.mark.backend.mysql.mapper.GroupMapper;
import com.mark.backend.mysql.mapper.GroupUserMapper;
import com.mark.backend.mysql.mapper.RemarkMapper;
import com.mark.backend.mysql.mapper.UserMapper;
import com.mark.backend.mysql.po.Group;
import com.mark.backend.mysql.po.GroupExample;
import com.mark.backend.mysql.po.GroupUser;
import com.mark.backend.mysql.po.GroupUserExample;
import com.mark.backend.mysql.po.Remark;
import com.mark.backend.mysql.po.RemarkExample;
import com.mark.backend.mysql.po.User;
import com.mark.backend.mysql.po.UserExample;
import com.mark.backend.service.impl.RemarkServiceImpl;
import com.mark.backend.service.impl.WeixinService;
import com.mark.backend.utils.Constans;
import com.mark.backend.utils.MarkUtils;

public class PunchNoticeWorker {
	@Resource
	private RemarkServiceImpl remarkService;
	@Resource
	private GroupMapper groupMapper;
	@Resource
	private RemarkMapper remarkMapper;
	@Resource
	private GroupUserMapper guMapper;
	@Resource
	private UserMapper	 userMapper;

	/**
	 * 发送打卡提醒
	 */
	public void sendPunchAlert() {
//		Set<Long> set = WeixinService.userMap.keySet();
		
		UserExample ex = new UserExample();
		ex.createCriteria();
		Set<Long> set = new HashSet<Long>();
		List<User> userList = userMapper.selectByExample(ex);
		for (User user : userList) {
			set.add(user.getId());
		}
		
		for (Long userId : set) {
			List<RemarkDto> punchList = remarkService.getPunchList(userId);
			sendAlertToUser(userId, punchList);
		}
	}

	/**
	 * 给用户发送打卡提醒
	 * 
	 * @param userId
	 * @param punchList
	 */
	public void sendAlertToUser(Long userId, List<RemarkDto> punchList) {
		String url = Constans.TEMPLEATE_URL + WeixinService.access_token;
		CloseableHttpClient httpClient = HttpClients.createDefault();
		@SuppressWarnings("unused")
		CloseableHttpResponse response = null;
		int i = 0;
		StringBuilder sb = new StringBuilder();
		for (RemarkDto dto : punchList) {
			if (!dto.getIsPunch()) {
				i++;
				sb.append(dto.getGroupName() + " ");
			}
		}
		if (i > 0) {
			HttpPost post = new HttpPost(url);
			Map<String, TemplateData> data = new HashMap<String, TemplateData>();
			String punchUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxdac023cef2cea008&redirect_uri=http%3a%2f%2fwww.swanhi.com%2fmark-backend%2fwechat%2fauthorize&response_type=code&scope=snsapi_base&state=%2ftab%2fpunch-center#wechat_redirect";
			WxTemplate wxt = new WxTemplate();
			wxt.setUrl(punchUrl);
			wxt.setTemplate_id(Constans.TEMPLEATE_NOTIFICE_ID);
			wxt.setTouser(WeixinService.userMap.get(userId).getOpenid());

			// 设置map中的数据，根据模板参数配置而看
			TemplateData first = new TemplateData();
			first.setValue("您好，亲爱的MarkMark成员，您今日仍有" + i + "个小组未打卡哦~~");
			TemplateData keynote1 = new TemplateData();
			keynote1.setValue(sb.toString());
			TemplateData keynote2 = new TemplateData();
			keynote2.setValue(MarkUtils.formatDate("yy-MM-dd",
					MarkUtils.getZeroTime()));
			TemplateData remark = new TemplateData();
			remark.setValue("点击浏览当日作业。");
			data.put("first", first);
			data.put("keynote1", keynote1);
			data.put("keynote2", keynote2);
			data.put("remark", remark);
			wxt.setData(data);
			String jsonStr = JSONObject.toJSONString(wxt);
			StringEntity entity = new StringEntity(jsonStr, "utf-8");
			entity.setContentEncoding("UTF-8");
			entity.setContentType("application/json");
			post.setEntity(entity);
			try {
				response = httpClient.execute(post);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 想组长发送当天组员打卡信息
	 */
	public void sendPunchDetail() {
		Date startDate = MarkUtils.getYestarday();
		Date endDate = MarkUtils.getZeroTime();
		GroupExample ex = new GroupExample();
		ex.createCriteria().andStatusEqualTo("3");
		List<Group> groupList = groupMapper.selectByExample(ex);
		for (Group group : groupList) {
			sendDetailToCaptaion(group, startDate, endDate);
		}
	}

	public void sendDetailToCaptaion(Group group, Date startDate, Date endDate) {
		RemarkExample rex = new RemarkExample();
		rex.createCriteria().andGroupIdFkEqualTo(group.getId())
				.andCreateTimeLessThan(endDate)
				.andCreateTimeGreaterThanOrEqualTo(startDate);
		// 当天的打卡记录
		List<Remark> rList = remarkMapper.selectByExample(rex);
		// 打卡的id列表
		List<Long> punchUserList = new ArrayList<Long>();
		for (Remark r : rList) {
			punchUserList.add(r.getUserIdFk());
		}
		
		
		
		GroupUserExample guex = new GroupUserExample();
		if (punchUserList.size() > 0) {
			guex.createCriteria().andGroupIdFkEqualTo(group.getId())
					.andUserStatusEqualTo("1").andUserIdFkNotIn(punchUserList);
		}else{
			guex.createCriteria().andGroupIdFkEqualTo(group.getId())
			.andUserStatusEqualTo("1");
		}

		List<GroupUser> guList = guMapper.selectByExample(guex);
		StringBuilder sb = new StringBuilder();
		for (GroupUser groupUser : guList) {
			sb.append(WeixinService.userMap.get(groupUser.getUserIdFk())
					.getNickname() + "、");
		}
		/*---------------------------------------------------------------------------------------------*/
		String url = Constans.TEMPLEATE_URL + WeixinService.access_token;
		CloseableHttpClient httpClient = HttpClients.createDefault();
		@SuppressWarnings("unused")
		CloseableHttpResponse response = null;
		HttpPost post = new HttpPost(url);
		Map<String, TemplateData> data = new HashMap<String, TemplateData>();
		WxTemplate wxt = new WxTemplate();
		wxt.setTemplate_id(Constans.TEMPLEATE_NOTIFICE_ID);
		wxt.setTouser(WeixinService.userMap.get(group.getUserIdFk())
				.getOpenid());
		// 设置map中的数据，根据模板参数配置而看
		TemplateData first = new TemplateData();
		first.setValue("您好，亲爱的组长，这是" + group.getGroupName() + "当日的打卡情况");
		TemplateData keynote1 = new TemplateData();
		keynote1.setValue(group.getGroupName());
		TemplateData keynote2 = new TemplateData();
		keynote2.setValue(MarkUtils.formatDate("yy-MM-dd",
				MarkUtils.getZeroTime()));
		TemplateData remark = new TemplateData();
		remark.setValue("打卡人数：" + rList.size() + "，未打卡人数：" + guList.size()
				+ "。" + sb.toString());
		data.put("first", first);
		data.put("keynote1", keynote1);
		data.put("keynote2", keynote2);
		data.put("remark", remark);
		wxt.setData(data);
		String jsonStr = JSONObject.toJSONString(wxt);
		StringEntity entity = new StringEntity(jsonStr, "utf-8");
		entity.setContentEncoding("UTF-8");
		entity.setContentType("application/json");
		post.setEntity(entity);
		try {
			response = httpClient.execute(post);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// public static void main(String[] args) {
	// Calendar calendar = Calendar.getInstance();
	// calendar.setTime(new Date());
	// int day = calendar.get(Calendar.DATE);
	// calendar.set(Calendar.HOUR_OF_DAY, 0);
	// calendar.set(Calendar.MINUTE, 0);
	// calendar.set(Calendar.SECOND, 0);
	// calendar.set(Calendar.MILLISECOND, 0);
	// calendar.set(Calendar.DATE, day-1);
	// System.out.println(calendar.getTime());
	// }
}

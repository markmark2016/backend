package com.mark.backend.worker;

import java.io.IOException;
import java.util.HashMap;
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
import com.mark.backend.service.impl.RemarkServiceImpl;
import com.mark.backend.service.impl.WeixinService;
import com.mark.backend.utils.Constans;
import com.mark.backend.utils.MarkUtils;

public class PunchNoticeWorker {
	@Resource
	private RemarkServiceImpl remarkService;

	/**
	 * 发送打卡提醒
	 */
	public void sendPunchAlert() {
		Set<Long> set = WeixinService.userMap.keySet();
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
			WxTemplate wxt = new WxTemplate();
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
}

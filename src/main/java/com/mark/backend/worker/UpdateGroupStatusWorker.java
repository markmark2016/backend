package com.mark.backend.worker;

import javax.annotation.Resource;

import com.mark.backend.mysql.mapper.GroupMapper;
import com.mark.backend.mysql.po.Group;
import com.mark.backend.mysql.po.GroupExample;
import com.mark.backend.utils.MarkUtils;

public class UpdateGroupStatusWorker {
	@Resource
	private GroupMapper groupMapper;

	public void updateGroupStatus() {
		GroupExample ex = new GroupExample();
		ex.createCriteria()
				.andEndTimeLessThanOrEqualTo(MarkUtils.getZeroTime());
		Group g = new Group();
		g.setStatus("1");
		groupMapper.updateByExampleSelective(g, ex);

		ex.clear();
		ex.createCriteria().andBeginTimeGreaterThanOrEqualTo(
				MarkUtils.getZeroTime());
		g.setStatus("2");
		groupMapper.updateByExampleSelective(g, ex);

		ex.clear();
		ex.createCriteria()
				.andBeginTimeLessThanOrEqualTo(MarkUtils.getZeroTime())
				.andEndTimeGreaterThanOrEqualTo(MarkUtils.getZeroTime());
		g.setStatus("3");
		groupMapper.updateByExampleSelective(g, ex);
	}

	public static void main(String[] args) {
		// updateGroupStatus();
	}
}

package com.mark.backend.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.mark.backend.dto.AssociationDto;
import com.mark.backend.dto.GroupDto;
import com.mark.backend.dto.RemarkDto;
import com.mark.backend.dto.UserDto;
import com.mark.backend.mysql.mapper.AssociationExMapper;
import com.mark.backend.mysql.mapper.BookMapper;
import com.mark.backend.mysql.mapper.GroupExMapper;
import com.mark.backend.mysql.mapper.GroupUserMapper;
import com.mark.backend.mysql.mapper.RemarkExMapper;
import com.mark.backend.mysql.mapper.ScoreExMapper;
import com.mark.backend.mysql.mapper.UserExMapper;
import com.mark.backend.mysql.mapper.UserMapper;
import com.mark.backend.mysql.mapper.userLikeMapper;
import com.mark.backend.mysql.po.Book;
import com.mark.backend.mysql.po.GroupUser;
import com.mark.backend.mysql.po.GroupUserExample;
import com.mark.backend.mysql.po.Remark;
import com.mark.backend.mysql.po.User;
import com.mark.backend.mysql.po.UserExample;
import com.mark.backend.mysql.po.userLike;
import com.mark.backend.mysql.po.userLikeExample;
import com.mark.backend.service.IRemarkService;
import com.mark.backend.service.IUserService;
import com.mark.backend.utils.Constans;
import com.mark.backend.utils.MarkUtils;

@Service
public class UserServiceImpl implements IUserService {

	@Resource
	private UserMapper userMapper;
	@Resource
	private UserExMapper uexMapper;
	@Resource
	private GroupExMapper gexMapper;
	@Resource
	private AssociationExMapper aexMapper;
	@Resource
	private ScoreExMapper sexMapper;
	@Resource
	private RemarkExMapper rexMapper;
	@Resource
	private IRemarkService remarkService;
	@Resource
	private userLikeMapper ulMapper;
	@Resource
	private BookMapper bookMapper;
	@Resource
	private GroupUserMapper guMapper;

	ExecutorService executor = Executors.newFixedThreadPool(2);

	@Override
	public User getUserByOpenId(String openId) {
		UserExample ue = new UserExample();
		ue.createCriteria().andOpenidEqualTo(openId);
		User u = userMapper.selectByExample(ue).get(0);
		return u;
	}

	@Override
	public int insertUser(User user) {
		return userMapper.insert(user);
	}

	@Override
	public List<UserDto> queryUserList(Map<String, Object> params) {
		List<UserDto> dtoList = uexMapper.queryUserList(params);
		return dtoList;
	}

	@Override
	public List<User> getUserList(String nickName) {
		UserExample ue = new UserExample();
		if (StringUtils.isEmpty(nickName)) {
			ue.createCriteria();
		} else {
			ue.createCriteria().andNicknameLike("%" + nickName + "%");
		}
		List<User> userList = userMapper.selectByExample(ue);
		return userList;
	}

	@Override
	public UserDto queryUserPageInfo(Map<String, Object> params) {
		UserDto dto = uexMapper.queryUserPageInfo(params);
		dto.setTotalPunch((Integer) remarkService.getContinuePunchInfo(params)
				.get("totalPunch"));
		return dto;
	}

	@Override
	public Integer updateUserDetailInfo(Long userId, User user) {
		UserExample ex = new UserExample();
		ex.createCriteria().andIdEqualTo(userId);
		user.setUpdateTime(MarkUtils.getCurrentTime());
		int i = userMapper.updateByExampleSelective(user, ex);
		final Long id = userId;
		executor.execute(new Runnable() {

			@Override
			public void run() {
				User user = userMapper.selectByPrimaryKey(id);
				WeixinService.userMap.put(id, user);
			}
		});
		return i;
	}

	@Override
	public Map<String, Object> getUserGroupDetail(Long userId) {
		List<GroupDto> gdtoList = gexMapper.getUserGroupList(userId);
		List<GroupDto> finalgdtoList = new ArrayList<GroupDto>();
		List<AssociationDto> assdtoList = new ArrayList<AssociationDto>();
		Map<String, Object> params = new HashMap<String, Object>();
		Map<Long, AssociationDto> assMap = new HashMap<Long, AssociationDto>();
		for (GroupDto dto : gdtoList) {
			if (dto.getAssociationId() != null) {
				params.put("associationId", dto.getAssociationId());
				AssociationDto adto = aexMapper.queryAssociationList(params)
						.get(0);
//				assdtoList.add(adto);
				assMap.put(adto.getId(), adto);
			}
		}

		Set<Long> assId = assMap.keySet();
		for (Long assid : assId) {
			for (GroupDto groupDto : gdtoList) {
				if (groupDto.getAssociationId() == assid) {
					assMap.get(assid).getGroupList().add(groupDto);
				}
			}
			assdtoList.add(assMap.get(assid));
		}

//		for (AssociationDto dto : assdtoList) {
//			for (GroupDto groupDto : gdtoList) {
//				if (groupDto.getAssociationId() == dto.getId()) {
//					dto.getGroupList().add(groupDto);
//				}
//			}
//		}
		for (GroupDto groupDto : gdtoList) {
			if (groupDto.getAssociationId() == null) {
				finalgdtoList.add(groupDto);
			}
		}
		params.clear();
		params.put("groupList", finalgdtoList);
		params.put("associationList", assdtoList);
		return params;
	}

	@Override
	public List<GroupDto> getUserReadedList(Long userId) {
		List<GroupDto> gdtoList = gexMapper.getUserGroupList(userId);
		List<GroupDto> finalgdtoList = new ArrayList<GroupDto>();
		for (GroupDto groupDto : gdtoList) {
			if (2 == groupDto.getUserStatus()) {
				finalgdtoList.add(groupDto);
			}
		}
		return finalgdtoList;
	}

	@Override
	public Integer getUserRank(Long userId) {
		Integer rank = sexMapper.getUserRank(userId);
		return rank;
	}

	@Override
	public Map<String, Object> getRankInfo(Long userId) {
		Integer rank = this.getUserRank(userId);
		List<UserDto> rankList = sexMapper.getAllScoreList();
		Map<String, Object> rankMap = this.getUserGroupRankInfo(userId);
		rankMap.put("rank", rank);
		rankMap.put("totalranklist", rankList);
		return rankMap;
	}

	@Override
	public Map<String, Object> getUserGroupScoreInfo(Long userId) {
		List<GroupDto> list = sexMapper.getUserGroupScore(userId);
		int totalScore = 0;
		for (GroupDto dto : list) {
			totalScore += dto.getScore();
		}
		Map<String, Object> scoreMap = new HashMap<String, Object>();
		scoreMap.put("totalScore", totalScore);
		scoreMap.put("scorelist", list);
		return scoreMap;
	}

	@Override
	public Map<String, Object> getUserGroupRankInfo(Long userId) {
		Map<String, Object> rankMap = new HashMap<String, Object>();
		List<Long> groupIdList = sexMapper.getUserGroupIdList(userId);
		// Integer total = groupIdList.size();
		List<GroupDto> finalList = new ArrayList<GroupDto>();
		for (Long id : groupIdList) {
			List<GroupDto> tmpDto = sexMapper.getUserGroupRank(id);
			for (GroupDto groupDto : tmpDto) {
				if (userId.equals(groupDto.getUserId())) {
					GroupDto finalDto = new GroupDto();
					BeanUtils.copyProperties(groupDto, finalDto);
					finalDto.setId(id);
					GroupUserExample ex = new GroupUserExample();
					ex.createCriteria().andGroupIdFkEqualTo(groupDto.getId());
					List<GroupUser> list = guMapper.selectByExample(ex);
					finalDto.setGroupRank(groupDto.getNum().toString() + "/"
							+ list.size());
					finalList.add(finalDto);
				}
			}
		}
		rankMap.put("groupranklist", finalList);
		return rankMap;
	}

	@Override
	public Map<String, Object> getUserGroupRankInfoDetail(Long userId,
			Long groupId) {
		Integer totalReader = gexMapper.getTotalReader(groupId);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("groupId", groupId);
		GroupDto dto = gexMapper.queryGroupList(params).get(0);
		dto.setTotoalReader(totalReader.toString());
		List<UserDto> userList = sexMapper.getGroupUserScoreList(groupId);
		params.clear();
		params.put("groupinfo", dto);
		params.put("userlist", userList);
		return params;
	}

	@Override
	public Map<String, Object> getRemarkInGroupkByUserId(Long userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<RemarkDto> dtoList = rexMapper.getRemarkInGroupkByUserId(userId);
		Integer totalRemark = 0;
		for (RemarkDto remarkDto : dtoList) {
			totalRemark += remarkDto.getGroupTotalRemark();
		}
		map.put("remarkList", dtoList);
		map.put("totalRemark", totalRemark);
		return map;
	}

	@Override
	public Map<String, Object> getUserRemarkInGroup(Long userId, Long groupId) {
		Map<String, Object> params = new HashMap<String, Object>();
		User user = WeixinService.userMap.get(userId);
		// 获得groupdto，不要在这里加入userId
		params.put("allInfo", "1");
		params.put("groupId", groupId);
		GroupDto group = gexMapper.queryGroupList(params).get(0);
		params.remove("allInfo");
		params.put("userId", userId);
		// 获得用户的书评列表
		List<RemarkDto> remarkList = rexMapper.getUserInGroupRemarkList(params);
		// 获得该小组下所有id-评论数列表
		params.remove("userId");
		params.put("type", "1");
		List<RemarkDto> replyList = rexMapper
				.getGroupRemarkInteractList(params);
		// 获得该小组下所有的id-点赞数列表
		params.remove("userId");
		params.put("type", "2");
		List<RemarkDto> likeList = rexMapper.getGroupRemarkInteractList(params);
		// 最终丰富的列表
		List<RemarkDto> finalList = new ArrayList<RemarkDto>();
		// 丰富最终列表的内容
		for (RemarkDto remarkDto : remarkList) {
			remarkDto.setUserId(userId);
			remarkDto.setHeadImage(user.getHeadImgUrl());
			remarkDto.setUserName(user.getNickname());
			for (RemarkDto replyDto : replyList) {
				if (replyDto.getRemarkId() == remarkDto.getRemarkId()) {
					remarkDto.setTotalReply(replyDto.getTotalReply());
				}
			}
			for (RemarkDto likeDto : likeList) {
				if (likeDto.getRemarkId() == remarkDto.getRemarkId()) {
					remarkDto.setTotalLike(likeDto.getTotalLike());
				}
			}
			finalList.add(remarkDto);
		}
		params.clear();
		params.put("groupInfo", group);
		params.put("remarklist", finalList);
		// 该用户信息
		// params.put("user", WeixinService.userMap.get(userId));
		return params;
	}

	/**
	 * 获得打卡数组
	 */
	@Override
	public Map<String, Object> getPunchDateArray(Map<String, Object> params) {
		// 查询开始时间
		if (params.get("startDate") != null) {
			Date startDate = new Date((Long) params.get("startDate"));
			params.put("startDate",
					MarkUtils.formatDate(Constans.DATE_TYPE_ONE, startDate));
		} else {
			params.put("startDate",
					MarkUtils.getMonthStartAndEnd().get("startDate"));
		}
		// 查询结束时间
		if (params.get("endDate") != null) {
			Date endDate = new Date((Long) params.get("endDate"));
			params.put("endDate",
					MarkUtils.formatDate(Constans.DATE_TYPE_ONE, endDate));
		} else {
			params.put("endDate", MarkUtils.getMonthStartAndEnd()
					.get("endDate"));
		}
		List<Remark> punchDateList = rexMapper.getContinuePunch(params);
		Long[] dateArray = new Long[punchDateList.size()];
		int i = 0;
		for (Remark r : punchDateList) {
			dateArray[i] = r.getCreateTime().getTime();
			i++;
		}
		params.clear();
		params.put("datearray", dateArray);
		params.put("monthtotalpunch", dateArray.length);
		return params;
	}

	@Override
	public Integer editUserBook(Map<String, Object> params) {
		Long userId = (Long) params.get("userId");
		Long bookId = (Long) params.get("bookId");
		Book book = (Book) params.get("book");
		String type = params.get("type").toString();
		Object delete = params.get("delete");
		int i = 0;
		if (!StringUtils.isEmpty(delete)) {
			userLikeExample ex = new userLikeExample();
			ex.createCriteria().andUserIdFkEqualTo(userId)
					.andBookIdFkEqualTo(bookId).andTypeEqualTo(type);
			i = ulMapper.deleteByExample(ex);
		} else {
			bookMapper.insert(book);
			userLike ul = new userLike();
			ul.setCreateTime(new Date());
			ul.setUpdateTime(new Date());
			ul.setUserIdFk(userId);
			ul.setBookIdFk(book.getId());
			ul.setType(type);
			ul.setStatus("1");
			i = ulMapper.insert(ul);
		}
		return i;
	}

	@Override
	public User getUserById(Long userId) {
		User user = userMapper.selectByPrimaryKey(userId);
		return user;
	}
}

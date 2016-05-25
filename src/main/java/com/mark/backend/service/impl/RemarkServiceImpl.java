package com.mark.backend.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.mark.backend.dto.GroupDto;
import com.mark.backend.dto.InteractDto;
import com.mark.backend.dto.RemarkDto;
import com.mark.backend.dto.UserDto;
import com.mark.backend.mysql.mapper.GroupExMapper;
import com.mark.backend.mysql.mapper.GroupUserMapper;
import com.mark.backend.mysql.mapper.InteractExMapper;
import com.mark.backend.mysql.mapper.InteractMapper;
import com.mark.backend.mysql.mapper.RemarkExMapper;
import com.mark.backend.mysql.mapper.RemarkInteractMapper;
import com.mark.backend.mysql.mapper.RemarkMapper;
import com.mark.backend.mysql.po.Interact;
import com.mark.backend.mysql.po.Remark;
import com.mark.backend.mysql.po.RemarkExample;
import com.mark.backend.mysql.po.RemarkInteract;
import com.mark.backend.mysql.po.RemarkWithBLOBs;
import com.mark.backend.mysql.po.User;
import com.mark.backend.service.IPicService;
import com.mark.backend.service.IRemarkService;
import com.mark.backend.service.IScoreService;
import com.mark.backend.utils.Constans;
import com.mark.backend.utils.MarkUtils;

@Service
public class RemarkServiceImpl implements IRemarkService {

    private final static Logger LOGGER = LoggerFactory
            .getLogger(RemarkServiceImpl.class);

    ExecutorService executor = Executors.newSingleThreadExecutor();
    @Resource
    private RemarkMapper remarkMapper;
    @Resource
    private RemarkExMapper rexMapper;
    @Resource
    private GroupExMapper gexMapper;
    @Resource
    private GroupUserMapper guMapper;
    @Resource
    private InteractExMapper iexMapper;
    @Resource
    private RemarkInteractMapper riMapper;
    @Resource
    private InteractMapper interactMapper;
    @Resource
    private MessageService msgService;
    @Resource
    private IPicService picService;
    @Resource
    private IScoreService scoreService;

    @Override
    public List<RemarkDto> getPunchList(Long userId) {
        List<GroupDto> groupList = gexMapper.getUserGroupList(userId);
        List<RemarkDto> finalList = new ArrayList<RemarkDto>();
        for (GroupDto groupDto : groupList) {
            // if (groupDto.getBeginDate().getTime() > MarkUtils.getZeroTime()
            // .getTime()) {
            // break;
            // }
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("groupId", groupDto.getId());
            params.put("userId", userId);
            RemarkDto rdto = new RemarkDto();
            rdto.setGroupStatus(groupDto.getGroupStatus());
            rdto.setGroupId(groupDto.getId());
            rdto.setGroupName(groupDto.getGroupName());
            rdto.setBookName(groupDto.getBookName());
            rdto.setImage(groupDto.getGroupImage());
            rdto.setUserId(userId);
            rdto.setReadCompleteDate(groupDto.getReadCompleteDate());
            rdto.setIsComplete(2 == groupDto.getUserStatus());
            rdto.setContinuePunch((Integer) this.getContinuePunchInfo(params)
                    .get("totalPunch"));
            Map<String, Object> params1 = new HashMap<String, Object>();
            params1.put("userId", userId);
            params1.put("groupId", groupDto.getId());
            Integer lastPage = gexMapper.getLastReadPage(params1);
            rdto.setLastPage(lastPage);
            // 今日是否打卡了
            RemarkExample rex = new RemarkExample();
            rex.createCriteria().andGroupIdFkEqualTo(groupDto.getId())
                    .andCreateTimeGreaterThan(MarkUtils.getZeroTime());
            List<Remark> remarkList = remarkMapper.selectByExample(rex);
            for (Remark remark : remarkList) {
                if (userId.equals(remark.getUserIdFk())) {
                    rdto.setIsPunch(true);
                }
            }
            // 今日该小组打卡人数
            rdto.setTodayPunch(remarkList.size());
            finalList.add(rdto);
        }
        return finalList;
    }

    @Override
    public Long createRemark(RemarkWithBLOBs remark, String picUrl) {
        // 判断今天是否打过卡了
        RemarkExample ex = new RemarkExample();
        ex.createCriteria().andUserIdFkEqualTo(remark.getUserIdFk())
                .andGroupIdFkEqualTo(remark.getGroupIdFk())
                .andCreateTimeGreaterThan(MarkUtils.getZeroTime());
        List<Remark> remarkList = remarkMapper.selectByExample(ex);
        if (remarkList.size() > 0) {
            return -2L;
        }
        remark.setCreateTime(MarkUtils.getCurrentTime());
        remark.setUpdateTime(remark.getCreateTime());
        remarkMapper.insert(remark);
        Long returnId = remark.getId();
        if (!StringUtils.isEmpty(picUrl)) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("idFk", returnId);
            params.put("type", "1");
            String[] picArray = picUrl.split(",");
            params.put("picArray", picArray);
            picService.savePic(params);
        }
        scoreService.updateUserScore(remark.getUserIdFk(),
                remark.getGroupIdFk(), 6L);
        if (returnId > 0) {
            return remark.getId();
        } else {
            return -1L;
        }

    }

    @Override
    public Map<String, Object> getUserInGroupTodayRemark(Long userId,
                                                         Long groupId) {
        Map<String, Object> map = new HashMap<String, Object>();
        // 用户信息
        User user = WeixinService.userMap.get(userId);
        // 主体信息
        RemarkExample rex = new RemarkExample();
        rex.createCriteria().andUserIdFkEqualTo(userId)
                .andGroupIdFkEqualTo(groupId)
                .andCreateTimeGreaterThan(MarkUtils.getZeroTime());

        List<RemarkWithBLOBs> remarkList = remarkMapper
                .selectByExampleWithBLOBs(rex);
        if (remarkList.size() > 0) {
            RemarkWithBLOBs remark = remarkList.get(0);
            // 点赞列表
            List<UserDto> likeList = iexMapper.getLikeList(remark.getId());
            // 回复列表
            List<InteractDto> replyList = iexMapper
                    .getReplyList(remark.getId());
            // pic表中存储的书评图片，type是2
            String pictureUrl = picService.getPicByIdFk(remark.getId(), "1");

            List<RemarkDto> punchList = this.getPunchList(userId);
            RemarkDto dto = new RemarkDto();
            for (RemarkDto remarkDto : punchList) {
                if (remarkDto.getGroupId() == remark.getGroupIdFk()) {
                    BeanUtils.copyProperties(remarkDto, dto);
                }
            }
            if (dto != null) {
                map.put("bookname", dto.getBookName());
                map.put("image", dto.getImage());
                map.put("continuepunch", dto.getContinuePunch());
            }
            map.put("pictureUrl", pictureUrl);
            map.put("totalLike", likeList.size());
            map.put("likelist", likeList);
            map.put("replylist", replyList);
            map.put("user", user);
            map.put("remark", remark);
            return map;
        }
        return map;
    }

    @Override
    public Map<String, Object> getGroupRemark(Long groupId) {
        // 查询map，查点赞数和评论数用

        System.out.println("Getting GroupRemark....., groupId:"+groupId);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("groupId", groupId);
        // 只有热门的list中做了当前用户是否对这条书评点过赞的查询，而且若要进行查询，需在map中加入当前登录的用户id，而现在没有加
        // 热门的list
        List<RemarkDto> hotList = rexMapper.getGroupRemarkHotList(map);
        LOGGER.debug("热门书评列表大小：" + hotList.size());
        // 按时间排序list
        List<RemarkDto> timeOrderList = rexMapper
                .getGroupRemarkListRecentlyList(map);
        LOGGER.debug("热门书评列表大小：" + timeOrderList.size());
        // 丰富后的热门列表
        List<RemarkDto> finalHotList = new ArrayList<RemarkDto>();
        List<RemarkDto> finalTimeOrderList = new ArrayList<RemarkDto>();
        // 回复数id-count列表
        map.put("type", 1);
        List<RemarkDto> replyCountList = rexMapper
                .getGroupRemarkInteractList(map);
        // 点赞数id-count列表
        map.put("type", 2);
        List<RemarkDto> likeCountList = rexMapper
                .getGroupRemarkInteractList(map);
        for (RemarkDto hotDto : hotList) {
            if (hotDto == null) {
                continue;
            }
            User user = WeixinService.userMap.get(hotDto.getUserId());
            if(user == null){
                continue;
            }
            hotDto.setUserName(user.getNickname());
            hotDto.setHeadImage(user.getHeadImgUrl());
            for (RemarkDto replyDto : replyCountList) {
                if (replyDto.getRemarkId().equals(hotDto.getRemarkId())) {
                    hotDto.setTotalReply(replyDto.getTotalReply());
                }
            }
            for (RemarkDto likeDto : likeCountList) {
                if (likeDto == null) {
                    continue;
                }
                if (likeDto.getRemarkId().equals(hotDto.getRemarkId())) {
                    hotDto.setTotalLike(likeDto.getTotalLike());
                }
            }
            finalHotList.add(hotDto);
        }
        for (RemarkDto timeOrderDto : timeOrderList) {
            if (timeOrderDto == null) {
                continue;
            }
            User user = WeixinService.userMap.get(timeOrderDto.getUserId());
            if(user == null){
                continue;
            }
            timeOrderDto.setUserName(user.getNickname());
            timeOrderDto.setHeadImage(user.getHeadImgUrl());
            for (RemarkDto replyDto : replyCountList) {
                if (replyDto.getRemarkId().equals(timeOrderDto.getRemarkId())) {
                    timeOrderDto.setTotalReply(replyDto.getTotalReply());
                }
            }
            for (RemarkDto likeDto : likeCountList) {
                if (likeDto == null) {
                    continue;
                }
                if (likeDto.getRemarkId().equals(timeOrderDto.getRemarkId())) {
                    timeOrderDto.setTotalLike(likeDto.getTotalLike());
                }
            }
            finalTimeOrderList.add(timeOrderDto);
        }
        map.clear();
        map.put("hotlist", finalHotList);
        map.put("timeorderlist", finalTimeOrderList);
        LOGGER.debug("finla'热门书评列表大小：" + finalHotList.size());
        LOGGER.debug("final时间排序评列表大小：" + finalTimeOrderList.size());
        return map;
    }

    @Override
    public Map<String, Object> getRemarkById(Long remarkId) {
        Map<String, Object> map = new HashMap<String, Object>();
        RemarkWithBLOBs remark = remarkMapper.selectByPrimaryKey(remarkId);
        // pic表中存储的书评图片，type是2
        String pictureUrl = picService.getPicByIdFk(remarkId, "1");
        // 用户信息
        User user = WeixinService.userMap.get(remark.getUserIdFk());
        // 点赞列表
        List<UserDto> likeList = iexMapper.getLikeList(remark.getId());
        // 回复列表
        List<InteractDto> replyList = iexMapper.getReplyList(remark.getId());
        List<RemarkDto> punchList = this.getPunchList(remark.getUserIdFk());
        RemarkDto dto = new RemarkDto();
        for (RemarkDto remarkDto : punchList) {
            if (remarkDto.getGroupId() == remark.getGroupIdFk()) {
                BeanUtils.copyProperties(remarkDto, dto);
            }
        }
        if (dto != null) {
            map.put("bookname", dto.getBookName());
            map.put("image", dto.getImage());
            map.put("continuepunch", dto.getContinuePunch());
        }
        map.put("totalLike", likeList.size());
        map.put("likelist", likeList);
        map.put("replylist", replyList);
        map.put("user", user);
        map.put("remark", remark);
        map.put("pictureUrl", pictureUrl);
        return map;
    }

    @Override
    public Integer InteractWithRemark(Long remarkId, Long userId,
                                      Long authorId, String content, String type) {
        // 交互表对象
        Interact interact = new Interact();
        interact.setCreateTime(MarkUtils.getCurrentTime());
        interact.setUpdateTime(interact.getCreateTime());
        interact.setUserIdFk(userId);
        // 若有内容，设置回复内容
        if (!StringUtils.isEmpty(content)) {
            interact.setContent(content);
        }
        Integer insertId = interactMapper.insert(interact);
        if (insertId > 0) {
            // 书评交互关系表对象
            RemarkInteract ri = new RemarkInteract();
            ri.setCreateTime(interact.getCreateTime());
            ri.setUpdateTime(ri.getCreateTime());
            ri.setInteractIdFk(interact.getId());
            ri.setRemarkIdFk(remarkId);
            ri.setType(type);
            ri.setStatus(Constans.NOT_CHECK);
            Integer insertTrId = riMapper.insert(ri);

			/*---------------------分割线---------------------------*/
            // 输入user message表以及加上用户积分
            // 书评标题

            final RemarkWithBLOBs remark = remarkMapper
                    .selectByPrimaryKey(remarkId);
            final Long interactId = interact.getId();
            final Long toUserId = authorId;
            final Long fromUserId = userId;
            final String itype = type;
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put("title", remark.getTitle());
                    params.put("interactId", interactId);
                    params.put("toUserId", toUserId);
                    params.put("fromUserId", fromUserId);
                    params.put("type", itype);
                    msgService.insertNewMessage(params);
                    // 插入积分，被赞或者被评论的3分，动作发生人1分
                    scoreService.updateUserScore(toUserId,
                            remark.getGroupIdFk(), 3L);
                    scoreService.updateUserScore(fromUserId,
                            remark.getGroupIdFk(), 1L);
                }
            });
            /*---------------------分割线---------------------------*/

            if (insertTrId > 0) {
                return insertTrId + insertId;
            }
        }
        return -1;
    }

    @Override
    public Map<String, Object> getContinuePunchInfo(Map<String, Object> params) {
        Map<String, Object> punchMap = new HashMap<String, Object>();
        List<Remark> remarkList = rexMapper.getContinuePunch(params);
        // 获得今日零点
        Long baseline = MarkUtils.getZeroTime().getTime();
        // 连续打卡天数
        Integer continuePunch = 0;
        if (remarkList.size() > 0) {
            if (remarkList.get(0).getCreateTime().getTime() == baseline) {
                continuePunch += 1;
                for (int j = 1; j < remarkList.size(); j++) {
                    Long temp = (baseline - remarkList.get(j).getCreateTime()
                            .getTime())
                            - j * Constans.DAY;
                    if (temp == 0) {
                        continuePunch += 1;
                    } else {
                        break;
                    }
                }
            } else {
                for (int j = 0; j < remarkList.size(); j++) {
                    Long temp = (baseline - remarkList.get(j).getCreateTime()
                            .getTime())
                            - (j + 1) * Constans.DAY;
                    if (temp == 0) {
                        continuePunch += 1;
                    } else {
                        break;
                    }
                }
            }
        }
        punchMap.put("totalPunch", continuePunch);
        return punchMap;
    }

    @Override
    public Map<String, Object> getUserRemarkList(Map<String, Object> params) {
        // 开始和结束日期
        // String startDate = MarkUtils.formatDate(Constans.DATE_TYPE_ONE,
        // new Date((Long) params.get("date")));
        // String endDate = MarkUtils.formatDate(Constans.DATE_TYPE_ONE, new
        // Date(
        // (Long) params.get("date") + Constans.DAY));
        // params.put("startDate", startDate);
        // params.put("endDate", endDate);
        // 条件查询
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Long userId = (Long) params.get("userId");
        resultMap.put("userId", userId);
        User user = WeixinService.userMap.get(userId);
        RemarkExample rx = new RemarkExample();

        if (params.get("date") != null) {
            Date startDate = new Date((Long) params.get("date"));
            Date endDate = new Date((Long) params.get("date") + Constans.DAY);
            rx.createCriteria().andCreateTimeGreaterThanOrEqualTo(startDate)
                    .andCreateTimeLessThan(endDate).andUserIdFkEqualTo(userId);
        } else {
            rx.createCriteria().andUserIdFkEqualTo(userId);
        }
        List<RemarkWithBLOBs> remarkList = remarkMapper
                .selectByExampleWithBLOBs(rx);
        List<RemarkDto> dtoList = new ArrayList<RemarkDto>();
        for (RemarkWithBLOBs remark : remarkList) {
            RemarkDto dto = new RemarkDto();
            dto.setCreateTime(remark.getCreateTime());
            dto.setUserId(remark.getUserIdFk());
            dto.setGroupId(remark.getGroupIdFk());
            dto.setRemarkId(remark.getId());
            dto.setComment(remark.getComment());
            dto.setTitle(remark.getTitle());
            dto.setHeadImage(user.getHeadImgUrl());
            dto.setUserName(user.getNickname());
            dto.setTotalLike(iexMapper.getLikeList(remark.getId()).size());
            dto.setTotalReply(iexMapper.getReplyList(remark.getId()).size());
            dtoList.add(dto);
        }
        resultMap.put("remarklist", dtoList);
        return resultMap;
    }

    @Override
    public Integer updateRemark(RemarkWithBLOBs remark, String pictureUrl) {
        Map<String, Object> params = new HashMap<String, Object>();
        Long remarkId = remark.getId();
        // 先更新书评
        int i = remarkMapper.updateByPrimaryKeySelective(remark);
        // 更新书评的图片
        params.put("idFk", remarkId);
        params.put("type", "1");
        String[] picArray = pictureUrl.split(",");
        params.put("picArray", picArray);
        picService.savePic(params);
        // TODO Auto-generated method stub
        if (i > 0) {
            return i;
        } else {
            return -1;
        }

    }
}

package com.mark.backend.mysql.mapper;

import com.mark.backend.mysql.po.userMessage;
import com.mark.backend.mysql.po.userMessageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface userMessageMapper {
    int countByExample(userMessageExample example);

    int deleteByExample(userMessageExample example);

    int deleteByPrimaryKey(Long id);

    int insert(userMessage record);

    int insertSelective(userMessage record);

    List<userMessage> selectByExample(userMessageExample example);

    userMessage selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") userMessage record, @Param("example") userMessageExample example);

    int updateByExample(@Param("record") userMessage record, @Param("example") userMessageExample example);

    int updateByPrimaryKeySelective(userMessage record);

    int updateByPrimaryKey(userMessage record);
}
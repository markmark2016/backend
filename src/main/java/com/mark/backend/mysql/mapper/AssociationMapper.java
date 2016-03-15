package com.mark.backend.mysql.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mark.backend.mysql.po.Association;
import com.mark.backend.mysql.po.AssociationExample;
import com.mark.backend.mysql.po.Group;

public interface AssociationMapper {
    int countByExample(AssociationExample example);

    int deleteByExample(AssociationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Association record);

    int insertSelective(Association record);

    List<Association> selectByExample(AssociationExample example);

    Association selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Association record, @Param("example") AssociationExample example);

    int updateByExample(@Param("record") Association record, @Param("example") AssociationExample example);

    int updateByPrimaryKeySelective(Association record);

    int updateByPrimaryKey(Association record);

	List<Group> getGroupByAssociationId(Long assId);
}
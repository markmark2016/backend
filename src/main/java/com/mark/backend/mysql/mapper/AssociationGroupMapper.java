package com.mark.backend.mysql.mapper;

import com.mark.backend.mysql.po.AssociationGroup;
import com.mark.backend.mysql.po.AssociationGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AssociationGroupMapper {
    int countByExample(AssociationGroupExample example);

    int deleteByExample(AssociationGroupExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AssociationGroup record);

    int insertSelective(AssociationGroup record);

    List<AssociationGroup> selectByExample(AssociationGroupExample example);

    AssociationGroup selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AssociationGroup record, @Param("example") AssociationGroupExample example);

    int updateByExample(@Param("record") AssociationGroup record, @Param("example") AssociationGroupExample example);

    int updateByPrimaryKeySelective(AssociationGroup record);

    int updateByPrimaryKey(AssociationGroup record);
}
package com.mark.backend.mysql.mapper;

import com.mark.backend.mysql.po.AssociationCategory;
import com.mark.backend.mysql.po.AssociationCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AssociationCategoryMapper {
    int countByExample(AssociationCategoryExample example);

    int deleteByExample(AssociationCategoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AssociationCategory record);

    int insertSelective(AssociationCategory record);

    List<AssociationCategory> selectByExample(AssociationCategoryExample example);

    AssociationCategory selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AssociationCategory record, @Param("example") AssociationCategoryExample example);

    int updateByExample(@Param("record") AssociationCategory record, @Param("example") AssociationCategoryExample example);

    int updateByPrimaryKeySelective(AssociationCategory record);

    int updateByPrimaryKey(AssociationCategory record);
}
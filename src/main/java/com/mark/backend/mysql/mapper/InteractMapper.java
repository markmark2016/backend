package com.mark.backend.mysql.mapper;

import com.mark.backend.mysql.po.Interact;
import com.mark.backend.mysql.po.InteractExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InteractMapper {
    int countByExample(InteractExample example);

    int deleteByExample(InteractExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Interact record);

    int insertSelective(Interact record);

    List<Interact> selectByExampleWithBLOBs(InteractExample example);

    List<Interact> selectByExample(InteractExample example);

    Interact selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Interact record, @Param("example") InteractExample example);

    int updateByExampleWithBLOBs(@Param("record") Interact record, @Param("example") InteractExample example);

    int updateByExample(@Param("record") Interact record, @Param("example") InteractExample example);

    int updateByPrimaryKeySelective(Interact record);

    int updateByPrimaryKeyWithBLOBs(Interact record);

    int updateByPrimaryKey(Interact record);
}
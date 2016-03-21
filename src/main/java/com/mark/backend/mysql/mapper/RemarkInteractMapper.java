package com.mark.backend.mysql.mapper;

import com.mark.backend.mysql.po.RemarkInteract;
import com.mark.backend.mysql.po.RemarkInteractExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RemarkInteractMapper {
    int countByExample(RemarkInteractExample example);

    int deleteByExample(RemarkInteractExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RemarkInteract record);

    int insertSelective(RemarkInteract record);

    List<RemarkInteract> selectByExample(RemarkInteractExample example);

    RemarkInteract selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RemarkInteract record, @Param("example") RemarkInteractExample example);

    int updateByExample(@Param("record") RemarkInteract record, @Param("example") RemarkInteractExample example);

    int updateByPrimaryKeySelective(RemarkInteract record);

    int updateByPrimaryKey(RemarkInteract record);
}
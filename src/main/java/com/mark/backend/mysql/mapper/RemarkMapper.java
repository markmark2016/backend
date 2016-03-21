package com.mark.backend.mysql.mapper;

import com.mark.backend.mysql.po.Remark;
import com.mark.backend.mysql.po.RemarkExample;
import com.mark.backend.mysql.po.RemarkWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RemarkMapper {
    int countByExample(RemarkExample example);

    int deleteByExample(RemarkExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RemarkWithBLOBs record);

    int insertSelective(RemarkWithBLOBs record);

    List<RemarkWithBLOBs> selectByExampleWithBLOBs(RemarkExample example);

    List<Remark> selectByExample(RemarkExample example);

    RemarkWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RemarkWithBLOBs record, @Param("example") RemarkExample example);

    int updateByExampleWithBLOBs(@Param("record") RemarkWithBLOBs record, @Param("example") RemarkExample example);

    int updateByExample(@Param("record") Remark record, @Param("example") RemarkExample example);

    int updateByPrimaryKeySelective(RemarkWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(RemarkWithBLOBs record);

    int updateByPrimaryKey(Remark record);
}
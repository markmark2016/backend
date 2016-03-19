package com.mark.backend.mysql.mapper;

import com.mark.backend.mysql.po.userLike;
import com.mark.backend.mysql.po.userLikeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface userLikeMapper {
    int countByExample(userLikeExample example);

    int deleteByExample(userLikeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(userLike record);

    int insertSelective(userLike record);

    List<userLike> selectByExample(userLikeExample example);

    userLike selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") userLike record, @Param("example") userLikeExample example);

    int updateByExample(@Param("record") userLike record, @Param("example") userLikeExample example);

    int updateByPrimaryKeySelective(userLike record);

    int updateByPrimaryKey(userLike record);
}
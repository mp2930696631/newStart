package com.hz.myzuul.dao;

import com.hz.myzuul.entity.GrayscalePublishing;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GrayscalePublishingDao {
    int deleteByPrimaryKey(Integer id);

    int insert(GrayscalePublishing record);

    int insertSelective(GrayscalePublishing record);

    GrayscalePublishing selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GrayscalePublishing record);

    int updateByPrimaryKey(GrayscalePublishing record);

    List<GrayscalePublishing> getAllGrayscale();

    List<GrayscalePublishing> getGrayscaleByUserId(@Param("userId") String userId);
}
package com.hz.consumer02.dao;

import com.hz.consumer02.entity.GrayscalePublishing;
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

    GrayscalePublishing getGrayscaleByUserIdAndServiceId(@Param("userId") String userId, @Param("serviceId") String serviceId);
}
package com.hz.consumer01.service;

import com.hz.consumer01.entity.GrayscalePublishing;

import java.util.List;

/**
 * @author zehua
 * @date 2021/1/14 8:11
 */
public interface GrayscaleService {
    List<GrayscalePublishing> getAllGrayscale();

    List<GrayscalePublishing> getGrayscaleByUserId(String userId);

    GrayscalePublishing getGrayscaleByUserIdAndServiceId(String userId, String serviceId);
}

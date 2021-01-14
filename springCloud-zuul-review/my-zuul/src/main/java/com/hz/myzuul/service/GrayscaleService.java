package com.hz.myzuul.service;

import com.hz.myzuul.entity.GrayscalePublishing;

import java.util.List;

/**
 * @author zehua
 * @date 2021/1/14 8:11
 */
public interface GrayscaleService {
    List<GrayscalePublishing> getAllGrayscale();

    List<GrayscalePublishing> getGrayscaleByUserId(String userId);
}

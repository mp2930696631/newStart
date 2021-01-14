package com.hz.myzuul.service.impl;

import com.hz.myzuul.dao.GrayscalePublishingDao;
import com.hz.myzuul.entity.GrayscalePublishing;
import com.hz.myzuul.service.GrayscaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zehua
 * @date 2021/1/14 8:13
 */
@Service
public class GrayscaleServiceImpl implements GrayscaleService {
    @Autowired
    private GrayscalePublishingDao grayscalePublishingDao;

    @Override
    public List<GrayscalePublishing> getAllGrayscale() {
        return grayscalePublishingDao.getAllGrayscale();
    }

    @Override
    public List<GrayscalePublishing> getGrayscaleByUserId(String userId) {
        return grayscalePublishingDao.getGrayscaleByUserId(userId);
    }
}

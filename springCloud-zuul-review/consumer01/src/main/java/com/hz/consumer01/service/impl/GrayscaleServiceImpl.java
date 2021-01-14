package com.hz.consumer01.service.impl;

import com.hz.consumer01.dao.GrayscalePublishingDao;
import com.hz.consumer01.entity.GrayscalePublishing;
import com.hz.consumer01.service.GrayscaleService;
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

    @Override
    public GrayscalePublishing getGrayscaleByUserIdAndServiceId(String userId, String serviceId) {
        return grayscalePublishingDao.getGrayscaleByUserIdAndServiceId(userId, serviceId);
    }
}

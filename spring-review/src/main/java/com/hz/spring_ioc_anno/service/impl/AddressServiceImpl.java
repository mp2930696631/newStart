package com.hz.spring_ioc_anno.service.impl;

import com.hz.spring_ioc_anno.service.AddressService;
import org.springframework.stereotype.Service;

/**
 * @Auther zehua
 * @Date 2020/11/6 12:19
 */
@Service
public class AddressServiceImpl implements AddressService {
    @Override
    public void save() {
        System.out.println("address save.....");
    }
}

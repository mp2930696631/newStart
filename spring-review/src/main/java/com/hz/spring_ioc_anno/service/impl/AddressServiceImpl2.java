package com.hz.spring_ioc_anno.service.impl;

import com.hz.spring_ioc_anno.service.AddressService;
import org.springframework.stereotype.Service;

/**
 * @Auther zehua
 * @Date 2020/11/6 12:26
 */
@Service
public class AddressServiceImpl2 implements AddressService {
    @Override
    public void save() {
        System.out.println("address 2 save.............");
    }
}

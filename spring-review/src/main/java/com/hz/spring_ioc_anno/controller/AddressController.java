package com.hz.spring_ioc_anno.controller;

import com.hz.spring_ioc_anno.service.AddressService;
import com.hz.spring_ioc_anno.service.impl.AddressServiceImpl;
import com.hz.spring_ioc_anno.service.impl.AddressServiceImpl2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * @Auther zehua
 * @Date 2020/11/6 12:18
 */
@Controller
public class AddressController {
    // 在这种情况下，因为Autowired是按照类型进行查找，所有下面代码没有问题，变量addressServiceImpl的实际值为AddressServiceImpl2
    // 而如果使用Resource的话，会出现类型转化错误
    @Autowired
    private AddressServiceImpl2 addressServiceImpl;

    public void save() {
        addressServiceImpl.save();
    }
}

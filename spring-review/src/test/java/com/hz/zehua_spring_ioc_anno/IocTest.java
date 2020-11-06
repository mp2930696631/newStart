package com.hz.zehua_spring_ioc_anno;

import com.hz.zehua_spring_ioc_anno.controller.PersonController;
import com.hz.util.SpringUtil;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * @Auther zehua
 * @Date 2020/11/6 9:10
 */
public class IocTest {
    /**
     * 模拟Spring @AutoWired注解以及spring扫描原理工作原理
     * 可以递归扫描给定路径下的所有class文件
     * @throws Exception
     */
    @Test
    public void testX() throws Exception {
        File file = SpringUtil.getFileByDotSeparator("com.hz.zehua_spring_ioc_anno");
        ArrayList<String> al = new ArrayList<>();
        SpringUtil.getFilesAbsName(file, al);
        Iterator<String> iterator = al.iterator();
        ArrayList<String> alClassFullName = new ArrayList<>();
        while (iterator.hasNext()) {
            String str = iterator.next();
            String classFullName = SpringUtil.getClassFullName(str);
            alClassFullName.add(classFullName);
        }

        SpringUtil.removeNotNeedScan(alClassFullName);
        Map<String, Object> mapping = SpringUtil.doLoadClass(alClassFullName);

        PersonController personController = (PersonController) mapping.get("personController");
        personController.save();
    }

}

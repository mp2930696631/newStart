package test.com.hz;

import interf.com.hz.DriverManager;

/**
 * SPI真的是有意思啊
 * 其实没什么神奇的，主要依赖于ServiceLoad这个类，
 * 获取其Iterator对象并进行遍历的时候就会去classpath*：/META-INF/services/目录下面去寻找相应的实现类
 *
 * 注意：如果想在其他maven项目中引用本maven项目，那么，本项目的目录结构必须以<groupId>interf.com.hz</groupId>开头
 *
 * @author zehua
 * @date 2020/11/21 8:41
 */
public class Main {
    public static void main(String[] args) {
        final DriverManager driverManager = new DriverManager();
        driverManager.getDriver();
    }
}

package com.hz.observer.v2;

import com.hz.observer.v2.source.Button;
import com.hz.observer.v2.source.Window;

/**
 * 加入事件源，以及保存事件源对象，
 * 模拟buttonclick事件以及window最小化，最大化，关闭事件
 *
 * @Auther zehua
 * @Date 2020/11/8 7:32
 */
public class Main {

    public static void main(String[] args) {
        // 为button添加click监听
        Button button = new Button();
        button.addClickListener((event) -> {
            System.out.println("button clicked......");
            System.out.println("source: " + event.getSource().getSourceName());
        });
        button.addClickListener(event -> {
            System.out.println("button clicked and popup window.........");
            System.out.println("source: " + event.getSource().getSourceName());
        });
        button.click();

        // 为window添加max事件
        Window window = new Window();
        window.addMaxListener((event -> {
            System.out.println("window closed......");
            System.out.println("source: " + event.getSource().getSourceName());
        }));

        window.max();
    }

}

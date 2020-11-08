package com.hz.observer.v3;

import com.hz.observer.v3.adapter.ClickAdapter;
import com.hz.observer.v3.adapter.WindowMaxAdapter;
import com.hz.observer.v3.event.Event;
import com.hz.observer.v3.source.Button;
import com.hz.observer.v3.source.Window;

/**
 * 加入适配器Click1Listener,Click2Listener,WindowMaxListener都是adapter的实现类
 *
 * @Auther zehua
 * @Date 2020/11/8 7:32
 */
public class Main {

    public static void main(String[] args) {
        // 为button添加click监听
        Button button = new Button();
        button.addClickListener(new Click1Listener());
        button.addClickListener(new Click2Listener());
        button.click();

        // 为window添加max事件
        Window window = new Window();
        window.addMaxListener(new WindowMaxListener());

        window.max();
    }

    public static class Click1Listener extends ClickAdapter {

        @Override
        public void doWork(Event event) {
            System.out.println("button clicked......");
            System.out.println("source: " + event.getSource().getSourceName());
        }
    }

    public static class Click2Listener extends ClickAdapter {

        @Override
        public void doWork(Event event) {
            System.out.println("button clicked and popup window.........");
            System.out.println("source: " + event.getSource().getSourceName());
        }
    }

    public static class WindowMaxListener extends WindowMaxAdapter {

        @Override
        public void doWork(Event event) {
            System.out.println("window closed......");
            System.out.println("source: " + event.getSource().getSourceName());
        }
    }

}

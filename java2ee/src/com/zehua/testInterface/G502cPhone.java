package com.zehua.testInterface;

import com.zehua.testInterface.interfaces.Audio;

public class G502cPhone extends Phone implements Audio {

    public G502cPhone(String brand, String type) {
        super(brand, type);
    }

    @Override
    public void sendMsg() {
        System.out.println("G502c send msg");
    }

    @Override
    public void call() {
        System.out.println("G502c call");
    }

    @Override
    public void audioPlay() {
        System.out.println("G502c play audio");
    }
}

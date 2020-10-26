package com.zehua.testInterface;

import com.zehua.testInterface.interfaces.Audio;
import com.zehua.testInterface.interfaces.Internet;
import com.zehua.testInterface.interfaces.TakePicture;
import com.zehua.testInterface.interfaces.Video;

public class HtcPhone extends Phone implements Video, Internet, TakePicture, Audio {

    public HtcPhone(String brand, String type) {
        super(brand, type);
    }

    @Override
    public void sendMsg() {
        System.out.println("Htc send msg");
    }

    @Override
    public void call() {
        System.out.println("Htc call");
    }

    @Override
    public void surface() {
        System.out.println("Htc surface internet");
    }

    @Override
    public void takePicture() {
        System.out.println("Htc take picture");
    }

    @Override
    public void playVideo() {
        System.out.println("Htc play video");
    }

    @Override
    public void audioPlay() {
        System.out.println("Htc play audio");
    }
}

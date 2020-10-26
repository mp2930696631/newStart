package com.zehua.testInterface;

import com.zehua.testInterface.interfaces.Audio;
import com.zehua.testInterface.interfaces.Video;

public class TestInterface {

    public static void main(String[] args) {
        Video video = new HtcPhone("111", "1");
        Audio audio = new G502cPhone("222", "2");
        video.playVideo();
        audio.audioPlay();
    }

}

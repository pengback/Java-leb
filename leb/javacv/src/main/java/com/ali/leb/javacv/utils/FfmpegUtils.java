package com.ali.leb.javacv.utils;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FrameGrabber;

import java.io.File;

/**
 * @Author: aliber
 * @Date: 2020/12/2 下午4:34
 **/
public class FfmpegUtils {


    public static void test1() throws FrameGrabber.Exception {
        File file = new File("/Users/aliber/Movies/Islandia.mp4");
        FFmpegFrameGrabber grabber = FFmpegFrameGrabber.createDefault(file);
//        grabber.
    }

}

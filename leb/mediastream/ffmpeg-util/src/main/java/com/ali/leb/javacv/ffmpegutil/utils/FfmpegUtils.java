package com.ali.leb.javacv.ffmpegutil.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;

/**
 * @Author: aliber
 * @Date: 2020/12/25 上午10:55
 **/
@Slf4j
public class FfmpegUtils {

    /**
     * 根据文件名, 获取指定的视频文件, 并使用FFMPEG工具将视频文件切片成为可播放的视频流
     *
     * @param id
     * @param filepath
     * @param filename
     * @return
     */
    public static boolean sliceUp(String id, String filepath, String filename) {
        String template = "ffmpeg -i %s.mp4 -hls_time 30 -hls_list_size 0 -hls_segment_filename %s_%%05d.ts %s.m3u8";

        String sourcepath = filepath + File.separator + "vod" + File.separator + filename;
        String outpath = filepath + File.separator + "hls" + File.separator + filename + File.separator + filename;

        String cmd = String.format(template, sourcepath, outpath, outpath);

        Process process = null;
//        ProcessBuilder builder = new ProcessBuilder(cmd2);
//        builder.redirectErrorStream(true);
//        builder.directory();
        try {
//            process = builder.start();
            process = Runtime.getRuntime().exec(cmd);
            long timestart = System.currentTimeMillis();
            log.info("ffmpeg视频切片开始时间: " + timestart);
            long timeend = timestart + 3000;
            while (process.isAlive()) {
                long timenow = System.currentTimeMillis();
                if (timenow >= timeend) {
                    // 程序正常进行, 默认执行三秒不出现错误, 即视为切片程序正常运行, 返回给正常值.
                    log.info("程序正常进行, 默认执行三秒不出现错误, 即视为切片程序正常运行, 返回给正常值.");
                    return true;
                }
            }

            int value = process.waitFor();
            log.info("获取进程信息: " + value);
            if (value == 0) {
                // 切片进程正常结束;
                log.info("切片进程正常结束");
            } else {
                // 切片异常
                log.info("切片异常");
                return false;
            }
            log.info("切片结束");
            return true;
        } catch (IOException e) {
            log.error("切片异常", e);
            return false;
        } catch (InterruptedException e) {
            log.error("切片异常", e);
            return false;
        }
    }

    public static void main(String[] args) {
//        System.out.println(RuntimeUtil.execForStr("ifconfig"));
        sliceUp("", "/Users/aliber/workspace/ze/example/media", "chuan");
        log.info("主程序结束");
    }
}

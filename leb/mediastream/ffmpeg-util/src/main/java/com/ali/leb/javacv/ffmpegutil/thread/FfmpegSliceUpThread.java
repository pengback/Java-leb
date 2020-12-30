package com.ali.leb.javacv.ffmpegutil.thread;

import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.AsyncResult;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;

/**
 * @Author: aliber
 * @Date: 2020/12/29 下午4:59
 **/
@Slf4j
@Data
public class FfmpegSliceUpThread implements Callable<AsyncResult<Boolean>> {

    private boolean flag = false;
    private String filepath;
    private String filename;

    public FfmpegSliceUpThread(String filepath, String filename) {
        this.filepath = filepath;
        this.filename = filename;
    }

    @SneakyThrows
    @Override
    public AsyncResult call() {
        String template = "ffmpeg -i %s.mp4 -hls_time 30 -hls_list_size 0 -hls_segment_filename %s_%%05d.ts %s.m3u8";

        String sourcepath = filepath + File.separator + "vod" + File.separator + filename;
        String outpath = filepath + File.separator + "hls" + File.separator + filename + File.separator + filename;

        String cmd = String.format(template, sourcepath, outpath, outpath);

        Process process = null;
        try {
            process = Runtime.getRuntime().exec(cmd);
            long timestart = System.currentTimeMillis();
            log.info("ffmpeg视频切片开始时间: " + timestart);
            long timeend = timestart + 3000;
            while (process.isAlive()) {
                long timenow = System.currentTimeMillis();
                if (timenow >= timeend && !this.flag) {
                    // 程序正常进行, 默认执行三秒不出现错误, 即视为切片程序正常运行, 返回给正常值.
                    log.info("程序正常进行, 默认执行三秒不出现错误, 即视为切片程序正常运行, 返回给正常值.");
                    this.setTrueFlag();
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
                return new AsyncResult<>(false);
            }
            log.info("切片结束");
            return new AsyncResult<>(true);
        } catch (IOException e) {
            log.error("切片异常", e);
            return new AsyncResult<>(false);
        } catch (InterruptedException e) {
            log.error("切片异常", e);
            return new AsyncResult<>(false);
        }
    }

    /**
     * 返回内部标志
     * @return
     */
    public boolean checkFlag() {
        return this.flag;
    }

    public void setTrueFlag() {
        this.flag = true;
    }

    public void setFailureFlag() {
        this.flag = false;
    }
}

package com.ali.leb.hbase.util;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Describe habse线程池
 *
 * @author darker
 */
@Component
public class HBaseThreadPool {
    /**
     * 私有化构造方法，让用户不能new这个类的对象
     */
    private HBaseThreadPool() {}

    public static String ZK_PORT;

    public static String ZK_URL;

    @Value("${ze.hbase.zk_url}")
    public void setZkUrl(String url) {
        ZK_URL = url;
    }

    @Value("${ze.hbase.zk_port}")
    public void setZkPort(String port) {
        ZK_PORT = port;
    }

    /**
     * 要创建的connection
     */
    private static Connection connection = null;

    public static synchronized Connection getConnection() throws IOException {
        /**
         * 空的时候创建，不为空就直接返回；典型的单例模式
         */
        if (null == connection) {
            // 配置文件获取
            System.getProperties().setProperty("HADOOP_USER_NAME", "hbasetosoftware");
            Configuration config = HBaseConfiguration.create();
            /**
             * zookeeper地址
             */
            config.set("hbase.zookeeper.quorum", ZK_URL);
            /**
             * zookeeper端口
             */
            config.set("hbase.zookeeper.property.clientPort", ZK_PORT);
            /**
             * 设置客户端超时机制
             */
            config.setInt("hbase.rpc.timeout", 20000);
            config.setInt("hbase.client.operation.timeout", 30000);
            config.setInt("hbase.client.scanner.timeout.period", 20000);
            /**
             * 设置客户端rpc重试机制,重试间隔基数
             */
            config.setInt("hbase.client.pause", 50);
            // 重试最大次数
            config.setInt("hbase.client.retries.number", 21);

            // 连接池设置
            // 建立一个数量为20的线程池
            BlockingQueue workQueue = new LinkedBlockingQueue<Runnable>();
            ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(20, 20, 0L, TimeUnit.MILLISECONDS, workQueue);
            try {
                // 用线程池创建connection
                connection = ConnectionFactory.createConnection(config, threadPoolExecutor);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}

package com.yqj.myhadoop.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

public class TestHDFS {

    Configuration conf = null;
    FileSystem fs = null;

    @Before
    public void before() throws IOException {
        // 设置环境变量，指定user名称
        System.setProperty("HADOOP_USER_NAME", "root");
        // 读取resources的配置文件
        conf = new Configuration();
        // 创建hdfs cli对象
        fs = FileSystem.get(conf);
    }

    @Test
    public void testMkdir() throws IOException {
        Path dirPath = new Path("/user/root/abc");
        // 判断目录是否存在
        if (fs.exists(dirPath)) {
            // 递归删除目录
            fs.delete(dirPath, true);
        }
        // 创建目录
        fs.mkdirs(dirPath);
    }

    @Test
    public void testUpload() throws IOException {
        Path dirPath = new Path("/user/root/hdfs_data.txt");
        BufferedInputStream input = new BufferedInputStream(new FileInputStream("./data/hdfs_data.txt"));
        FSDataOutputStream output = fs.create(dirPath);
        IOUtils.copyBytes(input, output, conf, true);
    }

    @Test
    public void testDownload() throws IOException {
        Path dirPath = new Path("/user/root/hdfs_data.txt");
        FSDataInputStream input = fs.open(dirPath);
        BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream("./data/hdfs_data_out.txt"));
        IOUtils.copyBytes(input, output, conf, true);
    }

    @Test
    public void testBlocks() throws IOException {
        // 生成文件
        // for i in `seq 100000`;do  echo "hello hadoop $i"  >>  data.txt  ;done
        // 以1MB切块，上传至HDFS
        // hdfs dfs -D dfs.blocksize=1048576  -put  data.txt /user/root
        // cd /var/bigdata/hadoop/ha/dfs/data/current/BP-782029678-10.211.55.18-1675853576847/current/finalized/subdir0/subdir0
        // 查看最后一行被切割
        // hello hadoop 5
        // 5773
        Path file = new Path("/user/root/data.txt");
        FileStatus fileStatus = fs.getFileStatus(file);
        BlockLocation[] blks = fs.getFileBlockLocations(file, 0, fileStatus.getLen());
        // 0      ,1048576,node02,node04
        // 1048576,840319 ,node02,node03
        for (BlockLocation blk : blks) {
            System.out.println(blk);
        }
    }

    @After
    public void after() throws IOException {
        // 关闭
        fs.close();
    }
}

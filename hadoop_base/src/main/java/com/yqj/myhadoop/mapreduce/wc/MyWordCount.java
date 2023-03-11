package com.yqj.myhadoop.mapreduce.wc;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.io.IOException;

// 使用Maven打jar包，上传至服务器，手动使用linux命令，运行MR程序
// 1.编写程序，maven打包
// 2.scp上传jar至服务器
// 3.运行MR程序，hadoop jar hadoop_base-1.0-SNAPSHOT.jar com.yqj.myhadoop.mapreduce.wc.MyWordCount
public class MyWordCount {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        // 加载配置文件
        Configuration conf = new Configuration();
        // 使用配置文件参数创建job对象
        Job job = Job.getInstance(conf);
        // 找到jar的start位置
        job.setJarByClass(MyWordCount.class);
        job.setJobName("my_word_count");
        // 设置输入文件路径
        Path input = new Path("/data/wc/input");
        TextInputFormat.addInputPath(job,input);
        // 设置输出文件路径
        Path output = new Path("/data/wc/output");
        // 如果存在输出文件目录则删除
        if (output.getFileSystem(conf).exists(output)) {
            output.getFileSystem(conf).delete(output,true);
        }
        TextOutputFormat.setOutputPath(job,output);
        // 设置mapper
        job.setMapperClass(MyMapper.class);
        // 设置mapper的输出k-v类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        // 设置reducer
        job.setReducerClass(MyReduce.class);
        // 设置输出类型(默认key：LongWritable，value：Text)
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        // 启动job
        job.waitForCompletion(true);
    }
}

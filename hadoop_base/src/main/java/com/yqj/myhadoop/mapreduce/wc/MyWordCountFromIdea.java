package com.yqj.myhadoop.mapreduce.wc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import java.io.IOException;

// 使用Maven打jar包，在IDEA中直接完成任务提交，运行MR程序
// 1.编写程序，并使用Maven打包
// 2.idea配置中填写参数并运行
public class MyWordCountFromIdea {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        // 设置环境变量
        System.setProperty("HADOOP_USER_NAME", "root");
        // 加载配置文件
        Configuration conf = new Configuration();
        conf.set("mapred.jar", "target/hadoop_base-1.0-SNAPSHOT.jar");
        // 解决客户端和集群不在一个局域网导致无法访问datanode的问题
        // datanode的 client通信是否使用域名,默认为false
        conf.set("dfs.client.use.datanode.hostname","true");
        // 解析参数，将-D传参配置到conf对象中，剩余的参数放到数组other中
        String[] other = new GenericOptionsParser(conf, args).getRemainingArgs();
        // 使用配置文件参数创建job对象
        Job job = Job.getInstance(conf);
        // 找到jar的start位置
        job.setJarByClass(MyWordCount.class);
        job.setJobName("my_word_count_idea");
        // 设置输入文件路径
        Path input = new Path(other[0]);
        TextInputFormat.addInputPath(job,input);
        // 设置输出文件路径
        Path output = new Path(other[1]);
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

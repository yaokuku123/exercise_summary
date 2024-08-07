package com.yqj.myhadoop.mapreduce.topn;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import java.io.IOException;

// 使用Maven打jar包，上传至服务器，手动使用linux命令，运行MR程序
// 1.编写程序，maven打包
// 2.scp上传jar至服务器
// 3.运行MR程序，hadoop jar hadoop_base-1.0-SNAPSHOT.jar com.yqj.myhadoop.mapreduce.topn.MyTopN /data/topn/input /data/topn/output
public class MyTopN {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        // 设置用户环境变量
        System.setProperty("HADOOP_USER_NAME", "root");
        // 配置文件
        Configuration conf = new Configuration();
        // 解析参数
        String[] other = new GenericOptionsParser(conf, args).getRemainingArgs();
        // job对象
        Job job = Job.getInstance(conf);
        // 配置job
        job.setJarByClass(MyTopN.class);
        job.setJobName("my_topn");
        // input路径
        TextInputFormat.addInputPath(job, new Path(other[0]));
        // output路径
        Path outputPath = new Path(other[1]);
        if (outputPath.getFileSystem(conf).exists(outputPath)) {
            outputPath.getFileSystem(conf).delete(outputPath, true);
        }
        TextOutputFormat.setOutputPath(job, outputPath);
        // mapperTask
        // mapper
        job.setMapperClass(TopnMapper.class);
        // mapper输出格式（可能是由于反射存在泛型擦除的问题）
        job.setMapOutputKeyClass(TopnKey.class);
        job.setMapOutputValueClass(IntWritable.class);
        // 设置分区器 相同年（or 相同年月），相同分区。区别：数据倾斜
        job.setPartitionerClass(TopnPartitioner.class);
        // 设置排序比较器，年，月，温度且温度降序
        job.setSortComparatorClass(TopnSortComparator.class);
        // reduceTask
        // 设置分组器 相同年月为一组
        job.setGroupingComparatorClass(TopnGroupingComparator.class);
        // reducer
        job.setReducerClass(TopnReducer.class);
        // 设置输出类型(默认key：LongWritable，value：Text)
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        // 启动
        job.waitForCompletion(true);
    }
}

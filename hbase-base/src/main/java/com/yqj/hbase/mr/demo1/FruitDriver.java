package com.yqj.hbase.mr.demo1;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/*
    1.编写代码并使用maven打包
    2.scp将jar包和数据fruit.tsv上传服务器
    3.数据上传HDFS：
        hdfs dfs -mkdir -p /data/hbase/input
        hdfs dfs -put fruit.tsv /data/hbase/input
    4.创建Hbase表 create 'fruit2','info'
    5.执行 MapReduce 到 HBase 的 fruit2 表中
        hadoop jar hbase-base-1.0-SNAPSHOT.jar com.yqj.hbase.mr.demo1.FruitDriver /data/hbase/input fruit2
    6.scan查询结果
        scan 'fruit2'
 */
public class FruitDriver implements Tool {

    private Configuration conf = null;

    @Override
    public int run(String[] args) throws Exception {
        // 获取job对象
        Job job = Job.getInstance(conf);
        // 指定驱动类
        job.setJarByClass(FruitDriver.class);
        // 设置mapper和输出的KV类型
        job.setMapperClass(FruitMapper.class);
        job.setMapOutputKeyClass(LongWritable.class);
        job.setMapOutputValueClass(Text.class);
        // 指定reducer和输出类型
        TableMapReduceUtil.initTableReducerJob(args[1], FruitReducer.class, job);
        // 设置输入参数
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        // 执行
        boolean result = job.waitForCompletion(true);
        return result ? 0 : 1;
    }

    @Override
    public void setConf(Configuration configuration) {
        conf = configuration;
    }

    @Override
    public Configuration getConf() {
        return conf;
    }

    public static void main(String[] args) {
        try {
            Configuration conf = new Configuration();
            int run = ToolRunner.run(conf, new FruitDriver(), args);
            System.exit(run);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.yqj.myhadoop.mapreduce.wc;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.io.IOException;

public class MyWordCount {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
        job.setJarByClass(MyWordCount.class);
        job.setJobName("my_word_count");
        Path input = new Path("/data/wc/input");
        TextInputFormat.addInputPath(job,input);
        Path output = new Path("/data/wc/output");
        if (output.getFileSystem(conf).exists(output)) {
            output.getFileSystem(conf).delete(output,true);
        }
        TextOutputFormat.setOutputPath(job,output);

        job.setMapperClass(MyMapper.class);
        job.setReducerClass(MyReduce.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.waitForCompletion(true);
    }
}

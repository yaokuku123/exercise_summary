package com.yqj.myhadoop.mapreduce.topn;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class TopnPartitioner extends Partitioner<TopnKey, IntWritable> {
    @Override
    public int getPartition(TopnKey topnKey, IntWritable intWritable, int numPartitions) {
        return topnKey.getYear() % numPartitions;
    }
}

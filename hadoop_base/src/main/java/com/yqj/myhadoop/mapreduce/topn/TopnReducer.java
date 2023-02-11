package com.yqj.myhadoop.mapreduce.topn;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class TopnReducer extends Reducer<TopnKey, IntWritable, Text, IntWritable> {

    private final Text mKey = new Text();
    private final IntWritable mVal = new IntWritable();

    @Override
    protected void reduce(TopnKey key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        Iterator<IntWritable> iter = values.iterator();
        int flag = 0;
        int day = 0;
        while (iter.hasNext()) {
            iter.next(); // -> context.nextKeyValue() ->  对key和value更新值
            if (flag == 0) {
                mKey.set(key.getYear() + "-" + key.getMonth() + "-" + key.getDay());
                mVal.set(key.getWd());
                context.write(mKey,mVal);
                flag++;
                day = key.getDay();
            }
            if (flag != 0 && day != key.getDay()) {
                mKey.set(key.getYear() + "-" + key.getMonth() + "-" + key.getDay());
                mVal.set(key.getWd());
                context.write(mKey,mVal);
                break;
            }
        }
    }
}

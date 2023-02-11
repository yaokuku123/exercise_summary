package com.yqj.myhadoop.mapreduce.topn;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.util.StringUtils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TopnMapper extends Mapper<LongWritable, Text,TopnKey, IntWritable> {

    private final TopnKey mKey = new TopnKey();
    private final IntWritable mVal = new IntWritable();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        try {
            // 2019-6-1 22:22:22	1	39
            String[] strs = StringUtils.split(value.toString(), '\t');
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(strs[0]);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            // 设置 key
            mKey.setYear(calendar.get(Calendar.YEAR));
            mKey.setMonth(calendar.get(Calendar.MONTH) + 1);
            mKey.setDay(calendar.get(Calendar.DAY_OF_MONTH));
            mKey.setWd(Integer.parseInt(strs[2]));
            // 设置 value
            mVal.set(Integer.parseInt(strs[2]));
            context.write(mKey,mVal);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}

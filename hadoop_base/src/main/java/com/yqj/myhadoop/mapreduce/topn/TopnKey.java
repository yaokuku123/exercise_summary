package com.yqj.myhadoop.mapreduce.topn;

import lombok.Data;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

@Data
public class TopnKey implements WritableComparable<TopnKey> {

    private int year;
    private int month;
    private int day;
    private int wd;

    // 通用比较器，只用来比较年月日
    @Override
    public int compareTo(TopnKey o) {
        int c1 = Integer.compare(this.year, o.getYear());
        if (c1 == 0) {
            int c2 = Integer.compare(this.month, o.getMonth());
            if (c2 == 0) {
                return Integer.compare(this.day, o.getDay());
            }
            return c2;
        }
        return c1;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeInt(year);
        out.writeInt(month);
        out.writeInt(day);
        out.writeInt(wd);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        this.year = in.readInt();
        this.month = in.readInt();
        this.day = in.readInt();
        this.wd = in.readInt();
    }
}

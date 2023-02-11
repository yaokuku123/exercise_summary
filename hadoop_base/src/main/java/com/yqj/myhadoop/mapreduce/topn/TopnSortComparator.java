package com.yqj.myhadoop.mapreduce.topn;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class TopnSortComparator extends WritableComparator {

    public TopnSortComparator() {
        super(TopnKey.class, true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        TopnKey k1 = (TopnKey) a;
        TopnKey k2 = (TopnKey) b;
        int c1 = Integer.compare(k1.getYear(), k2.getYear());
        if (c1 == 0) {
            int c2 = Integer.compare(k1.getMonth(), k2.getMonth());
            if (c2 == 0) {
                return -Integer.compare(k1.getWd(), k2.getWd());
            }
            return c2;
        }
        return c1;
    }
}

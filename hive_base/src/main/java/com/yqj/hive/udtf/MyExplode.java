package com.yqj.hive.udtf;

import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDTF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspectorFactory;
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;

import java.util.ArrayList;
import java.util.List;

// add jar /root/hive_base-1.0-SNAPSHOT.jar;
// create temporary function my_explode as "com.yqj.hive.udtf.MyExplode";
// select movie,category_name from movie_info lateral view my_explode(category,',') tmp as category_name;

public class MyExplode extends GenericUDTF {

    private List<String> outList = new ArrayList<>();

    @Override
    public StructObjectInspector initialize(StructObjectInspector argOIs) throws UDFArgumentException {
        List<String> fieldsName = new ArrayList<>();
        List<ObjectInspector> fieldsOis = new ArrayList<>();
        fieldsName.add("word");
        fieldsOis.add(PrimitiveObjectInspectorFactory.javaStringObjectInspector);
        return ObjectInspectorFactory.getStandardStructObjectInspector(fieldsName,fieldsOis);
    }

    @Override
    public void process(Object[] args) throws HiveException {
        String content = args[0].toString();
        String splitChar = args[1].toString();
        String[] words = content.split(splitChar);
        for (String word : words) {
            outList.clear();
            outList.add(word);
            forward(outList);
        }
    }

    @Override
    public void close() throws HiveException {
    }
}

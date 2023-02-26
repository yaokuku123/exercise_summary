package com.yqj.hive.udf;

import org.apache.hadoop.hive.ql.exec.UDFArgumentException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentLengthException;
import org.apache.hadoop.hive.ql.exec.UDFArgumentTypeException;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.udf.generic.GenericUDF;
import org.apache.hadoop.hive.serde2.objectinspector.ObjectInspector;
import org.apache.hadoop.hive.serde2.objectinspector.primitive.PrimitiveObjectInspectorFactory;

// 1 添加jar包
// add jar linux_jar_path
// 2 创建函数
// create [temporary] function [dbname.]function_name AS class_name;
// 3 移除函数
// drop [temporary] function [if exists] [dbname.]function_name;

// 1. Maven打包上传服务器
// 2. hive
    // 2.1 添加jar包
    // add jar /root/hive_base-1.0-SNAPSHOT.jar;
    // 2.2 创建函数
    // create temporary function my_len as "com.yqj.hive.udf.MyLen";
    // 2.3 使用函数
    // select ename,my_len(ename) from emp;

public class MyLen extends GenericUDF {
    @Override
    public ObjectInspector initialize(ObjectInspector[] objectInspectors) throws UDFArgumentException {
        // 判别参数个数
        if (objectInspectors.length != 1) {
            throw new UDFArgumentLengthException("MyLen function args length error");
        }
        // 判别参数类型
        if (!objectInspectors[0].getCategory().equals(ObjectInspector.Category.PRIMITIVE)) {
            throw new UDFArgumentTypeException(0,"MyLen function args type error");
        }
        // 函数本身返回值为 int，需要返回 int 类型的鉴别器对象
        return PrimitiveObjectInspectorFactory.javaIntObjectInspector;
    }

    @Override
    public Object evaluate(DeferredObject[] deferredObjects) throws HiveException {
        return deferredObjects[0].get().toString().length();
    }

    @Override
    public String getDisplayString(String[] strings) {
        return "";
    }
}

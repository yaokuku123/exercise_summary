package com.yqj.hbase.opt;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

public class HbaseApiTest {

    private static Configuration conf;
    private static Connection connection;
    private static Admin admin;

    static {
        try {
            // 创建配置conf对象
            conf = HBaseConfiguration.create();
            // ZK集群信息
            conf.set("hbase.zookeeper.quorum", "node02,node03,node04");
            // 创建connection对象
            connection = ConnectionFactory.createConnection(conf);
            // 创建admin对象，crud表的对象
            admin = connection.getAdmin();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // DDL 判断表是否存在
    public static boolean isTableExist(String tableName) throws IOException {
        return admin.tableExists(TableName.valueOf(tableName));
    }

    // DDL 创建表
    public static void createTable(String tableName, int cfVersion, String... cfs) throws IOException {
        if (cfs.length <= 0) {
            System.out.println("未传递列族");
            return;
        }
        if (isTableExist(tableName)) {
            System.out.println("表已存在");
            return;
        }
        // 创建表描述器
        HTableDescriptor hTableDescriptor = new HTableDescriptor(TableName.valueOf(tableName));
        // 添加列族
        for (String cf : cfs) {
            // 创建列族描述器
            HColumnDescriptor hColumnDescriptor = new HColumnDescriptor(cf);
            // 给列族设置版本
            hColumnDescriptor.setMaxVersions(cfVersion);
            hTableDescriptor.addFamily(hColumnDescriptor);
        }
        // 创建表
        admin.createTable(hTableDescriptor);
        System.out.println("表" + tableName + "创建成功");
    }

    // DDL 删除表
    public static void deleteTable(String tableName) throws IOException {
        if (!isTableExist(tableName)) {
            System.out.println("表" + tableName + "不存在");
            return;
        }
        // 停用table
        admin.disableTable(TableName.valueOf(tableName));
        // 删除table
        admin.deleteTable(TableName.valueOf(tableName));
        System.out.println("表" + tableName + "已删除");
    }

    // DDL 创建命名空间
    public static void createNamespace(String namespace) {
        NamespaceDescriptor namespaceDescriptor = NamespaceDescriptor.create(namespace).build();
        try {
            admin.createNamespace(namespaceDescriptor);
        } catch (NamespaceExistException e) {
            System.out.println("命名空间已存在");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // DDL 关闭资源
    public static void close() {
        if (admin != null) {
            try {
                admin.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // DML 表插入数据
    public static void putData(String tableName, String rowKey, String cf, String cn, String value) throws IOException {
        // 获取table对象
        Table table = connection.getTable(TableName.valueOf(tableName));
        // 拿rowkey创建put对象
        Put put = new Put(Bytes.toBytes(rowKey));
        // 给put对象赋值
        put.addColumn(Bytes.toBytes(cf), Bytes.toBytes(cn), Bytes.toBytes(value));
        // 插入数据
        table.put(put);
        // 关闭表
        table.close();
    }

    // DML 表获取数据
    public static void getData(String tableName, String rowKey, String cf, String cn, int cfVersion) throws IOException {
        // 获取表对象
        Table table = connection.getTable(TableName.valueOf(tableName));
        // 创建get对象
        Get get = new Get(Bytes.toBytes(rowKey));
        // 指定获取的列族
//        get.addFamily(Bytes.toBytes(cf));
        // 指定获取的列
//        get.addColumn(Bytes.toBytes(cf),Bytes.toBytes(cn));
        // 设置获取的版本
        get.setMaxVersions(cfVersion);
        // 获取数据
        Result result = table.get(get);
        // 遍历cell
        for (Cell cell : result.rawCells()) {
            System.out.println("rowKey: " + Bytes.toString(CellUtil.cloneRow(cell)) +
                    "，cf: " + Bytes.toString(CellUtil.cloneFamily(cell)) +
                    "，column:" + Bytes.toString(CellUtil.cloneQualifier(cell)) +
                    "，value:" + Bytes.toString(CellUtil.cloneValue(cell)) +
                    "，timestamp:" + cell.getTimestamp());
        }
        // 关闭资源
        table.close();
    }

    // DML Scan获取表数据
    public static void scanData(String tableName,String startRow,String stopRow) throws IOException {
        // 获取表对象
        Table table = connection.getTable(TableName.valueOf(tableName));
        // 创建scan对象，左闭右开
        Scan scan = new Scan(Bytes.toBytes(startRow),Bytes.toBytes(stopRow));
        // 扫描表
        ResultScanner tableScanner = table.getScanner(scan);
        // 解析tableScanner
        for (Result result : tableScanner) {
            // 遍历cell
            for (Cell cell : result.rawCells()) {
                System.out.println("rowKey: " + Bytes.toString(CellUtil.cloneRow(cell)) +
                        "，cf: " + Bytes.toString(CellUtil.cloneFamily(cell)) +
                        "，column:" + Bytes.toString(CellUtil.cloneQualifier(cell)) +
                        "，value:" + Bytes.toString(CellUtil.cloneValue(cell)) +
                        "，timestamp:" + cell.getTimestamp());
            }
        }
        table.close();
    }

    // DML 删除表数据
    public static void deleteData(String tableName,String rowKey,String cf,String cn) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tableName));
        // 构建delete对象
        Delete delete = new Delete(Bytes.toBytes(rowKey));
        // 给delete对象赋值 addColumns删除所有版本，addColumn删除最新版本
        delete.addColumns(Bytes.toBytes(cf),Bytes.toBytes(cn));
        table.delete(delete);
        table.close();
    }

    public static void testDDL() throws IOException {
        System.out.println(isTableExist("student2"));
        createTable("student2", 5, "info1", "info2");
        System.out.println(isTableExist("student2"));
        deleteTable("student2");
        System.out.println(isTableExist("student2"));
        createNamespace("myns");
        close();
    }

    public static void testDML() throws IOException {
        putData("student", "1011", "info", "name", "yorick1");
        putData("student", "1011", "info", "sex", "male");
        putData("student", "1011", "info", "age", "25");
        putData("student", "1011", "info", "name", "yorick2");
        putData("student", "1011", "info", "name", "yorick3");
        getData("student","1011","","",3);
        System.out.println("********");
        scanData("student","1000","1012");
        deleteData("student","1011","info","name");
        System.out.println("********");
        scanData("student","1000","1012");
    }

    public static void main(String[] args) throws IOException {
//        testDDL();
        testDML();
    }
}

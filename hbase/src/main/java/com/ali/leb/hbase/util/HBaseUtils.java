package com.ali.leb.hbase.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.filter.SubstringComparator;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NavigableMap;


/**
 * 功能描述:HBase 工具类
 *
 * @Author: darker
 * @Date:
 */
@DependsOn("springContextHolder")
@Component
public class HBaseUtils {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static Connection connection = null;
    private static Admin admin = null;


    static {
        try {
            connection = HBaseThreadPool.getConnection();
            admin = connection.getAdmin();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建表
     *
     * @param tableName    表名
     * @param columnFamily 列族名
     * @throws IOException
     */
    public void createTable(String tableName, String[] columnFamily) {
        TableName tName = TableName.valueOf(tableName);
        try {
            if (admin.tableExists(tName)) {
                logger.error("create htable error! this table {} already exists!", tName);
            } else {
                HTableDescriptor desc = new HTableDescriptor(tName);
                for (String cf : columnFamily) {
                    desc.addFamily(new HColumnDescriptor(cf));
                }
                admin.createTable(desc);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 插入记录（对象）
     *
     * @param tableName    表名
     * @param row          行值
     * @param columnFamily 列族名
     * @param object       对象
     */
    public void insertObject(String tableName, String row, String columnFamily, Object object) {
        if (object == null) {
            throw new IllegalArgumentException("object is null");
        }
        JSONObject jsonObject = ObjectReflect.getObject(object);
        if (jsonObject == null || jsonObject.size() == 0) {
            return;
        }
        List<String> columnList = new ArrayList<>();
        List<String> valueList = new ArrayList<>();
        jsonObject.forEach((key, value) -> {
            columnList.add(key);
            valueList.add(String.valueOf(value));
        });
        insertRecords(tableName, row, columnFamily,
                columnList.toArray(new String[columnList.size()]),
                valueList.toArray(new String[valueList.size()]));
    }

    /**
     * 插入记录（单行单列族-多列多值）
     *
     * @param tableName    表名
     * @param row          行值
     * @param columnFamily 列族名
     * @param columns      列名
     * @param values       列值
     * @throws IOException
     */
    public void insertRecords(String tableName, String row, String columnFamily, String[] columns, String[] values) {
        Table table = getTable(tableName);
        try {
            Put put = new Put(Bytes.toBytes(row));
            for (int i = 0; i < columns.length; i++) {
                put.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(columns[i]), Bytes.toBytes(values[i]));
            }
            table.put(put);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 插入记录（单行单列族-单列单值）
     *
     * @param tableName    表名
     * @param row          行值
     * @param columnFamily 列族名
     * @param column       列名
     * @param value        列值
     * @throws IOException
     */
    public void insertOneRecord(String tableName, String row, String columnFamily, String column, String value) {
        Table table = getTable(tableName);
        try {
            Put put = new Put(Bytes.toBytes(row));
            put.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(column), Bytes.toBytes(value));
            table.put(put);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 删除一行记录
     *
     * @param tableName 表名
     * @param rowKey    行名
     * @throws IOException
     */
    public void deleteRow(String tableName, String rowKey) {
        Table table = getTable(tableName);
        try {
            Delete delete = new Delete(rowKey.getBytes());
            table.delete(delete);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除单行单列族记录
     *
     * @param tableName    表名
     * @param rowKey       行名
     * @param columnFamily 列族名
     * @throws IOException
     */
    public void deleteColumnFamily(String tableName, String rowKey, String columnFamily) {
        Table table = getTable(tableName);
        try {
            Delete delete = new Delete(rowKey.getBytes());
            delete.addFamily(Bytes.toBytes(columnFamily));
            table.delete(delete);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * 删除单行单列族单列记录
     *
     * @param tableName
     * @param rowKey
     * @param columnFamily
     * @param column
     * @throws IOException
     */
    public void deleteColum(String tableName, String rowKey, String columnFamily, String column) {
        Table table = getTable(tableName);
        try {
            Delete delete = new Delete(rowKey.getBytes());
            delete.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(column));
            table.delete(delete);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查找一行记录
     *
     * @param tableName 表名
     * @param rowKey    行名
     * @return string
     * @throws IOException
     */
    public static String selectRow(String tableName, String rowKey) {
        StringBuffer stringBuffer = null;
        try {
            TableName tName = TableName.valueOf(tableName);
            Table table = connection.getTable(tName);
            Get get = new Get(rowKey.getBytes());
            Result result = table.get(get);
            NavigableMap<byte[], NavigableMap<byte[], NavigableMap<Long, byte[]>>> map = result.getMap();
            for (Cell cell : result.rawCells()) {
                stringBuffer = new StringBuffer().append(Bytes.toString(cell.getRowArray())).append("\t")
                        .append(Bytes.toString(cell.getFamilyArray())).append("\t")
                        .append(Bytes.toString(cell.getQualifierArray())).append("\t")
                        .append(Bytes.toString(cell.getValueArray())).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }

    /**
     * 查找单行单列族单列记录
     *
     * @param tableName    表名
     * @param rowKey       行名
     * @param columnFamily 列族名
     * @param column       列名
     * @return
     * @throws IOException
     */
    public static String selectValue(String tableName, String rowKey, String columnFamily, String column) {
        try {
            TableName tName = TableName.valueOf(tableName);
            Table table = connection.getTable(tName);
            Get get = new Get(rowKey.getBytes());
            get.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(column));
            Result result = table.get(get);
            return Bytes.toString(result.value());
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 查询表中所有行（Scan方式）
     *
     * @param tableName
     * @return
     * @throws IOException
     */
    public List<Result> scanAllRecord(String tableName) {
        List<Result> listResult = new ArrayList<>();
        Table table = getTable(tableName);
        try {
            String record = "";
            Scan scan = new Scan();
            ResultScanner scanner = table.getScanner(scan);
            try {
                for (Result result : scanner) {
                    listResult.add(result);
                }
            } finally {
                if (scanner != null) {
                    scanner.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listResult;
    }

    /**
     * 根据rowkey关键字查询报告记录
     *
     * @param tableName  表名
     * @param rowKeyword 行名
     * @return
     * @throws IOException
     */
    public List scanReportDataByRowKeyword(String tableName, String rowKeyword) {
        ArrayList<String> list = new ArrayList<String>();
        Table table = getTable(tableName);
        try {
            Scan scan = new Scan();
            //添加行键过滤器，根据关键字匹配
            getResultToString(rowKeyword, list, table, scan);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }


    /**
     * 根据rowkey 关键字和时间戳范围查询报告记录
     *
     * @param tableName  表名
     * @param rowKeyword 行名
     * @param minStamp   时间戳最小值
     * @param maxStamp   时间戳最大值
     * @return
     * @throws IOException
     */
    public List scanReportDataByRowKeywordTimestamp(String tableName, String rowKeyword, Long minStamp, Long maxStamp) {
        ArrayList<String> list = new ArrayList<String>();
        Table table = getTable(tableName);
        try {
            Scan scan = new Scan();
            //添加scan的时间范围
            scan.setTimeRange(minStamp, maxStamp);
            getResultToString(rowKeyword, list, table, scan);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void getResultToString(String rowKeyword, ArrayList<String> list, Table table, Scan scan) throws IOException {
        RowFilter rowFilter = new RowFilter(CompareFilter.CompareOp.EQUAL, new SubstringComparator(rowKeyword));
        scan.setFilter(rowFilter);

        ResultScanner scanner = table.getScanner(scan);
        try {
            for (Result result : scanner) {
                for (Cell cell : result.rawCells()) {
                    StringBuffer stringBuffer = new StringBuffer().append(Bytes.toString(cell.getRowArray())).append("\t")
                            .append(Bytes.toString(cell.getFamilyArray())).append("\t")
                            .append(Bytes.toString(cell.getQualifierArray())).append("\t")
                            .append(Bytes.toString(cell.getValueArray())).append("\n");
                    String str = stringBuffer.toString();
                    list.add(str);
                }
            }
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    /**
     * 在HBase上面创建表
     *
     * @param tableName    表名
     * @param columnFamily 列族名（可以同时传入多个列族名）
     * @return
     */
    public boolean createTableCopy(String tableName, String[] columnFamily) {

        try {
            TableName tname = TableName.valueOf(tableName);
            HTableDescriptor tableDescriptor = new HTableDescriptor(tname);
            for (String cf : columnFamily) {
                tableDescriptor.addFamily(new HColumnDescriptor(cf));
            }
            admin.createTable(tableDescriptor);
            return admin.tableExists(tname);
        } catch (MasterNotRunningException e) {
            e.printStackTrace();
        } catch (ZooKeeperConnectionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 返回根据传入的实体类的list
     *
     * @param tableName 表名
     * @param c         实体类
     * @param rowKeys   行名
     * @param <T>       泛型
     * @return
     * @throws IOException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public <T> List<T> searchAll(Class<T> c, String tableName, List<String> rowKeys) {

        List<T> list = new ArrayList<T>();
        Table table = getTable(tableName);
        try {
            List<Get> getList = new ArrayList<>();

            for (String rowKey : rowKeys) {
                getList.add(new Get(Bytes.toBytes(rowKey)));
            }

            Result[] results = table.get(getList);
            for (Result result : results) {
                if (result.getRow() == null) {
                    continue;
                }
                T item = c.newInstance();
                item = JSONObject.parseObject(paseJSONObject(result).toJSONString(), c);
                list.add(item);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 查询单个对象
     *
     * @param tableName
     * @param c
     * @param rowKeys
     * @param <T>
     * @return
     */
    public <T> T selectOne(String tableName, Class<T> c, String rowKeys) {
        List<T> list = new ArrayList<T>();
        Table table = getTable(tableName);
        try {
            T item = c.newInstance();
            Result result = table.get(new Get(Bytes.toBytes(rowKeys)));
            item = JSONObject.parseObject(paseJSONObject(result).toJSONString(), c);
            return item;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (InstantiationException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将Result 结果转换为jsonobject对象
     *
     * @param result
     * @return
     */
    public JSONObject paseJSONObject(Result result) {
        List<Cell> ceList = result.listCells();
        JSONObject obj = new JSONObject();
        if (ceList != null && ceList.size() > 0) {
            for (Cell cell : ceList) {
                obj.put(Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(),
                        cell.getQualifierLength()),
                        Bytes.toString(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength()));
            }
        } else {
            return null;
        }
        return obj;
    }

    /**
     * 获取Table
     *
     * @param tableName
     * @return
     */
    public Table getTable(String tableName) {
        Table table = null;
        try {
            TableName tName = TableName.valueOf(tableName);
            table = connection.getTable(tName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return table;
    }


    /**
     * 插入记录
     *
     * @param tb
     * @param rowKey
     * @param cf
     * @param columns
     * @param values
     */
    @Deprecated
    public void put(String tb, String rowKey, String cf, List<String> columns, List<String> values) {
        try {
            TableName tname = TableName.valueOf(tb);
            Table table = connection.getTable(tname);
            Put put = new Put(Bytes.toBytes(rowKey));
            for (int i = 0; i < columns.size(); i++) {
                put.addColumn(Bytes.toBytes(cf),
                        Bytes.toBytes(columns.get(i)),
                        Bytes.toBytes(values.get(i)));
            }
            table.put(put);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 插入记录
     *
     * @param tb  表名
     * @param put 数据put对象
     */
    public void put(String tb, Put put) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tb));
        table.put(put);
        table.close();
    }

    /**
     * 插入记录 多个对象插入
     *
     * @param tb
     * @param puts
     * @throws IOException
     */
    public void put(String tb, List<Put> puts) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tb));
        table.put(puts);
        table.close();
    }

    /**
     * 删除数据
     *
     * @param tb     表名
     * @param delete 数据delete对象
     */
    public void delete(String tb, Delete delete) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tb));
        table.delete(delete);
        table.close();
    }

    /**
     * 删除数据 删除多条数据
     *
     * @param tb      表名
     * @param deletes 数据delete对象集合
     */
    public void delete(String tb, List<Delete> deletes) throws IOException {
        Table table = connection.getTable(TableName.valueOf(tb));
        table.delete(deletes);
        table.close();
    }

    /**
     * 获取数据
     *
     * @param tb  表名
     * @param get 数据get对象
     * @return
     */
    public Result get(String tb, Get get) {
        Result result = null;
        try {
            Table table = connection.getTable(TableName.valueOf(tb));
            result = table.get(get);
            table.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取数据
     *
     * @param tb   表名
     * @param gets 数据gets对象
     * @return
     */
    public List<Result> get(String tb, List<Get> gets) {
        List<Result> results = null;
        try {
            Table table = connection.getTable(TableName.valueOf(tb));
            results = Arrays.asList(table.get(gets));
            table.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return results;
    }

    /**
     * 批量删除
     *
     * @param tb
     * @param rowKeys
     * @param cf
     */
    public void delete(String tb, List<String> rowKeys, String cf) {
        try {
            TableName tname = TableName.valueOf(tb);
            Table table = connection.getTable(tname);
            List<Delete> deletes = new ArrayList<>();
            for (int i = 0; i < rowKeys.size(); i++) {
                byte[] row = Bytes.toBytes(rowKeys.get(i));
                Delete delete = new Delete(row);
                delete.addFamily(Bytes.toBytes(cf));
                deletes.add(delete);
            }
            table.delete(deletes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * truncate 表
     *
     * @param tb 表名
     * @throws IOException
     */
    public void truncateTable(String tb) throws IOException {
        // 取得目标数据表的表名对象
        TableName tname = TableName.valueOf(tb);
        // 设置表状态为无效
        admin.disableTable(tname);
        // 清空指定表的数据
        admin.truncateTable(tname, true);
        // 设置表状态为有效
        admin.enableTable(tname);
    }
}

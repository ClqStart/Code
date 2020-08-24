package mashibing;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.PrefixFilter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 如果遇到特别慢的情况，设置DNS服务器地址
 * 223.5.5.5
 * 223.6.6.6
 */

public class HBaseDeam {
    Configuration conf = null;
    Connection conn = null;
    Admin admin = null;
    //表名
    TableName tableName = TableName.valueOf("phone");
    //操作对象
    Table table;

    @Before
    public void init() throws IOException {
        //创建配置文件对象
        conf = HBaseConfiguration.create();
        //加载zookerper的配置
        conf.set("hbase.zookeeper.quorum", "node102,node103,node104");
        //获取连接
        conn = ConnectionFactory.createConnection(conf);

        //获取管理员进行操作
        admin = conn.getAdmin();
        //获取数据操作对象
        table = conn.getTable(tableName);
    }

    @Test
    public void createTable() throws IOException {
        //定义表
        TableDescriptorBuilder tableDescriptorBuilder = TableDescriptorBuilder.newBuilder(tableName);
        //定义列簇
        ColumnFamilyDescriptorBuilder columnFamilyDescriptorBuilder = ColumnFamilyDescriptorBuilder.newBuilder("cf".getBytes());
        //对表进行设置列簇
        tableDescriptorBuilder.setColumnFamily(columnFamilyDescriptorBuilder.build());
        //创建表
        if (admin.tableExists(tableName)) {
            admin.disableTable(tableName);
            admin.deleteTable(tableName);
        }
        admin.createTable(tableDescriptorBuilder.build());
    }

    @Test
    public void insert() throws IOException {
        Put put = new Put(Bytes.toBytes("111"));
        put.addColumn(Bytes.toBytes("cf"), Bytes.toBytes("name"), Bytes.toBytes("张山"));
        put.addColumn(Bytes.toBytes("cf"), Bytes.toBytes("age"), Bytes.toBytes("11"));
        put.addColumn(Bytes.toBytes("cf"), Bytes.toBytes("sex"), Bytes.toBytes("男"));
        table.put(put);
    }
    @Test
    public  void get() throws IOException {
        Get get = new Get(Bytes.toBytes("111"));
        get.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("name"));
        get.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("age"));
        get.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("sex"));
        Result result = table.get(get);
        Cell cell = result.getColumnLatestCell(Bytes.toBytes("cf"), Bytes.toBytes("name"));
        Cell cell1 = result.getColumnLatestCell(Bytes.toBytes("cf"), Bytes.toBytes("age"));
        Cell cell2 = result.getColumnLatestCell(Bytes.toBytes("cf"), Bytes.toBytes("sex"));
        System.out.println(Bytes.toString(CellUtil.cloneValue(cell)));
        System.out.println(Bytes.toString(CellUtil.cloneValue(cell1)));
        System.out.println(Bytes.toString(CellUtil.cloneValue(cell2)));
    }
    @Test
    public  void scan() throws IOException {
        Scan scan = new Scan();
        ResultScanner results = table.getScanner(scan);
        for (Result result : results) {
            Cell cell = result.getColumnLatestCell(Bytes.toBytes("cf"), Bytes.toBytes("name"));
            Cell cell1 = result.getColumnLatestCell(Bytes.toBytes("cf"), Bytes.toBytes("age"));
            Cell cell2 = result.getColumnLatestCell(Bytes.toBytes("cf"), Bytes.toBytes("sex"));
            System.out.println(Bytes.toString(CellUtil.cloneValue(cell)));
            System.out.println(Bytes.toString(CellUtil.cloneValue(cell1)));
            System.out.println(Bytes.toString(CellUtil.cloneValue(cell2)));
        }
    }

    @Test
    public  void scanByCondition() throws IOException, ParseException {
        Scan scan = new Scan();
        String  startRow = "15811664638_"+(Long.MAX_VALUE-sdf.parse("20200331000000").getTime());
        String  stopRow = "15811664638_"+(Long.MAX_VALUE-sdf.parse("20200301000000").getTime());
        scan.withStartRow(Bytes.toBytes(startRow));
        scan.withStopRow(Bytes.toBytes(stopRow));
        ResultScanner results = table.getScanner(scan);
        for (Result result : results) {
            System.out.print(Bytes.toString(CellUtil.cloneValue(result.getColumnLatestCell(Bytes.toBytes("cf"),Bytes.toBytes("dnum")))));
            System.out.print("--"+Bytes.toString(CellUtil.cloneValue(result.getColumnLatestCell(Bytes.toBytes("cf"),Bytes.toBytes("length")))));
            System.out.print("--"+Bytes.toString(CellUtil.cloneValue(result.getColumnLatestCell(Bytes.toBytes("cf"),Bytes.toBytes("date")))));
            System.out.println("--"+Bytes.toString(CellUtil.cloneValue(result.getColumnLatestCell(Bytes.toBytes("cf"),Bytes.toBytes("type")))));

        }

    }

    @Test
    public  void getType() throws IOException {
        Scan scan = new Scan();
        FilterList filterList = new FilterList(FilterList.Operator.MUST_PASS_ALL);
        SingleColumnValueFilter filter = new SingleColumnValueFilter(Bytes.toBytes("cf"), Bytes.toBytes("type"), CompareOperator.EQUAL, Bytes.toBytes("1"));
        filterList.addFilter(filter);
        PrefixFilter filter1 = new PrefixFilter(Bytes.toBytes("15811664638"));
        filterList.addFilter(filter1);
        scan.setFilter(filterList);
        ResultScanner results = table.getScanner(scan);
        for (Result result : results) {
            System.out.print(Bytes.toString(CellUtil.cloneValue(result.getColumnLatestCell(Bytes.toBytes("cf"),Bytes.toBytes("dnum")))));
            System.out.print("--"+Bytes.toString(CellUtil.cloneValue(result.getColumnLatestCell(Bytes.toBytes("cf"),Bytes.toBytes("length")))));
            System.out.print("--"+Bytes.toString(CellUtil.cloneValue(result.getColumnLatestCell(Bytes.toBytes("cf"),Bytes.toBytes("date")))));
            System.out.println("--"+Bytes.toString(CellUtil.cloneValue(result.getColumnLatestCell(Bytes.toBytes("cf"),Bytes.toBytes("type")))));
        }
    }

    /**
     * 造数据
     */
    @Test
    public void  insertByScan() throws ParseException, IOException {
        List<Put> puts = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String  phoneNumber = getNumber("158");
            for (int j = 0; j < 1000; j++) {
                String dnum = getNumber("177");
                String  length = String.valueOf(random.nextInt(100));
                String  date = getDate("2020");
                String  type= String.valueOf(random.nextInt(2));

                //rowKey
                String  rowkey = phoneNumber+"_"+(Long.MAX_VALUE-sdf.parse(date).getTime());
                Put put = new Put(Bytes.toBytes(rowkey));
                put.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("dnum"),Bytes.toBytes(dnum));
                put.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("length"),Bytes.toBytes(length));
                put.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("date"),Bytes.toBytes(date));
                put.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("type"),Bytes.toBytes(type));

                puts.add(put);
            }
            table.put(puts);
        }
    }
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

    private String getDate(String s) {
        return s+ String.format("%02d%02d%02d%02d%02d",random.nextInt(12)+1,random.nextInt(31),random.nextInt(24),random.nextInt(60),random.nextInt(60));

    }

    Random random=new Random();
    public  String   getNumber(String str){
        return str+String.format("%08d",random.nextInt(99999999));
    }


    @Test
    public void insertByProtoBuf() throws ParseException, IOException {
        List<Put> puts = new ArrayList<>();
        for(int i = 0;i<10;i++){
            String phoneNumber = getNumber("158");
            for(int j = 0 ;j<1000;j++){
                String dnum = getNumber("177");
                String length = String.valueOf(random.nextInt(100));
                String date = getDate("2020");
                String type = String.valueOf(random.nextInt(2));
                //rowkey
                String rowkey = phoneNumber+"_"+(Long.MAX_VALUE-sdf.parse(date).getTime());

                Phone.PhoneDetail.Builder builder = Phone.PhoneDetail.newBuilder();
                builder.setDate(date);
                builder.setDnum(dnum);
                builder.setLength(length);
                builder.setType(type);
                Put put = new Put(Bytes.toBytes(rowkey));
                put.addColumn(Bytes.toBytes("cf"),Bytes.toBytes("phone"),builder.build().toByteArray());
                puts.add(put);
            }
        }
        table.put(puts);
    }

    @Test
    public void getByProtoBuf() throws IOException {
        Get get = new Get("15891786859_9223370459111400807".getBytes());
        Result rs = table.get(get);
        byte[] b = CellUtil.cloneValue(rs.getColumnLatestCell(Bytes.toBytes("cf"),Bytes.toBytes("phone")));
        Phone.PhoneDetail phoneDetail = Phone.PhoneDetail.parseFrom(b);
        System.out.println(phoneDetail);
    }


    @Test
    public  void delete() throws IOException {
        Delete delete = new Delete("111".getBytes());
        table.delete(delete);
    }

    @After
    public void desory() throws IOException {
        table.close();
        admin.close();
        conn.close();
    }
}

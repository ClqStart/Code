package hadoop.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.net.URI;

public class TestHDFS {

    public Configuration conf = null;
    public FileSystem fs= null;

    @Before
    public  void conn() throws IOException, InterruptedException {
        conf = new Configuration(true);
        //抽象类，参考core-site.xml返回系统类型
        //fs = FileSystem.get(conf);

        fs= FileSystem.get(URI.create("hdfs://mycluster"),conf,"god");

    }

    @Test
    public  void  mkdir() throws IOException {
        Path dir=new Path("/user/masb");
        if(fs.exists(dir)){
            fs.delete(dir,true);
        }
        fs.mkdirs(dir);

    }

    @Test
    public  void upload() throws IOException {
        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(new File("./data/hello.txt")));

        Path outfile = new Path("/user/masb/out.txt");

        FSDataOutputStream outputStream = fs.create(outfile);

        IOUtils.copyBytes(inputStream,outputStream,conf);

    }

    @Test
    public  void block() throws IOException {
        Path path = new Path("/user/god/data.txt");
        FileStatus fss = fs.getFileStatus(path);
        BlockLocation[] blocks = fs.getFileBlockLocations(fss, 0, fss.getLen());
        for (BlockLocation block : blocks) {
            System.out.println(block);
        }
        //0,1048576,node105,node102
        //1048576,540319,node105,node102

        FSDataInputStream in = fs.open(path); //面向文件打开的输入流，无论如何都要重文件开始读起

        //计算向数据移动后，期望的是分治，只读取自己关心（通过seek实现），同时，具备距离的概念（框架默认）
        in.seek(1048576);
        System.out.println((char) in.readByte());
        System.out.println((char) in.readByte());
        System.out.println((char) in.readByte());
        System.out.println((char) in.readByte());
        System.out.println((char) in.readByte());
        System.out.println((char) in.readByte());
    }



    @After
    public  void close() throws IOException {
        fs.close();
    }
}

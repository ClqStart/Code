package mashibing.wc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

public class WCRunner {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration(true);
        conf.set("hbase.zookeeper.quorum","node104,node102,node103");
        conf.set("mapreduce.app-submission.cross-platform","true");
//        conf.set("mapreduce.framework.name","local");

        //创建job对象
        Job job = Job.getInstance(conf);
        job.setJar("G:\\study\\code\\java\\bigdata\\hbase\\target\\hbase-1.0-SNAPSHOT.jar");
        job.setJarByClass(WCRunner.class);

        //设置mapper类
        job.setMapperClass(WcMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        //设置reduce类
//        job.setReducerClass();
//        TableMapReduceUtil.initTableMapperJob();
        TableMapReduceUtil.initTableReducerJob("wc",WcReducer.class,job,null,null,null,null,true);
        job.setOutputKeyClass(NullWritable.class);
        job.setOutputValueClass(Put.class);

        //指定hdfs存储数据的目录
        FileInputFormat.addInputPath(job,new Path("/data/fof/input"));
        job.waitForCompletion(true);
    }
}


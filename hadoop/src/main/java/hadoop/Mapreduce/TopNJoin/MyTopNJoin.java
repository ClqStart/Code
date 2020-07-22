package hadoop.Mapreduce.TopNJoin;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import java.io.IOException;

public class MyTopNJoin {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        Configuration conf = new Configuration(true);
        conf.set("mapreduce.app-submission.cross-platform","true");

        String[] other = new GenericOptionsParser(conf, args).getRemainingArgs();

        Job job = Job.getInstance(conf);
        job.setJobName("TopNJoin");

        job.addCacheFile(new Path("/data/topn/dick.txt").toUri());
        job.setJar("D:\\java\\bigdata\\hadoop\\target\\hadoop-1.0-SNAPSHOT.jar");


        TextInputFormat.addInputPath(job,new Path(other[0]));

        Path output = new Path(other[1]);

        if(output.getFileSystem(conf).exists(output)) output.getFileSystem(conf).delete(output,true);
        TextOutputFormat.setOutputPath(job,output);

        job.setMapperClass(TJMapper.class);
        job.setMapOutputKeyClass(TJKey.class);
        job.setMapOutputValueClass(IntWritable.class);


        job.setPartitionerClass(TJPartitioner.class);

        job.setSortComparatorClass(TJSortComparator.class);

        job.setGroupingComparatorClass(TJGroupingComparator.class);

        job.setReducerClass(TJReducer.class);

        job.waitForCompletion(true);



    }
}

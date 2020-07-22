package hadoop.Mapreduce.wc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import java.io.IOException;

public class MyWordCount {
    //bin/hadoop command [genericOptions] [commandOptions]
    //    hadoop jar  ooxx.jar  ooxx   -D  ooxx=ooxx  inpath  outpath
    //  args :   2类参数  genericOptions   commandOptions
    //  人你有复杂度：  自己分析 args数组

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        Configuration conf = new Configuration(true);

        GenericOptionsParser parser = new GenericOptionsParser(conf, args);
        String[] otherargs = parser.getRemainingArgs();

        //客户端是window必须设置
        conf.set("mapreduce.app-submission.cross-platform","true"); //windows上必须配

        Job job = Job.getInstance(conf);

        job.setJar("D:\\java\\bigdata\\hadoop\\target\\hadoop-1.0-SNAPSHOT.jar");
        // Create a new Job
//        Job job = Job.getInstance();
        job.setJarByClass(MyWordCount.class);

        // Specify various job-specific parameters
        job.setJobName("mashibing");

//        job.setInputPath(new Path("in"));
//        job.setOutputPath(new Path("out"));

        Path infile = new Path("/data/wc/input");
        TextInputFormat.addInputPath(job,infile);

        Path outfile = new Path("/data/wc/output");
        if(outfile.getFileSystem(conf).exists(outfile)) {
            outfile.getFileSystem(conf).delete(outfile,true);
        }
        TextOutputFormat.setOutputPath(job,outfile);


        job.setMapperClass(MyMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setReducerClass(MyReducer.class);

        // Submit the job, then poll for progress until the job is complete
        job.waitForCompletion(true);
    }

}

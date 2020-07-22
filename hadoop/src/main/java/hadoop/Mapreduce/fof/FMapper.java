package hadoop.Mapreduce.fof;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.util.StringUtils;

import java.io.IOException;

public class FMapper extends Mapper<LongWritable, Text,Text, IntWritable> {

    Text mkey = new Text();
    IntWritable mvalue = new IntWritable();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String[] strs = StringUtils.split(value.toString(), ' ');
        for (int i = 1; i < strs.length ; i++) {
            String s = getFof(strs[0], strs[i]);
            mkey.set(s);
            mvalue.set(0);
            context.write(mkey,mvalue);
            for (int j = i+1; j <strs.length ; j++) {
                mkey.set(getFof(strs[i],strs[j]));
                mvalue.set(1);
                context.write(mkey,mvalue);
            }

        }

    }
    public static  String  getFof(String s1, String s2){
        if(s1.compareTo(s2)>0){
            return s1+"-"+s2;
        }else {
            return  s2+"-"+s1;
        }

    }
}

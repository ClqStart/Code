package hadoop.Mapreduce.fof;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class FReducer extends Reducer<Text, IntWritable,Text,IntWritable> {

    IntWritable rval =new IntWritable();
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {

        int flg =0;
        int sum =0;
        for (IntWritable value : values) {
            //只要出现0表示直接关系
            if(value.get() ==0){
                flg =1;
            }
            sum += value.get();

        }
        //没有出现就是间接关系，获得推荐分数
        if(flg==0){
            rval.set(sum);
            context.write(key,rval);
        }

    }
}

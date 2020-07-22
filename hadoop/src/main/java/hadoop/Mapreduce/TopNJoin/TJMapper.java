package hadoop.Mapreduce.TopNJoin;


import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.util.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class TJMapper extends Mapper<LongWritable, Text, TJKey, IntWritable> {

    TJKey mkey =  new  TJKey();
    IntWritable mval = new IntWritable();

    public HashMap<String,String> dick = new HashMap<>();

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        URI[] files = context.getCacheFiles();
        Path path = new Path(files[0].getPath());

        BufferedReader reader = new BufferedReader(new FileReader(path.getName()));

        String line = reader.readLine();

        while (line !=null){
            String[] split = line.split("\t");
            dick.put(split[0],split[1]);
            line = reader.readLine();
        }

    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] strs = StringUtils.split(value.toString(), '\t');
        //2019-6-1 22:22:22   /   1    /   31
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(strs[0]);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            mkey.setYear(cal.get(Calendar.YEAR));
            mkey.setMonth(cal.get(Calendar.MONTH )+1);
            mkey.setDay(cal.get(Calendar.DAY_OF_MONTH));
            int wd = Integer.parseInt(strs[2]);
            mkey.setWd(wd);
            mkey.setLocation(dick.get(strs[1]));
            mval.set(wd);

            context.write(mkey,mval);


        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}

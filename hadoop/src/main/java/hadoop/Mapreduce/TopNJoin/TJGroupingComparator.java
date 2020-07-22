package hadoop.Mapreduce.TopNJoin;


import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class TJGroupingComparator extends WritableComparator {

    public  TJGroupingComparator(){
        super(TJKey.class,true );
    }
    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        TJKey k1 = (TJKey)a;
        TJKey k2 = (TJKey)b;
        //  按着 年，月分组
        int c1 = Integer.compare(k1.getYear(), k2.getYear());
        if(c1 == 0 ){
            return  Integer.compare(k1.getMonth(), k2.getMonth());

        }

        return c1;


    }
}

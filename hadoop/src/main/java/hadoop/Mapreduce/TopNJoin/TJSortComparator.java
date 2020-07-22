package hadoop.Mapreduce.TopNJoin;

import hadoop.Mapreduce.TopN.TKey;
import org.apache.hadoop.io.RawComparator;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class TJSortComparator extends WritableComparator {

    public TJSortComparator(){
        super(TKey.class,true);
    }


    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        TKey k1 = (TKey)a;
        TKey k2 = (TKey)b;
        //  年，月，温度，，且温度倒序：
        int c1 = Integer.compare(k1.getYear(), k2.getYear());
        if(c1 == 0 ){
            int c2 = Integer.compare(k1.getMonth(), k2.getMonth());
            if(c2 == 0){
                return  - Integer.compare(k1.getWd(),k2.getWd());
            }
            return c2;
        }

        return c1;


    }
}
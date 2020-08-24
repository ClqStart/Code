package com.bigdata;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import scala.Tuple2;

import java.util.Arrays;
import java.util.Iterator;

public class javaWordCount {
    public static void main(String[] args) {

        SparkConf conf = new SparkConf();
        conf.setAppName("javawc");
        conf.setMaster("local");

        JavaSparkContext context = new JavaSparkContext(conf);

        JavaRDD<String> javaRDD = context.textFile("data/testdata.txt");
        JavaRDD<String> words = javaRDD.flatMap(new FlatMapFunction<String, String>() {
            public Iterator<String> call(String line) throws Exception {
                return Arrays.asList(line.split(" ")).listIterator();
            }
        });
        JavaPairRDD<String, Integer> pairRDD = words.mapToPair(new PairFunction<String, String, Integer>() {
            public Tuple2<String, Integer> call(String w) throws Exception {
                return new Tuple2<String, Integer>(w, 1);
            }
        });
        JavaPairRDD<String, Integer> res = pairRDD.reduceByKey(new Function2<Integer, Integer, Integer>() {
            public Integer call(Integer oldv, Integer v) throws Exception {
                return oldv + v;
            }
        });
        res.foreach(new VoidFunction<Tuple2<String, Integer>>() {
            public void call(Tuple2<String, Integer> Value) throws Exception {
                System.out.println(Value._1+"\t"+Value._2);
            }
        });

    }
}

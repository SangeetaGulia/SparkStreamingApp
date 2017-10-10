package com.knoldus.utils;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaStreamingContext;

public class StreamingUtil {

    public static JavaStreamingContext getStreamingContext() {
        Duration time = new Duration(1000);
        SparkConf config = new SparkConf().setMaster("local[*]").setAppName("twitter-stream-sentiment");
        JavaSparkContext sparkContext = new JavaSparkContext(config);
        sparkContext.setLogLevel("WARN");
        JavaStreamingContext streamingContext = new JavaStreamingContext(sparkContext, time);
        return streamingContext;
    }
}

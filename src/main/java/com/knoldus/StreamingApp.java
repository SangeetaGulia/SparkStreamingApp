package com.knoldus;

import com.knoldus.utils.StreamingUtil;
import com.knoldus.utils.TwitterUtil;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.twitter.TwitterUtils;
import twitter4j.HashtagEntity;
import twitter4j.Status;

import java.util.Arrays;

public class StreamingApp {

    public static void main(String[] args) {
        TwitterUtil.setupTwitter();
        JavaStreamingContext streamingContext = StreamingUtil.getStreamingContext();
        JavaReceiverInputDStream<Status> stream = TwitterUtils.createStream(streamingContext);
        System.out.println("---------------------------------Print the streams --------------------------------");
        stream.print(10);

        System.out.println("---------------------------------Print HashTagStreams -----------------------------");
        JavaDStream<HashtagEntity> hashTagStream = stream.flatMap(status -> Arrays.asList(status.getHashtagEntities()));
        hashTagStream.print();

        streamingContext.start();
        streamingContext.awaitTermination();
    }

}

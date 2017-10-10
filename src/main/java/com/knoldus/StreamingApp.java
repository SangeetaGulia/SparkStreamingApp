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
        /*System.out.println("---------------------------------Print the streams --------------------------------");
        stream.print(10);*/

        System.out.println("---------------------------------Print HashTagStreams -----------------------------");
        JavaDStream<HashtagEntity> hashTagStream = stream.flatMap(status -> Arrays.asList(status.getHashtagEntities()));
//        hashTagStream.filter()
        hashTagStream.print();

        System.out.println("---------------------------------Print FilteredStreams -----------------------------");
        JavaDStream<Status> filteredStream = stream.filter(dataStream ->
                dataStream.getText().toLowerCase().contains("#bigdata") ||
                        dataStream.getText().toLowerCase().contains("#hadoop") ||
                        dataStream.getText().toLowerCase().contains("#lagom") ||
                        dataStream.getText().toLowerCase().contains("#akka") ||
                        dataStream.getText().toLowerCase().contains("#microservice") ||
                        dataStream.getText().toLowerCase().contains("#analytics") ||
                        dataStream.getText().toLowerCase().contains("#spark"));
        filteredStream.print();
        streamingContext.start();
        streamingContext.awaitTermination();
    }

}

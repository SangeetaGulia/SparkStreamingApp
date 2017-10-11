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
import java.util.Date;

public class StreamingApp {

    private static void saveTrendingTweets(JavaReceiverInputDStream<Status> stream) {
        JavaDStream<HashtagEntity> tagStream = stream.flatMap(status -> {
            status.getText().replaceAll("[^a-z A-Z1-9]+", "");
            System.out.println(Arrays.asList(status.getHashtagEntities()));
            return Arrays.asList(status.getHashtagEntities());
        });

        tagStream.countByValue().foreachRDD(rdd -> {
            Date now = new Date();
            rdd.sortByKey().saveAsTextFile("./target/tagdata"+now);
            return null;
        });
    }

    public static void main(String[] args) {
        TwitterUtil.setupTwitter();
        JavaStreamingContext streamingContext = StreamingUtil.getStreamingContext();
        JavaReceiverInputDStream<Status> stream = TwitterUtils.createStream(streamingContext);

        saveTrendingTweets(stream);

        /*System.out.println("---------------------------------Print FilteredStreams -----------------------------");
        JavaDStream<Status> filteredStream = stream.filter(dataStream ->
                dataStream.getText().toLowerCase().contains("#bigdata") ||
                        dataStream.getText().toLowerCase().contains("#Hadoop") ||
                        dataStream.getText().toLowerCase().contains("#Lagom") ||
                        dataStream.getText().toLowerCase().contains("#Akka") ||
                        dataStream.getText().toLowerCase().contains("#microservice") ||
                        dataStream.getText().toLowerCase().contains("#analytics") ||
                        dataStream.getText().toLowerCase().contains("#Scala") ||
                        dataStream.getText().toLowerCase().contains("#Java9") ||
                        dataStream.getText().toLowerCase().contains("#MakeInIndia") ||
                        dataStream.getText().toLowerCase().contains("#DigitalIndia") ||
                        dataStream.getText().toLowerCase().contains("#BREAKING") ||
                        dataStream.getText().toLowerCase().contains("#IndianArmy") ||
                        dataStream.getText().toLowerCase().contains("#Reactive") ||
                        dataStream.getText().toLowerCase().contains("#Bachchan") ||
                        dataStream.getText().toLowerCase().contains("#Spark"));
        filteredStream.print();*/

        streamingContext.start();
        streamingContext.awaitTermination();
    }

}

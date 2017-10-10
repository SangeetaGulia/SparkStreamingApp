package com.knoldus.utils;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

 public class ConfigReader {

    static Config conf = ConfigFactory.load();

    public static String getTwitterConsumerKey() {
        System.out.println(conf.getString("twitter.consumerKey"));
        return conf.getString("twitter.consumerKey");
    }

    public static String getTwitterConsumerSecretKey() {
        return conf.getString("twitter.consumerSecret");
    }

    public static String getTwitterAccessToken() {
        return conf.getString("twitter.accessToken");
    }

    public static String getTwitterAccessSecretToken() {
        return conf.getString("twitter.accessTokenSecret");
    }

/*    public String getKafkaServers() {
        return conf.getString("kafka.servers");
    }

    public String getKafkaTopic() {
        return conf.getString("kafka.topic");
    }*/

}

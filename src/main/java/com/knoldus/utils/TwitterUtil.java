package com.knoldus.utils;


public class TwitterUtil {

    public static void setupTwitter() {
        System.setProperty("twitter4j.oauth.consumerKey", ConfigReader.getTwitterConsumerKey());
        System.setProperty("twitter4j.oauth.consumerSecret", ConfigReader.getTwitterConsumerSecretKey());
        System.setProperty("twitter4j.oauth.accessToken", ConfigReader.getTwitterAccessToken());
        System.setProperty("twitter4j.oauth.accessTokenSecret", ConfigReader.getTwitterAccessSecretToken());
    }
}

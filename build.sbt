name := "SparkStreamingApp"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++=  Seq(
    "org.apache.spark" % "spark-core_2.11" % "1.5.2",
    "org.apache.spark" % "spark-streaming_2.11" % "1.5.2",
    "org.apache.spark" % "spark-streaming-twitter_2.11" % "1.5.2",
    "com.typesafe" % "config" % "1.3.1",
    "com.google.inject" % "guice" % "3.0",
    "edu.stanford.nlp" % "stanford-corenlp" % "3.5.1",                                         // Sentiment Analysis
    "edu.stanford.nlp" % "stanford-corenlp" % "3.5.1" classifier "models"                      // Sentiment Analysis
)
    
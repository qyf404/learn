package com.qyf404.learn.akka;


import akka.Done;
import akka.NotUsed;
import akka.actor.ActorSystem;
import akka.stream.ActorMaterializer;
import akka.stream.IOResult;
import akka.stream.Materializer;
import akka.stream.ThrottleMode;
import akka.stream.javadsl.FileIO;
import akka.stream.javadsl.Flow;
import akka.stream.javadsl.Keep;
import akka.stream.javadsl.Sink;
import akka.stream.javadsl.Source;
import akka.util.ByteString;
import scala.concurrent.duration.Duration;

import java.io.File;
import java.math.BigInteger;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;


public class SampleAkka {
    public static void main(String[] args) {
        four();
    }


    public static void one() {
        final ActorSystem system = ActorSystem.create("QuickStart");
        final Materializer materializer = ActorMaterializer.create(system);

        final Source<Integer, NotUsed> source = Source.range(1, 100);
        source.runForeach(i ->
                System.out.println(Thread.currentThread().getId() + ":" + i
                ), materializer);

//        source.async().runForeach(i ->
//                System.out.println(Thread.currentThread().getId() + ":" + i
//                ), materializer);

        System.out.println(Thread.currentThread().getId());
    }


    public static void two() {
        final ActorSystem system = ActorSystem.create("QuickStart");
        final Materializer materializer = ActorMaterializer.create(system);

        final Source<Integer, NotUsed> source = Source.range(1, 100);

        final Source<BigInteger, NotUsed> factorials =
                source.scan(BigInteger.ONE, (acc, next) -> acc.multiply(BigInteger.valueOf(next)));
        final CompletionStage<IOResult> result =
                factorials
                        .map(num -> ByteString.fromString(num.toString() + "\n"))
                        .runWith(FileIO.toFile(new File("factorials.txt")), materializer);

    }


    public static void three() {
        final ActorSystem system = ActorSystem.create("QuickStart");
        final Materializer materializer = ActorMaterializer.create(system);

        final Source<Integer, NotUsed> source = Source.range(1, 100);

        final Source<BigInteger, NotUsed> factorials =
                source.scan(BigInteger.ONE, (acc, next) -> acc.multiply(BigInteger.valueOf(next)));

        factorials.map(BigInteger::toString).runWith(lineSink("factorial2.txt"), materializer);

    }

    public static Sink<String, CompletionStage<IOResult>> lineSink(String filename) {
        return Flow.of(String.class)
                .map(s -> ByteString.fromString(s.toString() + "\n"))
                .toMat(FileIO.toFile(new File(filename)), Keep.right());
    }

    public static void four() {
        final ActorSystem system = ActorSystem.create("QuickStart");
        final Materializer materializer = ActorMaterializer.create(system);

        final Source<Integer, NotUsed> source = Source.range(1, 100);

        final Source<BigInteger, NotUsed> factorials =
                source.scan(BigInteger.ONE, (acc, next) -> acc.multiply(BigInteger.valueOf(next)));

        final CompletionStage<Done> done =
                factorials.zipWith(Source.range(0, 99), (num, idx) -> String.format("%d! = %s", idx, num))
                        .throttle(1, Duration.create(1, TimeUnit.SECONDS), 1, ThrottleMode.shaping())
                        .runForeach(s -> System.out.println(s), materializer);

    }
}

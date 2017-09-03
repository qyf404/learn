package com.qyf404.learn.java8;

import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.sun.tools.doclint.Entity.sum;

/**®
 * Created by yfqi on 4/29/16.
 */
@SuppressWarnings("Anonymous2MethodRef")
public class LearnStream {

    public static void main(String[] args) {


        four();


    }

    private static void one() {
        List<String> list = new ArrayList<String>() {{
            add("a");
            add("b");
            add("c");
            add("d");
        }};

        String first = list.stream().findFirst().get();
        System.out.println("first =" + first);

    }

    private static void two() {
        Stream.generate(new Supplier<Double>() {
            @Override
            public Double get() {
                return Math.random();
            }
        });
        Stream.generate(() -> Math.random());
        Stream.generate(Math::random);

    }

    private static void three() {

        List<Integer> nums = Lists.newArrayList(1, 1, null, 2, 3, 4, null, 5, 6, 7, 8, 9, 10);
        System.out.println("sum is:" + nums.stream().filter(num -> num != null).
                distinct().mapToInt(num -> num * 2).peek(System.out::println)
                .skip(2).limit(4).sum());
    }

    private static void four() {
        List<Integer> nums = Lists.newArrayList(1, 1, null, 2, 3, 4, null, 5, 6, 7, 8, 9, 10);
        List<Integer> numsWithoutNull = nums.stream().filter(num -> num != null).
                collect(() -> new ArrayList<Integer>(),
                        (list, item) -> list.add(item),
                        (list1, list2) -> list1.addAll(list2));

        System.out.println(ToStringBuilder.reflectionToString(numsWithoutNull.toArray()));
    }
}

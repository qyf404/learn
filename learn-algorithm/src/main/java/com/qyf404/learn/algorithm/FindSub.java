package com.qyf404.learn.algorithm;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 有这么一个业务场景, 会计向税务报增值税抵扣时, 报了这么一组数据
 * 1540.25
 * 25163.89
 * 1519.67
 * 8309.09
 * 3762.93
 * 3430.91
 * 1274.57
 * 2999.32
 * 45871.56
 * 1426.16
 * 1130.35
 * 2995.2
 * 8792.35
 * 4386.79
 * 876.42
 * 3879.16
 * 691.72
 * 12272.33
 * 27522.94
 * 331.27
 * <p>
 * 但是税务查出的总额是104408.73, 而税务又查不出明细
 * <p>
 * 那问题来了, 如何找到这个总额是由哪些明细组成的?
 */
public class FindSub {

    public static void main(String[] args) {
        // 为方便计算所有的金额的单位直接到分.

        List<Integer> amounts = Arrays.asList(
                154025,
                2516389,
                151967,
                830909,
                376293,
                343091,
                127457,
                299932,
                4587156,
                142616,
                113035,
                299520,
                879235,
                438679,
                87642,
                387916,
                69172,
                1227233,
                2752294,
                33127);
        int totalAmount = 10440873;

//        List<Integer> amounts = Arrays.asList(1,2,3,4,5,6,7,8,9,0);
//        int totalAmount = 9;

        List<Boolean> tags = new ArrayList<Boolean>(20);
        for (int i = 0; i < amounts.size(); i++) {
            tags.add(false);
        }


        while (!isAllTrue(tags)) {
            List<Integer> sub = new ArrayList<>();
            for (int i = 0; i < tags.size(); i++) {
                if (tags.get(i)) {
                    sub.add(amounts.get(i));
                }
            }

            int reduce = sub.stream().reduce(0, (r, i) -> r + i);
            int x = 10;
            if (Math.abs(reduce - totalAmount) <=x) {

                System.out.println("|("+StringUtils.join(sub, "+") +")"+ "-" + totalAmount + "|<= " + x);
            }
            addTrue(tags, 0);
        }
    }

    private static void addTrue(List<Boolean> tags, int index) {
        if (tags.size() <= index) {
            return;
        }
        Boolean a = tags.get(index);
        if (a) {
            tags.set(index, false);
            addTrue(tags, index + 1);
        } else {
            tags.set(index, true);
            return;
        }


    }

    private static boolean isAllTrue(List<Boolean> tags) {
        return tags.stream().allMatch(Boolean::booleanValue);
    }
}

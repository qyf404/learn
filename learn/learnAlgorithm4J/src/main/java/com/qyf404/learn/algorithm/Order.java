package com.qyf404.learn.algorithm;

import java.util.Arrays;

import org.apache.commons.lang.math.RandomUtils;

public class Order {
	int[] arr;

	public Order(int[] arr) {
		this.arr = arr;
	}

	public int[] getArr() {
		return arr;
	}

	public void sort1() {

		int length = arr.length;
		// int[] result = new int[length];
		for (int i = 0; i < length - 1; i++) {
			for (int j = i + 1; j < length; j++) {

				if (arr[i] > arr[j]) {
					int temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}

			}
		}
	}

	public void sort2() {
		sort2(0, arr.length - 1);

	}

	public void sort2(int i, int j) {
		//System.out.println(Arrays.toString(arr) + "|" + i + "|" + j);
		if (i >= j) {
			return;
		}
		int first = i;
		int last = j;
		int key = arr[first];
		while (first < last) {
//			System.out.println("first="+first+",last="+last);
			while (first < last && arr[last] >= key) {
				last--;
			}
			arr[first] = arr[last];
			while (first < last && arr[first] <= key) {
				first++;
			}
			arr[last] = arr[first];
		}
		arr[first] = key;
		sort2(i, first - 1);
		sort2(first + 1, j);

	}

	public static void main(String[] args) {
		int max = 1000;
		int [] a = new int[max];
		
		for(int i=0;i<max ;i++){
			a[i] = RandomUtils.nextInt();
		}
		
		
		 {
//		 int[] arr = { 1, 5, 4, 6, 8, 3, 2, 8, 0, 9, 2, 4, 6, 56 };
		 int[] arr = Arrays.copyOf(a, a.length);
//		 System.out.println(Arrays.toString(arr));
		 Order order = new Order(arr);
		
		 long begin = System.currentTimeMillis();
		 order.sort1();
		 long end = System.currentTimeMillis();
		
		 System.out.println("time=" +( end - begin));
//		 System.out.println(Arrays.toString(order.getArr()));
		 }

		{
//			int[] arr = { 1, 5, 4, 6, 8, 3, 2, 8, 0, 9, 2, 4, 6, 56 };
			int[] arr = Arrays.copyOf(a, a.length);
//			System.out.println(Arrays.toString(arr));

			Order order = new Order(arr);
			
			long begin = System.currentTimeMillis();
			order.sort2();
			long end = System.currentTimeMillis();

			System.out.println("time=" +( end - begin));
//			System.out.println(Arrays.toString(order.getArr()));
		}

		// TODO Auto-generated method stub
	}

}

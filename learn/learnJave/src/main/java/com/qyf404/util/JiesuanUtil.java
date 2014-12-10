package com.qyf404.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

public class JiesuanUtil {
	/**
	 * 清理数据
	 * 
	 * @param list
	 * @return
	 */
	public static List<List<String>> cleanData(List<List<String>> list) {
		List<List<String>> result = new ArrayList<List<String>>(list.size() / 2);

		for (int i = 0; i < list.size(); i++) {
			List<String> rowList = list.get(i);

			String str0 = rowList.get(0);
			String str1 = rowList.get(1);
			String str2 = getStr(rowList.get(2));
			String str3 = null;
			if(StringUtils.isBlank(str2)){
				str3 = "";
			}else{
				str3 = getStr(rowList.get(3));
			}
			String str4 = rowList.get(4);
			String str5 = rowList.get(5);
			String str6 = rowList.get(6);
			String str7 = rowList.get(7);

			List<String> resultRowList = new ArrayList<String>();
			resultRowList.add(str0);
			resultRowList.add(str1);
			resultRowList.add(str2);
			resultRowList.add(str3);
			resultRowList.add(str4);
			resultRowList.add(str5);
			resultRowList.add(str6);
			resultRowList.add(str7);

			result.add(resultRowList);
		}
		return result;
	}

	public static String getStr(String str) {
		str = str.trim();
		if ("无".equals(str)) {
			str = "";
		} else if ("".equals(str)) {
			str = "";
		} else {
			try {
				int tempInt = Integer.valueOf(str);

				if (tempInt == 0) {
					str = "";
				}
			} catch (Exception e) {
				System.err.println(e);
			}
		}
		return str;
	}

	/**
	 * 去除重复
	 * 
	 * @param list
	 * @return
	 */
	public static List<List<String>> quchongfu(List<List<String>> list) {
		List<List<String>> result = new ArrayList<List<String>>(list.size() / 2);

		Set<String> keys = new HashSet<String>(list.size());

		List<String> otherKeys = new ArrayList<String>();

		for (int i = 0; i < list.size(); i++) {
			String key = getKey(list.get(i));
			if (keys.contains(key)) {
				otherKeys.add(key);
				continue;
			} else {
				result.add(list.get(i));
				keys.add(key);
			}
		}
		System.out.println(String.format("入%s条，出%s条，重复%s条", list.size(),
				result.size(), otherKeys.size()));
		for (String key : otherKeys) {
			System.out.println(key);
		}
		return result;
	}
	/**
	 * 获取唯一标示
	 * 
	 * @param rowList
	 * @return
	 */
	public static String getKey(List<String> rowList) {
		String str2 = rowList.get(2);

		String str3 = rowList.get(3);

		StringBuilder sb = new StringBuilder();
		sb.append(rowList.get(0)).append("_").append(rowList.get(1))
				.append("_")

				.append(str2).append("_").append(str3).append("_");
		return sb.toString();
	}
}

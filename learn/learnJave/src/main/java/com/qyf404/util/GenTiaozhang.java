package com.qyf404.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.math.RandomUtils;

public class GenTiaozhang {
	/**
	 * 最多出现的调账次数，防止程序崩溃
	 */
	private static final int MAX_NUM = 50;
	
	public List<List<String>> shengList ;
	public List<List<String>> shiList ;
	
	/**
	 * @param rowList
	 * @return
	 */
	public List<List<String>> getResult(final List<String> rowList){
		
		
		List<List<String>> result = new ArrayList<List<String>>();
		
		int num = 0;
		int a;
		//三分之二的概率出现调账
		while(( a = RandomUtils.nextInt(10)) != 0 && ++num <= MAX_NUM ){
			List<String> resultRowList = new ArrayList<String>();
			for(String str : rowList){
				resultRowList.add(str);
			}
			
			
			if(RandomUtils.nextInt(5) == 0){
				//调账到省
				List<String> sheng = shengList.get(RandomUtils.nextInt(shengList.size()));
				resultRowList.add(sheng.get(0));
				resultRowList.add("");
				
			}else{
				//调账到市
				List<String> shi = shiList.get(RandomUtils.nextInt(shiList.size()));
				resultRowList.add(shi.get(2));
				resultRowList.add(shi.get(0));
			}
			
			int jinge = RandomUtils.nextInt(new Random() , 100000);
			
			resultRowList.add(RandomUtils.nextBoolean()?"" + jinge : "-" + jinge );
			
			result.add(resultRowList);
		}
		
		
		return result;
	}
	
	/**
	 * 加载省市
	 */
	private void loadShengShi(){
		shengList = CsvUtil.readCSVFile("/Users/qyfmac/temp/0-sheng.csv", "gbk");
		shiList = CsvUtil.readCSVFile("/Users/qyfmac/temp/0-shi.csv", "gbk");
	}
	
	public void gen() {
		loadShengShi();
		
		List<List<String>> list = CsvUtil.readCSVFile("/Users/qyfmac/temp/test2.csv", "gbk");

		list = JiesuanUtil.cleanData(list);

		list = JiesuanUtil.quchongfu(list);

		List<List<String>> resultList = new ArrayList<List<String>>();
		// "111","20","","","","SP彩信MMS业务","","","receivableInformationCosts * (1 - badDebtRate) * settlementProportion + changeAccount;","0.008","310000","0.9","0.9"
		for (int i = 0; i < list.size(); i++) {
			List<String> rowList = list.get(i);
			
			resultList.addAll(getResult(rowList));
			
		}

		CsvUtil.write(resultList, "/Users/qyfmac/temp/3-tiaozhang.csv", "utf-8");

	}
	
	
	public static void main(String[] args) {
		GenTiaozhang g = new GenTiaozhang();
		g.gen();
	}
}

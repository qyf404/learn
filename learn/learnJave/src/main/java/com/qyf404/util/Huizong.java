package com.qyf404.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.math.RandomUtils;

/**
 * 按照结算规则汇总
 * 
 * @author qyfmac
 * 
 */
public class Huizong {
	
	
	public static void writeCsv(Map<String,TempModel> map, String path)
			throws IOException {
		
		File outFile = new File(path);

		FileOutputStream fos = new FileOutputStream(outFile);
		OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
		BufferedWriter bw = new BufferedWriter(osw);
		try {
			for(String key :map.keySet()){
				TempModel tempModel = map.get(key);
				bw.write("\"");
				bw.write(tempModel.key);
				bw.write("\",");
				bw.write("\"");
				bw.write(tempModel.count.toString());
				bw.write("\",");
				bw.write("\"");
				bw.write(tempModel.list.get(2));
				bw.write("\",");
				bw.write("\"");
				bw.write(tempModel.list.get(3));
				bw.write("\",");
				bw.write("\"");
				bw.write(tempModel.list.get(21));
				bw.write("\",");
				bw.write("\"");
				bw.write(tempModel.list.get(4));
				bw.write("\",");
				bw.write("\"");
				bw.write(tempModel.list.get(19));
				bw.write("\"");
				bw.write("\n");
			}
		} finally {
			bw.close();
		}
	}
	public static void run(String inFile, String outFile) throws IOException{

		List<List<String>> csvList = CsvUtil.readCSVFile(inFile, "utf-8");
		Map<String,TempModel> map = new HashMap<String,TempModel>();
		for(int i=0;i<csvList.size();i++){
			List<String> list = csvList.get(i);
			String key = getKey(list);
			if(map.containsKey(key)){
				TempModel tempModel = map.get(key);
				tempModel.count = tempModel.count.add(new BigDecimal(list.get(11)));
				map.put(key, tempModel);
			}else{
				TempModel tempModel = new TempModel();
				tempModel.key = key;
				tempModel.list = list;
				tempModel.count = new BigDecimal(list.get(11));
				map.put(key, tempModel);
			}
		}
		
		writeCsv(map, outFile);
	
	}
	
	public static void main(String[] args) throws IOException {
		run("/Users/qyfmac/temp/2-zhangdan-temp.csv", "/Users/qyfmac/temp/2-huizong-temp.csv");
		run("/Users/qyfmac/temp/2-zhangdan-temp2.csv", "/Users/qyfmac/temp/2-huizong-temp2.csv");
		
		
	}
	private static String getKey(List<String> list){
		
		String PARTY_CODE = list.get(2);
		String SRVTYPE_ID = list.get(3);
		String SERVICE_CODE= getStr(list.get(21));
		String PROD_CODE= getStr(list.get(4));
		String sheng= list.get(19);
		//"CONN_NET_TYPE","ID","PARTY_CODE","SRVTYPE_ID","PROD_CODE","USER_NO","USER_TYPE","ACCESS_NO","PROV_CODE","AREA_CODE","PERIOD","REFUNDFEE","REFUNDREASON","REFUNDHAPENDATE","REFUNDOPERDATE","PRESERVE1","PRESERVE2","PRESERVE3","FILEID","DATARANGE","DATATYPE","SERVICE_CODE","LASTMODIFIED","LASTMODIFYUSER","CREATETIME","OWNER_ID","REMARK","STATUS","VERSION","TARGET_CODE","TARGET_NAME"
		//"1","68178","91670","7","0000000000","1560501****","0","10666608","038","385","201012","200","999","2011/1/21","2011/1/22","","","","917f29f8-7bbb-40a7-9b8b-e6bdd227487e","038","4","ZHTKLYX","2011/1/22","","2011/1/22","","","2","0","91670","北京时代讯达科技有限公司",
		
		return PARTY_CODE+"__"+SRVTYPE_ID+"__"+SERVICE_CODE+"__"+PROD_CODE+"__"+sheng;
		
	}
	
	private static String getStr(String str) {
		str = str.trim();
		if ("无".equals(str)) {
			str = "";
		} else if ("".equals(str)) {
			str = "";
		} else {
			try{
			int tempInt = Integer. valueOf(str);
			
			if (tempInt == 0) {
				str = "";
			}
			}catch(Exception e){
				//throw e;
			}
		}
		return str;
	}
	
}
//"PARTY_CODE","SRVTYPE_ID","SERVICE_CODE","PROD_CODE
	class TempModel{
		String key;
		List<String> list;
		
		BigDecimal count = new BigDecimal(0);
		
		
	}

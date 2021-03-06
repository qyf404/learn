package com.qyf404.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.math.RandomUtils;

public class TestImportCsv {

	private InputStreamReader fr = null;
	private BufferedReader br = null;

	public TestImportCsv(String f) throws IOException {
		fr = new InputStreamReader(new FileInputStream(f), "GBK");
	}

	/**
	 * 解析csv文件 到一个list中 每个单元个为一个String类型记录，每一行为一个list。 再将所有的行放到一个总list中
	 */
	public List<List<String>> readCSVFile() throws IOException {
		br = new BufferedReader(fr);
		String rec = null;// 一行
		String str;// 一个单元格
		List<List<String>> listFile = new ArrayList<List<String>>();
		try {
			// 读取一行
			while ((rec = br.readLine()) != null) {
				rec += ",";
				Pattern pCells = Pattern
						.compile("(\"[^\"]*(\"{2})*[^\"]*\")*[^,]*,");
				Matcher mCells = pCells.matcher(rec);
				List<String> cells = new ArrayList<String>();// 每行记录一个list
				// 读取每个单元格
				while (mCells.find()) {
					str = mCells.group();
					str = str.replaceAll(
							"(?sm)\"?([^\"]*(\"{2})*[^\"]*)\"?.*,", "$1");
					str = str.replaceAll("(?sm)(\"(\"))", "$2");
					cells.add(str);
				}
				listFile.add(cells);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fr != null) {
				fr.close();
			}
			if (br != null) {
				br.close();
			}
		}
		return listFile;
	}

	private String getStr(String str) throws Exception{
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
				throw e;
			}
		}
		return str;
	}
	
	private String getKey(List<String> rowList){
		String str2;
		try {
			str2 = getStr(rowList.get(2));
		} catch (Exception e) {
			str2 = rowList.get(2);
		}
		
		String str3;
		try {
			str3 = getStr(rowList.get(3));
		} catch (Exception e) {
			str3 = rowList.get(3);
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(rowList.get(0)).append("_")
			.append(rowList.get(1)).append("_")
		
			.append(str2).append("_")
			.append(str3).append("_");
		return sb.toString();
		
	}

	public void writeCsv(List<List<String>> csvList, String path)
			throws IOException {
		
		Set<String> keySet = new HashSet<String>();
		
		File outFile = new File(path);

		FileOutputStream fos = new FileOutputStream(outFile);
		OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
		BufferedWriter bw = new BufferedWriter(osw);
		try {
			// "PARTY_CODE","SRVTYPE_ID","SERVICE_CODE","PROD_CODE","PARTY_NAME","SRVTYPE_NAME","SERVICE_NAME","PROD_NAME"
			// "111","20","00000000","00000000","","SP彩信MMS业务","",""
			// "1","20011","90","","","20011","SP_SRVTYPE","receivableInformationCosts * (1 - badDebtRate) * settlementProportion;","0.008","0.7","","","","",""
			int k = 0;
			for (List<String> rowList : csvList) {
				if(k ==0 ){
					k++;
					continue;
				}
				
				
				String key = getKey(rowList);
				if(keySet.contains(key)){
					continue;
				}else{
					keySet.add(key);
				}
				
				
				
				for (int i = 0; i < rowList.size(); i++) {
					String str = rowList.get(i);

					if (i == 2) {
						try {
							str = getStr(str);
						} catch (Exception e) {
//							System.out.println("类型转换失败,不是0000,第" + k + "行");
						}
					} else if (i == 3) {
						try {
							str = getStr(str);
						} catch (Exception e) {
//							System.out.println("类型转换失败,不是0000,第" + k + "行");
						}
					}

					bw.write("\"");
					bw.write(str);
					bw.write("\",");
				}

				bw.write("\"receivableInformationCosts * (1 - badDebtRate) * settlementProportion + changeAccount;\",");
				bw.write("\"0.008\",");

				int jinge = RandomUtils.nextInt(100) * 10000;
				bw.write("\"" + jinge + "\",");

				int a;
				do {
					a = RandomUtils.nextInt(10);
				} while (a == 0);
				bw.write("\"0." + a + "\",");

				int b = 10 - a;
				b = RandomUtils.nextInt(b) + a;
				bw.write("\"0." + b + "\"");

				k++;
				if (k < csvList.size()) {
					bw.write("\n");
				}
			}
			System.out.println("成功转换" + k + "行数据");
		} finally {
			bw.close();
		}
	}

	public void writeCsv2(List<List<String>> csvList, String path)
			throws IOException {
		
		Set<String> keySet = new HashSet<String>();
		
		File outFile = new File(path);

		FileOutputStream fos = new FileOutputStream(outFile);
		OutputStreamWriter osw = new OutputStreamWriter(fos);
		BufferedWriter bw = new BufferedWriter(osw);
		try {
			// "PARTY_CODE","SRVTYPE_ID","SERVICE_CODE","PROD_CODE","PARTY_NAME","SRVTYPE_NAME","SERVICE_NAME","PROD_NAME"
			// "111","20","00000000","00000000","","SP彩信MMS业务","",""
			// "1","20011","90","","","20011","SP_SRVTYPE","receivableInformationCosts * (1 - badDebtRate) * settlementProportion;","0.008","0.7","","","","",""
			int k = 0;
			for (List<String> rowList : csvList) {
				if(k ==0 ){
					k++;
					continue;
				}
				
				String key = getKey(rowList);
				if(keySet.contains(key)){
					System.out.println("发现重复数据,第"+k+"行");
					System.out.println(Arrays.toString(rowList.toArray()));
					k++;
					continue;
				}else{
					keySet.add(key);
				}
				
				boolean flag = false;
				for (int i = 0; i < rowList.size(); i++) {
					String str = rowList.get(i);
					if (i == 2) {
						try {
							str = getStr(str);
						} catch (Exception e) {
							System.out.println("类型转换失败,不是0000,第" + k + "行");
							flag = true;
						}
					} else if (i == 3) {
						try {
							str = getStr(str);
						} catch (Exception e) {
							System.out.println("类型转换失败,不是0000,第" + k + "行");
							flag = true;
						}
					}
					
					bw.write("\"");
					bw.write(str);
					bw.write("\",");
				}
				if(flag){
					System.out.println(Arrays.toString(rowList.toArray()));
				}

				int xiaoshu = RandomUtils.nextInt(100);
				int zhegnshu = RandomUtils.nextInt(100000);
				bw.write("\"" + zhegnshu + "." + xiaoshu + "\"");

				k++;
				if (k < csvList.size()) {
					bw.write("\n");
				}
			}
		} finally {

			bw.close();
		}

	}

	public static void main(String[] args) throws Throwable {
		TestImportCsv test = new TestImportCsv("/Users/qyfmac/temp/test2.csv");
		List<List<String>> csvList = test.readCSVFile();
		test.writeCsv(csvList, "/Users/qyfmac/temp/guize.csv");
		test.writeCsv2(csvList, "/Users/qyfmac/temp/tiaozhang.csv");
	}

}
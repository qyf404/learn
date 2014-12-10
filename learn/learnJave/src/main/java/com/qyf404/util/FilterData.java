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
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 清理账单数据，根据给定sp和业务类型抓取出匹配的账单数据
 * @author qyfmac
 *
 */
public class FilterData {
	
	public static void writeCsv(String inFile, String inCode, String outFilePath, String sp, String servType)
			throws IOException {
		

		InputStreamReader fr = new InputStreamReader(new FileInputStream(inFile), inCode);
		BufferedReader br = new BufferedReader(fr);
		
		
		File outFile = new File(outFilePath);
		FileOutputStream fos = new FileOutputStream(outFile);
		OutputStreamWriter osw = new OutputStreamWriter(fos,"utf-8");
		BufferedWriter bw = new BufferedWriter(osw);
		
		String rec = null;// 一行
		String str;// 一个单元格
//		List<List<String>> listFile = new ArrayList<List<String>>();
		try {
			int i=0;
			// 读取一行
			while ((rec = br.readLine()) != null) {
				System.out.println(i++);
				//in
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
				
				
				//out
				if(cells !=null && cells.size() > 4){
					String spTemp = cells.get(2);
					String servTypeTemp = cells.get(3);
					
					if(sp.equals(spTemp) && servType.equals(servTypeTemp)){
						
					}else{
						continue;
					}
					
					for (int j = 0; j < cells.size(); j++) {
						String strtemp = cells.get(j);
						
						bw.write("\"");
						bw.write(strtemp);
						bw.write("\",");
					}

						bw.write("\n");
				}
				
				
				
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
			bw.close();
		}

	}
	public static void main(String[] args) throws IOException {
//		List<List<String>> csvList = CsvUtil.readCSVFile("/Users/qyfmac/temp/2-zhangdan.csv", "GBK");
		
		writeCsv("/Users/qyfmac/temp/2-zhangdan.csv", "GBK", "/Users/qyfmac/temp/3-zhangdan-temp.csv", "91670", "7");
//		writeCsv("/Users/qyfmac/temp/2-zhangdan.csv", "GBK", "/Users/qyfmac/temp/2-zhangdan-temp2.csv", "90350", "20");
		
		
	}
}

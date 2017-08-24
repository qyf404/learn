package com.qyf404.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CsvUtil {
	/**
	 * 解析csv文件 到一个list中 每个单元个为一个String类型记录，每一行为一个list。 再将所有的行放到一个总list中
	 */
	public static List<List<String>> readCSVFile(String f, String charsetName) {
		InputStreamReader fr = null;
		BufferedReader br = null;

		String rec = null;// 一行
		String str;// 一个单元格
		List<List<String>> listFile = new ArrayList<List<String>>();
		try {
			fr = new InputStreamReader(new FileInputStream(f), charsetName);
			br = new BufferedReader(fr);
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
				try {
					fr.close();
				} catch (IOException e) {
				}
			}
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
				}
			}
		}

		System.out.println(String.format("共读取了%s行", listFile.size()));
		return listFile;
	}

	public static void write(List<List<String>> list, String fileName,
			String charsetName) {
		File outFile = new File(fileName);

		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		BufferedWriter bw = null;

		try {
			fos = new FileOutputStream(outFile);
			osw = new OutputStreamWriter(fos, charsetName);
			bw = new BufferedWriter(osw);
			for (int j = 0; j < list.size(); j++) {
				List<String> rowList = list.get(j);
				for (int i = 0; i < rowList.size(); i++) {
					String str = rowList.get(i);
					bw.write("\"");
					bw.write(str);
					bw.write("\"");

					if (i < rowList.size() - 1) {
						bw.write(",");
					}
				}
				if (j < list.size() - 1) {
					bw.write("\n");
				}
			}
			bw.flush();
			System.out.println(String.format("共输出%s行", list.size()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch (IOException e) {
			}
			try {
				osw.close();
			} catch (IOException e) {
			}
			try {
				bw.close();
			} catch (IOException e) {
			}
		}
	}
}

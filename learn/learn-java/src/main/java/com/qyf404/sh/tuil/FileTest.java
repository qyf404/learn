package com.qyf404.sh.tuil;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * 
 * Title: FileTest<br>
 * Description: <br>
 * Company: <br>
 * @author Huosd
 * @createDate 2015年3月24日
 */
public class FileTest {
	
	private static String root = "/Users/qyfmac/Documents/workspace4.3/shappserver-parent/shappserver-web/target/classes/";

	public static void main(String[] args) throws Exception {
		readfile(root);
	}

	public static boolean readfile(String filepath) throws Exception {
		try {
			File file = new File(filepath);
			if (!file.isDirectory()) {
				if(!file.getName().contains("$1.class")){
					System.out.println(file.getAbsolutePath().replace(root, " "));
				}
			} else if (file.isDirectory()) {
				String[] filelist = file.list();
				for (int i = 0; i < filelist.length; i++) {
					File readfile = new File(filepath + "/" + filelist[i]);
					if (!readfile.isDirectory()) {
						if(!readfile.getName().contains("$1.class")){
							System.out.println(readfile.getAbsolutePath().replace(root, " "));
						}
					} else if (readfile.isDirectory()) {
						readfile(filepath + "/" + filelist[i]);
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("readfile()   Exception:" + e.getMessage());
		}
		return true;
	}
}

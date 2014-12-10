package com.qyf404.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.math.RandomUtils;

public class GenGuize {
	static final String ruleFormat0 = "receivableInformationCosts * (1 - badDebtRate) * settlementProportion;";
	static final String ruleFormat1 = "receivableInformationCosts * settlementProportion;";
	static final String ruleFormat2 = "receivableInformationCosts * (1 - badDebtRate) * settlementProportion + changeAccount;";
	static final String ruleFormat3 = "receivableInformationCosts * settlementProportion + changeAccount;";

	static final String settlementProportionFormat0 = "0.%s;";
	static final String settlementProportionFormat1 = "if(totalReceivableInformationCosts>%s)\t0.%s;\telse\t0.%s;\tendif";
	static final String settlementProportionFormat2 = "if(totalReceivableInformationCosts>%s)\t0.%s;\telse\tif(totalReceivableInformationCosts>%s)\t0.%s;\telse\t0.%s;\tendif\tendif";
	static final String settlementProportionFormat3 = "if(totalReceivableInformationCosts>%s)\t0.%s;\telse\tif(totalReceivableInformationCosts>%s)\t0.%s;\telse\tif(totalReceivableInformationCosts>%s)\t0.%s;\telse\t0.%s;\tendif\tendif\tendif";

	

	

	

	public String getRuleFormat() {
		int i = RandomUtils.nextInt(4);
		String result;
		switch (i) {
		case 0:
			result = ruleFormat0;
			break;
		case 1:
			result = ruleFormat1;

			break;
		case 2:
			result = ruleFormat2;

			break;

		default:
			result = ruleFormat3;
			break;
		}
		return result;
	}

	private String getSettlementProportionFormat() {
		int i = RandomUtils.nextInt(4);
		String result;
		switch (i) {
		case 0:

			result = String.format(settlementProportionFormat0,
					RandomUtils.nextInt(99)+1);
			break;
		case 1: {
			int maxP = RandomUtils.nextInt(99) + 1;
			result = String.format(settlementProportionFormat1,
					RandomUtils.nextInt(), maxP, RandomUtils.nextInt(maxP) + 1);
		}
			break;
		case 2: {
			int a1 = RandomUtils.nextInt();
			int a2 = RandomUtils.nextInt(a1);

			int b1 = RandomUtils.nextInt(99) + 1;
			int b2 = RandomUtils.nextInt(b1) + 1;
			int b3 = RandomUtils.nextInt(b2) + 1;

			result = String.format(settlementProportionFormat2, a1, b1, a2, b2,
					b3);
		}
			break;

		default: {
			int a1 = RandomUtils.nextInt();
			int a2 = RandomUtils.nextInt(a1);
			int a3 = RandomUtils.nextInt(a1);

			int b1 = RandomUtils.nextInt(99) + 1;
			int b2 = RandomUtils.nextInt(b1) + 1;
			int b3 = RandomUtils.nextInt(b2) + 1;
			int b4 = RandomUtils.nextInt(b3) + 1;

			result = String.format(settlementProportionFormat3, a1, b1, a2, b2,
					a3, b3, b4);
		}
			break;
		}
		return result;
	}

	private String getBadDebtRate() {
		return "0.008";
	}

	public void gen() {
		List<List<String>> list = CsvUtil.readCSVFile("/Users/qyfmac/temp/test2.csv", "gbk");

		list = JiesuanUtil.cleanData(list);

		list = JiesuanUtil.quchongfu(list);

		// "111","20","","","","SP彩信MMS业务","","","receivableInformationCosts * (1 - badDebtRate) * settlementProportion + changeAccount;","0.008","310000","0.9","0.9"
		for (int i = 0; i < list.size(); i++) {
			List<String> rowList = list.get(i);
			rowList.add(getRuleFormat());
			rowList.add(getBadDebtRate());
			rowList.add(getSettlementProportionFormat());

		}

		CsvUtil.write(list, "/Users/qyfmac/temp/3-guize.csv", "utf-8");

	}
	
	
	public static void main(String[] args) {
		GenGuize g = new GenGuize();
		g.gen();
	}

}

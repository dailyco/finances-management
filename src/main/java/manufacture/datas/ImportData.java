package manufacture.datas;

import java.util.HashMap;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.table.DefaultTableModel;

public class ImportData extends HashMap<String, ArrayList<Import>> {
	DecimalFormat formatter = new DecimalFormat("###,###");
	
	HashMap<String, String> categorySum = new HashMap<String, String>();
	ArrayList<String> categoryOrder = new ArrayList<String>();
	String date;
	Integer totalSum = 0;
	String totalSumStr;
	String totalSumKr;
	
	public ImportData(DefaultTableModel model, String date) {
		this.date = date;
		
		// dummy data 사용시 parameter 없는 것을 실행, parameter 있는 것을 주석처리
//		storeImportDatas(model);
		storeImportDatas();
		
		sortCategoryOrder();
		computeCategorySum();
		transferToTotalSumStr();
		transferToTotalSumKr();
		
		printResult();
	}
	
	private void storeImportDatas() {
		// 주일헌금 있는 버전 | 없는 버전
		String[][] dummyData = { { "주일헌금", "전체", "197000" }, { "건축", "강재구", "30000" }, { "건축", "백수근", "50000" }, { "건축", "김명중", "50000" }, { "십일조", "황충원", "200000" }, { "십일조", "백수근", "400000" }, { "십일조", "남안숙", "20000" }, { "십일조", "박현종", "25000" }, { "십일조", "백진엽", "100000" }, { "십일조", "백솔", "20000" }, { "십일조", "강재구", "320000" }, { "십일조", "이승아", "250000" }, { "십일조", "임영순(3/10)", "450000" }, { "십일조", "김정현", "70000" }, { "십일조", "진수현", "100000" }, { "십일조", "김병선(3/10)", "140000" }, { "기타", "커피판매수입", "0" }, { "감사", "강재구", "30000" }, { "감사", "백수근", "50000" }, { "감사", "김명중", "50000" }, { "감사", "김현섭", "50000" }, { "감사", "황금자", "50000" }, { "선교", "강재구", "20000" }, { "선교", "백수근", "30000" }, { "선교", "1권사회", "30000" }, { "선교", "안디옥", "30000" }, { "선교", "인도", "50000" }, { "선교", "다니엘", "30000" } };
//		String[][] dummyData = { { "건축", "강재구", "30000" }, { "건축", "백수근", "50000" }, { "건축", "김명중", "50000" }, { "십일조", "황충원", "200000" }, { "십일조", "백수근", "400000" }, { "십일조", "남안숙", "20000" }, { "십일조", "박현종", "25000" }, { "십일조", "백진엽", "100000" }, { "십일조", "백솔", "20000" }, { "십일조", "강재구", "320000" }, { "십일조", "이승아", "250000" }, { "십일조", "임영순(3/10)", "450000" }, { "십일조", "김정현", "70000" }, { "십일조", "진수현", "100000" }, { "십일조", "김병선(3/10)", "140000" }, { "기타", "커피판매수입", "0" }, { "감사", "강재구", "30000" }, { "감사", "백수근", "50000" }, { "감사", "김명중", "50000" }, { "감사", "김현섭", "50000" }, { "감사", "황금자", "50000" }, { "선교", "강재구", "20000" }, { "선교", "백수근", "30000" }, { "선교", "1권사회", "30000" }, { "선교", "안디옥", "30000" }, { "선교", "인도", "50000" }, { "선교", "다니엘", "30000" } };
		
		for (int i = 0; i < dummyData.length; i++) {
			String category = dummyData[i][0];
			String content = dummyData[i][1];
			Integer price = Integer.parseInt((String) dummyData[i][2]);
			String priceStr = formatter.format(price);
			totalSum += price;
			if (get(category) == null) {
				ArrayList<Import> arrList = new ArrayList<Import>();
				arrList.add(new Import(category, content, price, priceStr));
				categoryOrder.add(category);
				put(category, arrList);
			} else {
				get(category).add(new Import(category, content, price, priceStr));
			}
		}
	}
	
	private void storeImportDatas(DefaultTableModel model) {
		int row = model.getRowCount();
		
		for (int i = 0; i < row; i++) {
			String category = (String)model.getValueAt(i, 0);
			String content = (String)model.getValueAt(i, 1);
			Integer price = Integer.parseInt((String) model.getValueAt(i, 2));
			String priceStr = formatter.format(price);
			totalSum += price;
			if (get(category) == null) {
				ArrayList<Import> arrList = new ArrayList<Import>();
				arrList.add(new Import(category, content, price, priceStr));
				categoryOrder.add(category);
				put(category, arrList);
			} else {
				get(category).add(new Import(category, content, price, priceStr));
			}
		}
	}
	
	private void sortCategoryOrder() {
		categoryOrder.sort(new Comparator<String>() {
			@Override
			public int compare(String category1, String category2) {
				if (get(category1).size() == get(category2).size())
					return 0;
				else if (get(category1).size() > get(category2).size())
					return 1;
				else
					return -1;
			}
		});
	}
	
	private void computeCategorySum() {
		for (String category : keySet()) {
			int sum = 0;
			for (Import data : get(category)) {
				sum += data.price;
			}
			String sumStr = formatter.format(sum);
			categorySum.put(category, sumStr);
		}
	}
	
	private void transferToTotalSumStr() {
		totalSumStr = formatter.format(totalSum);
	}
	
	private void transferToTotalSumKr() {
//		String[] unit1 = {"","일","이","삼","사","오","육","칠","팔","구"};
//		String[] unit2 = {"","십","백","천"};
//		String[] unit3 = {"","만","억","조","경"};
//		StringBuffer result = new StringBuffer();
//		
//		int len = totalSum.toString().length();
//		for (int i = len-1; i >= 0; i--) {
//			result.append(unit1[Integer.parseInt(totalSum.toString().substring(len-i-1, len-i))]);
//			
//			if(Integer.parseInt(totalSum.toString().substring(len-i-1, len-i)) > 0)
//				result.append(unit2[i % 4]);
//			
//			if(i % 4 == 0)
//				result.append(unit3[i/4]);
//		}
//		
//		totalSumKr = result.toString() + "원정";
//	    System.out.println(totalSumKr);
		
		String[] numberLabels = { "", "일", "이", "삼", "사", "오", "육", "칠", "팔", "구" };
	    String[] rangeLabels = {"", "십", "백", "천", "만", "십", "백", "천", "억", "십억" };
	    
	    int rangeCount = totalSum.toString().length()-1;
	    int rawValue = Integer.valueOf(totalSum);
	    StringBuilder result = new StringBuilder();
	    
	    while(rangeCount >= 0) {
	    	int mod = (int)Math.pow(10, rangeCount);
	    	result.append(numberLabels[rawValue/mod]);
	    	if (rawValue/mod > 0) {
	    		result.append(rangeLabels[rangeCount]);
	    	}
	    	rawValue = rawValue % mod;
	    	rangeCount--;
	    }
	    totalSumKr = result.toString() + "원정";
	    System.out.println(totalSumKr);
	}
	
	private void printResult() {
		System.out.println(date);
		for ( String key : categorySum.keySet()) {
			System.out.println(key + " = " + categorySum.get(key));
		}
		System.out.println(totalSumStr);
	}
	
	public HashMap<String, String> getCategorySum() {
		return categorySum;
	}
	
	public ArrayList<String> getCategoryOrder() {
		return categoryOrder;
	}
	
	public String getDate() {
		return date;
	}
	
	public String getTotalSum() {
		return totalSumStr;
	}
	
	public String getTotalKr() {
		return totalSumKr;
	}
}

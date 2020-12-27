package manufacture.datas;

import java.util.HashMap;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.table.DefaultTableModel;

public class ImportData extends HashMap<String, ArrayList<Import>> {
	HashMap<String, Integer> categorySum = new HashMap<String, Integer>();
	String[] categoryOrder = { "주일헌금", "십일조", "감사헌금", "선교헌금", "건축헌금", "예금이자", "부활감사", "맥추감사", "성탄감사", "추수감사", "신년감사", "기타", "일시차입금", "적립금인출", "차입금", "전년도이월금" };
	String[] categoryContentOrder = { "감사헌금", "선교헌금", "건축헌금", "십일조", "부활감사", "맥추감사", "성탄감사", "추수감사", "신년감사", "일시차입금", "적립금인출", "차입금", "전년도이월금", "예금이자", "기타" };
	String date;
	Integer totalSum = 0;
	String totalSumStr;
	String totalSumKr;
	
	public ImportData(DefaultTableModel model, String date) {
		this.date = date;
		
		// dummy data 사용시 parameter 없는 것을 실행, parameter 있는 것을 주석처리
		storeImportDatas(model);
//		storeImportDatas();

		computeCategorySum();
		transferToTotalSumStr();
		transferToTotalSumKr();
		
		printResult();
	}
	
//	private void storeImportDatas() {
//		// 주일헌금 있는 버전 | 없는 버전
//		String[][] dummyData = { { "주일헌금", "전체", "197000" }, { "건축헌금", "강재구", "30000" }, { "건축헌금", "백수근", "50000" }, { "건축헌금", "김명중", "50000" }, { "십일조", "황충원", "200000" }, { "십일조", "백수근", "400000" }, { "십일조", "남안숙", "20000" }, { "십일조", "박현종", "25000" }, { "십일조", "백진엽", "100000" }, { "십일조", "백솔", "20000" }, { "십일조", "강재구", "320000" }, { "십일조", "이승아", "250000" }, { "십일조", "임영순(3/10)", "450000" }, { "십일조", "김정현", "70000" }, { "십일조", "진수현", "100000" }, { "십일조", "김병선(3/10)", "140000" }, { "기타", "커피판매수입", "0" }, { "감사헌금", "강재구", "30000" }, { "감사헌금", "백수근", "50000" }, { "감사헌금", "김명중", "50000" }, { "감사헌금", "김현섭", "50000" }, { "감사헌금", "황금자", "50000" }, { "선교헌금", "강재구", "20000" }, { "선교헌금", "백수근", "30000" }, { "선교헌금", "1권사회", "30000" }, { "선교헌금", "안디옥", "30000" }, { "선교헌금", "인도", "50000" }, { "선교헌금", "다니엘", "30000" } };
//
//		for (int i = 0; i < dummyData.length; i++) {
//			String category = dummyData[i][0];
//			String content = dummyData[i][1];
//			Integer price = Integer.parseInt((String) dummyData[i][2]);
//			totalSum += price;
//			if (get(category) == null) {
//				ArrayList<Import> arrList = new ArrayList<Import>();
//				arrList.add(new Import(category, content, price));
//				put(category, arrList);
//			} else {
//				get(category).add(new Import(category, content, price));
//			}
//		}
//	}
	
	private void storeImportDatas(DefaultTableModel model) {
		int row = model.getRowCount();
		
		for (int i = 0; i < row; i++) {
			String category = (String)model.getValueAt(i, 0);
			String content = (String)model.getValueAt(i, 1);
			Integer price = Integer.parseInt((String) model.getValueAt(i, 2));
			totalSum += price;
			if (get(category) == null) {
				ArrayList<Import> arrList = new ArrayList<Import>();
				arrList.add(new Import(category, content, price));
				put(category, arrList);
			} else {
				get(category).add(new Import(category, content, price));
			}
		}
	}
	
	private void computeCategorySum() {
		for (String category : keySet()) {
			int sum = 0;
			for (Import data : get(category)) {
				sum += data.price;
			}
			categorySum.put(category, sum);
		}
	}
	
	private void transferToTotalSumStr() {
		totalSumStr = (new DecimalFormat("###,###")).format(totalSum);
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
	
	public HashMap<String, Integer> getCategorySum() {
		return categorySum;
	}
	
	public String[] getCategoryOrder() {
		return categoryOrder;
	}

	public String[] getCategoryContentOrder() { return categoryContentOrder; }

	public String getDate() {
		return date;
	}
	
	public Integer getTotalSum() {
		return totalSum;
	}
	
	public String getTotalKr() {
		return totalSumKr;
	}
}

package manufacture.expendituredata;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.table.DefaultTableModel;

public class ExpenditureData extends HashMap<String, ArrayList<Expenditure>> {
	String[] categoryOrder = { "관리", "교육", "봉사", "예배", "선교", "자본관리", "기타" };
	
	String date;
	Integer totalSum = 0;
	String totalSumStr;
	String totalSumKr;
	
	public ExpenditureData(DefaultTableModel model, String date) {
		this.date = date;
		
		// dummy data 사용시 parameter 없는 것을 실행, parameter 있는 것을 주석처리
		storeExpenditureDatas(model);
//		storeExpenditureDatas();
		
		transferToTotalSumStr();
		transferToTotalSumKr();
		
		printResult();
	}
	
	private void storeExpenditureDatas() {
		String[][] dummyData = { { "관리", "차량유지비", "교회차량주유", "71000" }, { "관리", "시설관리유지비", "화장실수리비품", "11000" }, { "관리", "차량유지비", "엔진오일교환", "100000" }, { "선교", "간사선교비", "간사선교비", "500000" }, { "교육", "교육활동비", "청년부야유회", "109500" }, { "예배", "목회자생활비", "목회생활비", "2800000" }, { "예배", "목회활동비", "목회활동비", "400000" }, { "예배", "부교역자생활비", "부교역자생활비", "1000000" }, { "예배", "연금 및 의보", "부교역자연금", "100000" } };
		
		for (int i = 0; i < dummyData.length; i++) {
			String category = dummyData[i][0];
			String subCategory = dummyData[i][1];
			String content = dummyData[i][2];
			Integer price = Integer.parseInt((String) dummyData[i][3]);
			totalSum += price;
			if (get(category) == null) {
				ArrayList<Expenditure> arrList = new ArrayList<Expenditure>();
				arrList.add(new Expenditure(category, subCategory, content, price));
				put(category, arrList);
			} else {
				get(category).add(new Expenditure(category, subCategory, content, price));
			}
		}
	}
	
	private void storeExpenditureDatas(DefaultTableModel model) {
		int row = model.getRowCount();
		
		for (int i = 0; i < row; i++) {
			String category = (String)model.getValueAt(i, 0);
			String subCategory = (String)model.getValueAt(i, 1);
			String content = (String)model.getValueAt(i, 2);
			Integer price = Integer.parseInt((String) model.getValueAt(i, 3));
			totalSum += price;
			if (get(category) == null) {
				ArrayList<Expenditure> arrList = new ArrayList<Expenditure>();
				arrList.add(new Expenditure(category, subCategory, content, price));
				put(category, arrList);
			} else {
				get(category).add(new Expenditure(category, subCategory, content, price));
			}
		}
	}
	
	private void transferToTotalSumStr() {
		totalSumStr = (new DecimalFormat("###,###")).format(totalSum);
	}
	
	private void transferToTotalSumKr() {
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
		System.out.println(totalSumStr);
	}
	
	public String[] getCategoryOrder() {
		return categoryOrder;
	}
	
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

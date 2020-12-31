package excel.form.expenditure_data;

import org.apache.poi.ss.util.CellRangeAddress;

public class ExpenditureUnit {
	final String category = "항";
	final String subCategory = "목";
	final String content = "내역";
	final String account = "금액";
	
	public int getCategoryCol() {
		return 0;
	}
	
	public CellRangeAddress getSubCategoryRange(int row) {
		return new CellRangeAddress(row, row, 1, 2);
	}
	
	public CellRangeAddress getContentRange(int row) {
		return new CellRangeAddress(row, row, 3, 6);
	}
	
	public int getAccountCol() {
		return 7;
	}
}

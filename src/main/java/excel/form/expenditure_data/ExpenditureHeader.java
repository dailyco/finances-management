 package excel.form.expenditure_data;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

public class ExpenditureHeader {
	private final int ROWSIZE = 18;
	private final int COLSIZE = 8;
	
	HSSFWorkbook workbook;
	
	final String title = "지출결의서";
	final String churchName = "대한예수교장로회 기쁨의 교회";
	final String sign = "결재";
	final String[] strArr = { "기록", "출납", "재정부장", "제직회장" };
	String dateChurchName = "";
	String totalAccount = "    일금: ";
	
	CellRangeAddress titleRange = new CellRangeAddress(0, 0, 0, COLSIZE-1);
	CellRangeAddress churchRange = new CellRangeAddress(1, 2, 0, 2);
	CellRangeAddress signRange = new CellRangeAddress(1, 2, 3, 3);
	int[][] strArrPos = { {1, 4}, {1, 5}, {1, 6}, {1, 7} };
	CellRangeAddress totalAccoutRange = new CellRangeAddress(3, 3, 0, COLSIZE-1);
	
	HSSFCellStyle headerStyle;
	HSSFCellStyle titleStyle;
	HSSFCellStyle accountStyle;
	
	public ExpenditureHeader(HSSFWorkbook workbook) {
		this.workbook = workbook;
		
		setStyle();
	}
	
	private void setStyle() {
		// header style
		headerStyle = workbook.createCellStyle();
		headerStyle.setWrapText(true);
		headerStyle.setAlignment(HorizontalAlignment.CENTER);
		headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		
		headerStyle.setBorderRight(BorderStyle.THIN);
		headerStyle.setBorderLeft(BorderStyle.THIN);
		headerStyle.setBorderTop(BorderStyle.THIN);
		headerStyle.setBorderBottom(BorderStyle.THIN);
		
		// title style
		titleStyle = workbook.createCellStyle();
		titleStyle.setAlignment(HorizontalAlignment.CENTER);
		titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		
		titleStyle.setBorderRight(BorderStyle.THIN);
		titleStyle.setBorderLeft(BorderStyle.THIN);
		titleStyle.setBorderTop(BorderStyle.THIN);
		titleStyle.setBorderBottom(BorderStyle.THIN);
		
		Font titleFont = workbook.createFont();
		titleFont.setFontHeightInPoints((short)18);
		titleFont.setBold(true);
		titleStyle.setFont(titleFont);
		
		// account style
		accountStyle = workbook.createCellStyle();
		accountStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		accountStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
		accountStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		accountStyle.setBorderRight(BorderStyle.THIN);
		accountStyle.setBorderLeft(BorderStyle.THIN);
		accountStyle.setBorderTop(BorderStyle.THIN);
		accountStyle.setBorderBottom(BorderStyle.THIN);
	}
}

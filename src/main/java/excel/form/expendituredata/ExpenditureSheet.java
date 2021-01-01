package excel.form.expendituredata;

import java.text.DecimalFormat;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

import manufacture.expendituredata.Expenditure;
import manufacture.expendituredata.ExpenditureData;

public class ExpenditureSheet {
	private final int ROWSIZE = 18;
	private final int COLSIZE = 8;
	private final short ROWUNIT = 20;
	private final short COLUNIT = 256;
	
	final short[] rowHeight = { 41, 25, 36, 41, 40, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 37, 40 };
	final short[] colWidth = { 8, 8, 8, 6, 12, 12, 12, 13 };
	
	HSSFWorkbook workbook;
	HSSFSheet sheet;
	HSSFCell[][] cells = new HSSFCell[ROWSIZE][COLSIZE];
	HSSFCellStyle commonStyle;
	
	ExpenditureHeader header;
	ExpenditureUnit main;
	
	public ExpenditureSheet(HSSFWorkbook workbook, String sheetName) {
		this.workbook = workbook;
		sheet = workbook.createSheet(sheetName);
		
		writeCommonStyle();
		createRowCol();
		writeHeader();
	};
	
	public void setDate(String date) {
		write(header.churchRange, date + header.churchName);
	}
	
	public void setTotalAccount(String accountKr, Integer account) {
		write(header.totalAccoutRange, header.totalAccount + accountKr + "(`" + (new DecimalFormat("###,###")).format(account) + ")");
	}
	
	public void writeData(ExpenditureData data) {
		int row = 5;
		for (String category : data.getCategoryOrder()) {
			if (data.get(category) != null) {
				int startRow = row;
				if (row + data.get(category).size() >= ROWSIZE) {
					row += data.get(category).size();
					break;
				}
				for (Expenditure d : data.get(category)) {
					mergeAndWrite(main.getSubCategoryRange(row), d.subCategory);
					mergeStyle(main.getSubCategoryRange(row), header.headerStyle);
					mergeAndWrite(main.getContentRange(row), d.content);
					write(row, main.getAccountCol(), d.price);
					row++;
				}
				if (startRow+1 < row) {
					mergeAndWrite(new CellRangeAddress(startRow, row-1, main.getCategoryCol(), main.getCategoryCol()), category);
					mergeStyle(new CellRangeAddress(startRow, row-1, main.getCategoryCol(), main.getCategoryCol()), header.headerStyle);
				} else {
					write(startRow, main.getCategoryCol(), category);
					setStyle(startRow, main.getCategoryCol(), header.headerStyle);
				}
			}
		}
		
		if (row >= ROWSIZE)
			System.out.println("자리 모자람!");
		else {
			for (int i = row; i < ROWSIZE-1; i++) {
				merge(main.getSubCategoryRange(i));
				merge(main.getContentRange(i));
			}
		}
		
		merge(main.getSubCategoryRange(ROWSIZE-1));
		mergeAndWrite(main.getContentRange(ROWSIZE-1), "계");
		mergeStyle(main.getContentRange(ROWSIZE-1), header.headerStyle);
		write(ROWSIZE-1, COLSIZE-1, data.getTotalSum());
	}
	
	private void writeCommonStyle() {
		commonStyle = workbook.createCellStyle();
		commonStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		
		commonStyle.setBorderRight(BorderStyle.THIN);
		commonStyle.setBorderLeft(BorderStyle.THIN);
		commonStyle.setBorderTop(BorderStyle.THIN);
		commonStyle.setBorderBottom(BorderStyle.THIN);
		
		commonStyle.setDataFormat(workbook.createDataFormat().getFormat("#,##0"));
	}
	
	private void createRowCol() {
		for (int i = 0; i < ROWSIZE; i++) {
			HSSFRow row = sheet.createRow(i);
			row.setHeight((short) (rowHeight[i] * ROWUNIT));
			for (int j = 0; j < COLSIZE; j++) {
				HSSFCell cell = row.createCell(j);
				cell.setCellStyle(commonStyle);
				cells[i][j] = cell;
			}
		}
		
		for (int i = 0; i < COLSIZE; i++) {
			sheet.setColumnWidth(i, colWidth[i] * COLUNIT);
		}
	}
	
	private void writeHeader() {
		header = new ExpenditureHeader(workbook);
		main = new ExpenditureUnit();
		
		// merge & write text
		mergeAndWrite(header.titleRange, header.title);
		mergeAndWrite(header.churchRange, header.churchName);
		mergeAndWrite(header.signRange, header.sign);
		for (int i = 0; i < header.strArr.length; i++) {
			cells[header.strArrPos[i][0]][header.strArrPos[i][1]].setCellValue(header.strArr[i]);
		}
		
		mergeAndWrite(header.totalAccoutRange, header.totalAccount);
		
		write(4, main.getCategoryCol(), main.category);
		mergeAndWrite(main.getSubCategoryRange(4), main.subCategory);
		mergeAndWrite(main.getContentRange(4), main.content);
		write(4, main.getAccountCol(), main.account);
		
		// style
		mergeStyle(header.titleRange, header.titleStyle);
		mergeStyle(header.churchRange, header.headerStyle);
		mergeStyle(header.signRange, header.headerStyle);
		for (int i = 0; i < header.strArr.length; i++) {
			setStyle(header.strArrPos[i][0], header.strArrPos[i][1], header.headerStyle);
		}
		
		mergeStyle(header.totalAccoutRange, header.accountStyle);
		
		setStyle(4, main.getCategoryCol(), header.headerStyle);
		mergeStyle(main.getSubCategoryRange(4), header.headerStyle);
		mergeStyle(main.getContentRange(4), header.headerStyle);
		setStyle(4, main.getAccountCol(), header.headerStyle);
	}
	
	private void mergeStyle(CellRangeAddress addr, HSSFCellStyle style) {
		int startRow = addr.getFirstRow();
		int endRow = addr.getLastRow();
		int startColumn = addr.getFirstColumn();
		int endColumn = addr.getLastColumn();
		
		for (int i = startRow; i <= endRow; i++) {
			for (int j = startColumn; j <= endColumn; j++) {
				cells[i][j].setCellStyle(style);
			}
		}
	}
	
	private void setStyle(int i, int j, HSSFCellStyle style) {
		cells[i][j].setCellStyle(style);
	}
	
	private void mergeAndWrite(CellRangeAddress addr, String text) {
		write(addr, text);
		merge(addr);
	}
	
	private void write(CellRangeAddress addr, String text) {
		cells[addr.getFirstRow()][addr.getFirstColumn()].setCellValue(text);
	}
	
	private void write(int i, int j, String text) {
		cells[i][j].setCellValue(text);
	}
	
	private void write(int i, int j, Integer text) {
		cells[i][j].setCellValue(text);
	}
	
	private void merge(CellRangeAddress addr) {
		sheet.addMergedRegion(addr);
	}
}

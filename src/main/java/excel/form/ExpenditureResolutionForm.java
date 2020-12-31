package excel.form;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import excel.form.expenditure_data.ExpenditureSheet;
import manufacture.datas.ExpenditureData;

public class ExpenditureResolutionForm {
	HSSFWorkbook workbook;
	ArrayList<ExpenditureSheet> sheets = new ArrayList<ExpenditureSheet>();
	
	int sheetCount = 0;
	
	public ExpenditureResolutionForm(String path, ExpenditureData data) {
		dataToFile(path, data);
	}
	
	private void fileToData(String path) {
		readFile(path);
	}
	
	private void readFile(String path) {
		try {
			FileInputStream file = new FileInputStream(path);
			workbook = new HSSFWorkbook(file);
			workbook.getSheetAt(sheetCount);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void dataToFile(String path, ExpenditureData data) {
		workbook = new HSSFWorkbook();
		ExpenditureSheet sheet = new ExpenditureSheet(workbook, "지출 결의서" + String.format("-%02d", sheetCount));
		
		sheet.setDate(data.getDate());
		sheet.setTotalAccount(data.getTotalKr(), data.getTotalSum());
		sheet.writeData(data);
		
		sheets.add(sheet);
		createFile(path);
	}
	
	private void createFile(String path) {
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(path);
			workbook.write(fos);
			fos.close();
			System.out.println("엑셀파일 생성 성공");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("엑셀파일 생성 실패");
		}
		
	}
}

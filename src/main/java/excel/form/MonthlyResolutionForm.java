package excel.form;

import excel.form.expenditure_data.ExpenditureSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MonthlyResolutionForm {
    HSSFWorkbook workbook;
    ArrayList<ExpenditureSheet> sheets = new ArrayList<ExpenditureSheet>();

    int sheetCount = 0;

    public MonthlyResolutionForm(String path, String data) {
        dataToFile(path, data);
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

    private void dataToFile(String path, String data) {
        workbook = new HSSFWorkbook();
        ExpenditureSheet sheet = new ExpenditureSheet(workbook, String.format("%s년 %s월 ", data, data) + "수입지출 보의서");

//        sheet.setDate(data.getDate());
//        sheet.setTotalAccount(data.getTotalKr(), data.getTotalSum());
//        sheet.writeData(data);

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

package excel.form;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import excel.form.import_data.ImportSheet;
import manufacture.datas.ImportData;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


public class ImportResolutionForm {
    HSSFWorkbook workbook;
    ArrayList<ImportSheet> sheets = new ArrayList<ImportSheet>();

    int sheetCount = 1;

    public ImportResolutionForm(String path, ImportData data) {
        dataToFile(path, data);
    }

//    private void fileToData(String path) {
//        readFile(path);
//    }
//
//    private void readFile(String path) {
//        try {
//            FileInputStream file = new FileInputStream(path);
//            workbook = new HSSFWorkbook(file);
//            workbook.getSheetAt(sheetCount);
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }

    private void dataToFile(String path, ImportData data) {
        int categoryIdx = 0;
        int dataIdx = -1;
        workbook = new HSSFWorkbook();
        ImportSheet sheet = new ImportSheet(workbook, sheetCount);
        sheets.add(sheet);

        sheet.writeHeader();
        sheet.setDate(data.getDate());
        sheet.setTotalAccount(data.getTotalKr(), data.getTotalSum());

        sheet.writeAside(data);
        while(true) {
            if(sheet.writeData(data, categoryIdx, dataIdx) == false) {
                categoryIdx = sheet.getCategoryIdx();
                dataIdx = sheet.getDataIdx();
                sheet = new ImportSheet(workbook, ++sheetCount);
                sheets.add(sheet);

                sheet.writeHeader();
                sheet.setDate(data.getDate());
            } else {
                break;
            }
        }

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
            e.printStackTrace();
            System.out.println("엑셀파일 생성 실패");
        }

    }




}

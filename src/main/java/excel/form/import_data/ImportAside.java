package excel.form.import_data;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

public class ImportAside {
    HSSFWorkbook workbook;

    final String category = "항목";
    final String account = "금액";
    final String totalSum = "합계";

    HSSFCellStyle categoryStyle;

    public ImportAside(HSSFWorkbook workbook) {
        this.workbook = workbook;

        setStyle();
    }

    public CellRangeAddress getCategoryRange(int row) {
        return new CellRangeAddress(row, row, 0, 1);
    }

    private void setStyle() {
        // category style
        categoryStyle = workbook.createCellStyle();
        categoryStyle.setWrapText(true);
        categoryStyle.setAlignment(HorizontalAlignment.CENTER);
        categoryStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        categoryStyle.setBorderRight(BorderStyle.THIN);
        categoryStyle.setBorderLeft(BorderStyle.THIN);
        categoryStyle.setBorderTop(BorderStyle.THIN);
        categoryStyle.setBorderBottom(BorderStyle.THIN);

        categoryStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        categoryStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    }
}

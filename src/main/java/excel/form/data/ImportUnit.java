package excel.form.data;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

public class ImportUnit {
    HSSFWorkbook workbook;

    HSSFCellStyle categoryStyle;
    HSSFCellStyle totalStyle;

    public ImportUnit(HSSFWorkbook workbook) {
        this.workbook = workbook;

        setStyle();
    }

    public CellRangeAddress getCategoryRange(int row, int col) {
        return new CellRangeAddress(row, row, col, col+2);
    }

    public CellRangeAddress getContentRange(int row, int col) {
        return new CellRangeAddress(row, row, col, col+1);
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

        categoryStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        categoryStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        // total style
        totalStyle = workbook.createCellStyle();
        totalStyle.setWrapText(true);
        totalStyle.setAlignment(HorizontalAlignment.CENTER);
        totalStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        totalStyle.setBorderRight(BorderStyle.THIN);
        totalStyle.setBorderLeft(BorderStyle.THIN);
        totalStyle.setBorderTop(BorderStyle.THIN);
        totalStyle.setBorderBottom(BorderStyle.THIN);

        totalStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
        totalStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    }
}

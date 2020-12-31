package excel.form.import_data;

import manufacture.datas.Import;
import manufacture.datas.ImportData;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ImportSheet {
    private final int ROWSIZE = 35;
    private final int COLSIZE = 9;
    private final short ROWUNIT = 20;
    private final short COLUNIT = 256;
    private final int ASIDE_START_ROW = 4;
    private final int ASIDE_END_ROW = 22;
    private final int DATA_START_ROW = 5;

    int categoryIdx = 0;
    int dataIdx = -1;
    int sheetNum;

    final short[] rowHeight = { 41, 25, 36, 27, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18};
    final short[] colWidth = { 8, 5, 12, 8, 5, 12, 5, 8, 12 };

    HSSFWorkbook workbook;
    HSSFSheet sheet;
    HSSFCell[][] cells = new HSSFCell[ROWSIZE][COLSIZE];

    HSSFCellStyle commonStyle;
    ImportHeader header;

    Import curData = null;

    public ImportSheet(HSSFWorkbook workbook, int sheetNum) {
        this.workbook = workbook;
        this.sheetNum = sheetNum;
        sheet = workbook.createSheet(String.format("수입 결의서-%02d", sheetNum));

        writeCommonStyle();
        createRowCol();
    };

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

    public void writeHeader() {
        header = new ImportHeader(workbook);

        // merge & write text
        mergeAndWrite(header.titleRange, header.title + " (" + this.sheetNum + ")");
        mergeAndWrite(header.churchRange, header.churchName);
        mergeAndWrite(header.signRange, header.sign);
        for (int i = 0; i < header.strArr.length; i++) {
            cells[header.strArrPos[i][0]][header.strArrPos[i][1]].setCellValue(header.strArr[i]);
        }

        mergeAndWrite(header.totalAccountRange, header.totalAccount);

        // style
        mergeStyle(header.titleRange, header.titleStyle);
        mergeStyle(header.churchRange, header.headerStyle);
        mergeStyle(header.signRange, header.headerStyle);
        for (int i = 0; i < header.strArr.length; i++) {
            setStyle(header.strArrPos[i][0], header.strArrPos[i][1], header.headerStyle);
        }

        mergeStyle(header.totalAccountRange, header.accountStyle);
    }

    public void writeAside(ImportData data) {
        int row = ASIDE_START_ROW;
        ImportAside aside = new ImportAside(workbook);
        mergeAndWrite(aside.getCategoryRange(row), aside.category);
        mergeStyle(aside.getCategoryRange(row), aside.categoryStyle);
        write(row, 2, aside.account);
        setStyle(row, 2, header.headerStyle);
        row++;

        for (String category : data.getCategoryOrder()) {
            mergeAndWrite(aside.getCategoryRange(row), category);
            mergeStyle(aside.getCategoryRange(row), aside.categoryStyle);
            if (data.getCategorySum().get(category) != null) {
                write(row, 2, data.getCategorySum().get(category));
            }
            row++;
        }

        merge(aside.getCategoryRange(row));
        mergeStyle(aside.getCategoryRange(row), aside.categoryStyle);
        row++;

        mergeAndWrite(aside.getCategoryRange(row), aside.totalSum);
        mergeStyle(aside.getCategoryRange(row), aside.categoryStyle);
        write(row, 2, data.getTotalSum());
    }

    public boolean writeData(ImportData data, int categoryIdx, int dataIdx) {
        int row = sheetNum == 1 ? ASIDE_END_ROW+1 : ASIDE_START_ROW;
        int col = 0;
        ImportUnit importUnit = new ImportUnit(workbook);

        String[] categories = data.getCategoryContentOrder();

        for (int i = categoryIdx; i < categories.length; i++ ) {
            String category = categories[i];
            if (data.get(category) != null) {

                // category
                if(dataIdx == -1) {
                    if (row >= ROWSIZE) {
                        if (col + 3 >= COLSIZE) { // next sheet 생성
                            this.categoryIdx = i;
                            this.dataIdx = -1;
                            return false;
                        } else { // column 변경
                            row = sheetNum == 1 ? DATA_START_ROW : DATA_START_ROW-1;
                            col += 3;
                            mergeAndWrite(importUnit.getCategoryRange(row, col), category);
                            mergeStyle(importUnit.getCategoryRange(row, col), importUnit.categoryStyle);
                        }
                    } else { // row 증가
                        mergeAndWrite(importUnit.getCategoryRange(row, col), category);
                        mergeStyle(importUnit.getCategoryRange(row, col), importUnit.categoryStyle);
                    }
                    row++;
                    dataIdx = 0;
                }


                // data
                if(dataIdx >= 0) {
                    ArrayList<Import> datas = data.get(category);
                    for (int j = dataIdx; j < datas.size(); j++) {
                        Import d = datas.get(j);
                        if (row >= ROWSIZE) {
                            if (col + 3 >= COLSIZE) { // next sheet 생성
                                this.categoryIdx = i;
                                this.dataIdx = j;
                                return false;
                            } else { // column 변경
                                row = sheetNum == 1 ? DATA_START_ROW : DATA_START_ROW-1;
                                col += 3;
                                mergeAndWrite(importUnit.getContentRange(row, col), d.content);
                                mergeStyle(importUnit.getContentRange(row, col), header.headerStyle);
                                write(row, col + 2, d.price);
                            }
                        } else { // row 증가
                            mergeAndWrite(importUnit.getContentRange(row, col), d.content);
                            mergeStyle(importUnit.getContentRange(row, col), header.headerStyle);
                            write(row, col + 2, d.price);
                        }
                        row++;
                    }

                    dataIdx = -2;
                }

                // total sum
                if(dataIdx == -2) {
                    if (row >= ROWSIZE) {
                        if (col + 3 >= COLSIZE) { // next sheet 생성
                            this.categoryIdx = i;
                            this.dataIdx = -2;
                            return false;
                        } else { // column 변경
                            row = sheetNum == 1 ? DATA_START_ROW : DATA_START_ROW-1;
                            col += 3;
                            mergeAndWrite(importUnit.getContentRange(row, col), "계");
                            mergeStyle(importUnit.getContentRange(row, col), importUnit.totalStyle);
                            write(row, col + 2, data.getCategorySum().get(category));
                        }
                    } else { // row 증가
                        mergeAndWrite(importUnit.getContentRange(row, col), "소계");
                        mergeStyle(importUnit.getContentRange(row, col), importUnit.totalStyle);
                        write(row, col + 2, data.getCategorySum().get(category));
                    }
                    row++;
                }

                dataIdx = -1;

                // blank_01
                if (row >= ROWSIZE) {
                    if (col + 3 >= COLSIZE) { // next sheet 생성
                        this.categoryIdx = i+1;
                        this.dataIdx = -1;
                        return false;
                    } else { // column 변경
                        row = sheetNum == 1 ? DATA_START_ROW : DATA_START_ROW-1;
                        col += 3;
                        continue;
                    }
                } else { // row 증가
                    merge(importUnit.getContentRange(row, col));
                    row++;
                }

                // blank_02
                if (row >= ROWSIZE) {
                    if (col + 3 >= COLSIZE) { // next sheet 생성
                        this.categoryIdx = i+1;
                        this.dataIdx = -1;
                        return false;
                    } else { // column 변경
                        row = sheetNum == 1 ? DATA_START_ROW : DATA_START_ROW-1;
                        col += 3;
                        merge(importUnit.getContentRange(row, col));
                    }
                } else { // row 증가
                    merge(importUnit.getContentRange(row, col));
                }
                row++;

            }
        }

        while(row < ROWSIZE) {
            merge(importUnit.getContentRange(row++, col));
        }

        return true;
    }

    private void mergeAndWrite(CellRangeAddress addr, String text) {
        write(addr, text);
        merge(addr);
    }

    private void write(CellRangeAddress addr, String text) {
        cells[addr.getFirstRow()][addr.getFirstColumn()].setCellValue(text);
    }

    private void merge(CellRangeAddress addr) {
        sheet.addMergedRegion(addr);
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

    public void setDate(String date) {
        write(header.churchRange, date + header.churchName);
    }

    public void setTotalAccount(String accountKr, Integer account) {
        write(header.totalAccountRange, "    일금: " + accountKr + "(₩" + (new DecimalFormat("###,###")).format(account) + ")");
    }

    private void write(int i, int j, Integer text) {
        cells[i][j].setCellValue(text);
    }

    private void write(int i, int j, String text) {
        cells[i][j].setCellValue(text);
    }

    public int getCategoryIdx() {
        return categoryIdx;
    }

    public void setCategoryIdx(int categoryIdx) {
        this.categoryIdx = categoryIdx;
    }

    public int getDataIdx() {
        return dataIdx;
    }

    public void setDataIdx(int dataIdx) {
        this.dataIdx = dataIdx;
    }
}


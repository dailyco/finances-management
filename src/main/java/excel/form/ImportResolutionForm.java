package excel.form;

import manufacture.datas.ImportData;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.swing.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ImportResolutionForm {
    HSSFWorkbook workbook = new HSSFWorkbook(); //excel 생성
    ArrayList<HSSFSheet> sheets = new ArrayList<>();

    int rowCnt = 41, colCnt = 7, bodyStartCol1 = 23, bodyStartCol2 = 6;
    int col0Width = 8, col1Width = 12, col2Width = 8, col3Width = 4, col4Width = 10, col5Width = 4, col6Width = 8, col7Width = 10;

    public void createFile(JFileChooser fileChooser, ImportData importDatas) {
        try {
            sheets.add(workbook.createSheet("수입 결의서" + "-01")); //새 Sheet 생성
            this.createRowAndCol();

            this.writeHeader(0);
            this.writeAside(0);
            this.writeBody(0);

//            FileOutputStream fileoutputstream = new FileOutputStream(fileChooser.getSelectedFile().getPath() + ".xls");
            FileOutputStream fileoutputstream = new FileOutputStream("/Users/sara/test/test" + ".xls");
            workbook.write(fileoutputstream);
            fileoutputstream.close();
            System.out.println("엑셀파일 생성 성공");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("엑셀파일 생성 실패");
        }
    }

    /**
     * CREATE ROW AND COL
     * */
    public void createRowAndCol() {
        for(int i = 0; i <= rowCnt; i++) {
            sheets.get(0).createRow(i);
            for(int j = 0; j <= colCnt; j++) {
                sheets.get(0).getRow(i).createCell(j);
                switch (j) {
                    case 0:
                        sheets.get(0).setColumnWidth(0, col0Width*256);
                        break;
                    case 1:
                        sheets.get(0).setColumnWidth(1, col1Width*256);
                        break;
                    case 2:
                        sheets.get(0).setColumnWidth(2, col2Width*256);
                        break;
                    case 3:
                        sheets.get(0).setColumnWidth(3, col3Width*256);
                        break;
                    case 4:
                        sheets.get(0).setColumnWidth(4, col4Width*256);
                        break;
                    case 5:
                        sheets.get(0).setColumnWidth(5, col5Width*256);
                        break;
                    case 6:
                        sheets.get(0).setColumnWidth(6, col6Width*256);
                        break;
                    case 7:
                        sheets.get(0).setColumnWidth(7, col7Width*256);
                        break;
                }
            }
        }
    }

    /**
     * header 쓰기
     * */
    public void writeHeader(int sheetIdx) {
        /*********************
         * Style
         *********************/
        /** Topic */
        CellStyle topicStyle = workbook.createCellStyle();
        //정렬
        topicStyle.setAlignment(HorizontalAlignment.CENTER); // 가로 가운데 정렬
        topicStyle.setVerticalAlignment(VerticalAlignment.CENTER); // 세로 가운데 정렬
        //테두리 선 (우,좌,위,아래)
        topicStyle.setBorderRight(BorderStyle.THIN);
        topicStyle.setBorderLeft(BorderStyle.THIN);
        topicStyle.setBorderTop(BorderStyle.THIN);
        topicStyle.setBorderBottom(BorderStyle.THIN);
        //폰트 설정
        Font topicFont = workbook.createFont();
        topicFont.setFontName("Arial"); // 글씨체
        topicFont.setFontHeightInPoints((short) 15); // 글자 크기
        topicFont.setBold(true); // 글씨 두께
        topicStyle.setFont(topicFont);

        /** Date */
        CellStyle dateStyle = workbook.createCellStyle();
        //정렬
        dateStyle.setAlignment(HorizontalAlignment.CENTER); // 가로 가운데 정렬
        dateStyle.setVerticalAlignment(VerticalAlignment.CENTER); // 세로 가운데 정렬
        //테두리 선 (우,좌,위,아래)
        dateStyle.setBorderRight(BorderStyle.THIN);
        dateStyle.setBorderLeft(BorderStyle.THIN);
        dateStyle.setBorderTop(BorderStyle.THIN);
        dateStyle.setBorderBottom(BorderStyle.THIN);
        // new line enable
        dateStyle.setWrapText(true);
        //폰트 설정
        Font dateFont = workbook.createFont();
        dateFont.setFontName("Arial"); // 글씨체
        dateFont.setFontHeightInPoints((short) 10); // 글자 크기
        dateFont.setBold(false); // 글씨 두께
        dateStyle.setFont(dateFont);

        /** Sign */
        CellStyle signStyle = workbook.createCellStyle();
        //정렬
        signStyle.setAlignment(HorizontalAlignment.CENTER); // 가로 가운데 정렬
        signStyle.setVerticalAlignment(VerticalAlignment.CENTER); // 세로 가운데 정렬
        //테두리 선 (우,좌,위,아래)
        signStyle.setBorderRight(BorderStyle.THIN);
        signStyle.setBorderLeft(BorderStyle.THIN);
        signStyle.setBorderTop(BorderStyle.THIN);
        signStyle.setBorderBottom(BorderStyle.THIN);
        //폰트 설정
        Font signFont = workbook.createFont();
        signFont.setFontName("Arial"); // 글씨체
        signFont.setFontHeightInPoints((short) 10);// 글자 크기
        signFont.setBold(false); // 글씨 두께
        signStyle.setFont(signFont);

        /** Total */
        CellStyle totalStyle = workbook.createCellStyle();
        //정렬
        totalStyle.setAlignment(HorizontalAlignment.GENERAL); // 가로 왼쪽 정렬
        totalStyle.setVerticalAlignment(VerticalAlignment.CENTER); // 세로 가운데 정렬
        //테두리 선 (우,좌,위,아래)
        totalStyle.setBorderRight(BorderStyle.THIN);
        totalStyle.setBorderLeft(BorderStyle.THIN);
        totalStyle.setBorderTop(BorderStyle.THIN);
        totalStyle.setBorderBottom(BorderStyle.THIN);
        //배경색
        totalStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.index);
        totalStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //폰트 설정
        Font totalFont = workbook.createFont();
        totalFont.setFontName("Arial"); // 글씨체
        totalFont.setFontHeightInPoints((short) 11); // 글자 크기
        totalFont.setColor(IndexedColors.BLACK.getIndex()); // 글자 색
        totalFont.setBold(false); // 글씨 두께
        totalStyle.setFont(totalFont);

        /*********************
         * Set Row and Col
         *********************/

        /** Topic */
        sheets.get(sheetIdx).addMergedRegion(setMergeCellStyle(sheetIdx, "수입 결의서", topicStyle, new CellRangeAddress(0, 1, 0,7)));

        /** Date */
        sheets.get(sheetIdx).addMergedRegion(setMergeCellStyle(sheetIdx,"2020년 3월 8일\n 대한예수교장로회 기쁨의교회", dateStyle, new CellRangeAddress(2, 4, 0,2)));

        /** Sign */
        // 결재
        sheets.get(sheetIdx).addMergedRegion(setMergeCellStyle(sheetIdx,"결재", signStyle, new CellRangeAddress(2, 4, 3,3)));
        // 기록
        setMergeCellStyle(sheetIdx,"기록", signStyle, new CellRangeAddress(2,2,4,4));
        sheets.get(sheetIdx).addMergedRegion(setMergeCellStyle(sheetIdx,"", signStyle, new CellRangeAddress(3, 4, 4,4)));
        // 입력
        setMergeCellStyle(sheetIdx,"입력", signStyle, new CellRangeAddress(2,2,5,5));
        sheets.get(sheetIdx).addMergedRegion(setMergeCellStyle(sheetIdx,"", signStyle, new CellRangeAddress(3, 4, 5,5)));
        // 재정부장
        setMergeCellStyle(sheetIdx,"재정부장", signStyle, new CellRangeAddress(2,2,6,6));
        sheets.get(sheetIdx).addMergedRegion(setMergeCellStyle(sheetIdx,"", signStyle, new CellRangeAddress(3, 4, 6,6)));
        // 제직회장
        setMergeCellStyle(sheetIdx,"제직회장", signStyle, new CellRangeAddress(2,2,7,7));
        sheets.get(sheetIdx).addMergedRegion(setMergeCellStyle(sheetIdx,"", signStyle, new CellRangeAddress(3, 4, 7,7)));

        /** Total */
        sheets.get(sheetIdx).addMergedRegion(setMergeCellStyle(sheetIdx,"일금: 이백팔십사만이천원정(₩2842000)", totalStyle, new CellRangeAddress(5, 5, 0,7)));
    }

    /**
     * aside 쓰기
     * */
    public void writeAside(int sheetIdx) {
        /*********************
         * Style
         *********************/
        /** Aside Topic */
        CellStyle asideTopicStyle = workbook.createCellStyle();
        //정렬
        asideTopicStyle.setAlignment(HorizontalAlignment.CENTER); // 가로 가운데 정렬
        asideTopicStyle.setVerticalAlignment(VerticalAlignment.CENTER); // 세로 가운데 정렬
        //배경색
        asideTopicStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.index);
        asideTopicStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //테두리 선 (우,좌,위,아래)
        asideTopicStyle.setBorderRight(BorderStyle.THIN);
        asideTopicStyle.setBorderLeft(BorderStyle.THIN);
        asideTopicStyle.setBorderTop(BorderStyle.THIN);
        asideTopicStyle.setBorderBottom(BorderStyle.THIN);
        //폰트 설정
        Font asideTopicFont = workbook.createFont();
        asideTopicFont.setFontName("Arial"); // 글씨체
        asideTopicFont.setFontHeightInPoints((short) 10); // 글자 크기
        asideTopicFont.setBold(false); // 글씨 두께
        asideTopicStyle.setFont(asideTopicFont);

        /** Aside Item Topic */
        CellStyle asideItemTopicStyle = workbook.createCellStyle();
        //정렬
        asideItemTopicStyle.setAlignment(HorizontalAlignment.RIGHT); // 가로 가운데 정렬
        asideItemTopicStyle.setVerticalAlignment(VerticalAlignment.CENTER); // 세로 가운데 정렬
        //테두리 선 (우,좌,위,아래)
        asideItemTopicStyle.setBorderRight(BorderStyle.THIN);
        asideItemTopicStyle.setBorderLeft(BorderStyle.THIN);
        asideItemTopicStyle.setBorderTop(BorderStyle.THIN);
        asideItemTopicStyle.setBorderBottom(BorderStyle.THIN);
        //폰트 설정
        Font asideItemTopicFont = workbook.createFont();
        asideItemTopicFont.setFontName("Arial"); // 글씨체
        asideItemTopicFont.setFontHeightInPoints((short) 10); // 글자 크기
        asideItemTopicFont.setBold(false); // 글씨 두께
        asideItemTopicStyle.setFont(asideItemTopicFont);

        /** Aside Amount */
        CellStyle asideAmountStyle = workbook.createCellStyle();
        //정렬
        asideAmountStyle.setAlignment(HorizontalAlignment.RIGHT); // 가로 가운데 정렬
        asideAmountStyle.setVerticalAlignment(VerticalAlignment.CENTER); // 세로 가운데 정렬
        //테두리 선 (우,좌,위,아래)
        asideAmountStyle.setBorderRight(BorderStyle.THIN);
        asideAmountStyle.setBorderLeft(BorderStyle.THIN);
        asideAmountStyle.setBorderTop(BorderStyle.THIN);
        asideAmountStyle.setBorderBottom(BorderStyle.THIN);
        //폰트 설정
        Font asideAmountFont = workbook.createFont();
        asideAmountFont.setFontName("Arial"); // 글씨체
        asideAmountFont.setFontHeightInPoints((short) 10); // 글자 크기
        asideAmountFont.setBold(false); // 글씨 두께
        asideAmountStyle.setFont(asideAmountFont);

        /*********************
         * Set Row and Col
         *********************/
        String[] incomes = { "주일헌금", "십일조", "감사헌금", "선교헌금", "건축헌금", "예금이자", "부활감사", "맥추감사", "성탄감사", "추수감사", "신년감사", "기타", "일시차입금", "적립금인출", "차입금", "전년도이월금" };
        String amount = "0";

        /** Aside Topic */
        setMergeCellStyle(sheetIdx, "항목", asideTopicStyle, new CellRangeAddress(6, 6, 0,0));
        setMergeCellStyle(sheetIdx, "금액", asideItemTopicStyle, new CellRangeAddress(6, 6, 1,1));
        for (int i = 0; i < incomes.length; i++) {
            setMergeCellStyle(sheetIdx, incomes[i], asideTopicStyle, new CellRangeAddress(i+7, i+7, 0,0));
            setMergeCellStyle(sheetIdx, amount, asideAmountStyle, new CellRangeAddress(i+7, i+7, 1,1));
        }
        setMergeCellStyle(sheetIdx, "합계", asideTopicStyle, new CellRangeAddress(6+incomes.length, 6+incomes.length, 0,0));
        setMergeCellStyle(sheetIdx, amount, asideAmountStyle, new CellRangeAddress(6+incomes.length, 6+incomes.length, 1,1));
    }

    /**
     * body 쓰기
     * */
    public void writeBody(int sheetIdx) {
        ArrayList category = new ArrayList();
        category.add("십일조"); category.add("감사헌금"); category.add("선교헌금");

        /*********************
         * Set Row and Col
         *********************/
        // 23~41까지... => 19 + 이후) 6~41

        int curRow = bodyStartCol1, curCol = 0;
        for(int categoryIdx = 0; categoryIdx < category.size(); categoryIdx++) {
            // 첫번째 row에 들어갈 수 없는 경우 다음 열(+2)로 넘어가기
            if(curCol == 0 && 6 > colCnt-bodyStartCol1+1) {// TODO :: size 6(key 값으로부터 length 얻기) 변경
                curRow = bodyStartCol2;
                this.writeBodyCategory(sheetIdx, new CellRangeAddress(curRow, curRow, curCol, curCol+1));
                curRow++;
            }
            // 첫번째 row에 들어가는 경우
            else {

            }
        }

        /** Body Category */
        /** Body Content */
        /** Body Total */
    }

    /**
     * body category 쓰기
     * */
    public void writeBodyCategory(int sheetIdx, CellRangeAddress position) {
        /*********************
         * Style
         *********************/
        /** Body Category */
        CellStyle bodyCategoryStyle = workbook.createCellStyle();
        //정렬
        bodyCategoryStyle.setAlignment(HorizontalAlignment.CENTER); // 가로 가운데 정렬
        bodyCategoryStyle.setVerticalAlignment(VerticalAlignment.CENTER); // 세로 가운데 정렬
        //배경색
        bodyCategoryStyle.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.index);
        bodyCategoryStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //테두리 선 (우,좌,위,아래)
        bodyCategoryStyle.setBorderRight(BorderStyle.THIN);
        bodyCategoryStyle.setBorderLeft(BorderStyle.THIN);
        bodyCategoryStyle.setBorderTop(BorderStyle.THIN);
        bodyCategoryStyle.setBorderBottom(BorderStyle.THIN);
        //폰트 설정
        Font bodyTopicFont = workbook.createFont();
        bodyTopicFont.setFontName("Arial"); // 글씨체
        bodyTopicFont.setFontHeightInPoints((short) 12); // 글자 크기
        bodyTopicFont.setBold(true); // 글씨 두께
        bodyCategoryStyle.setFont(bodyTopicFont);

        /*********************
         * Set Row and Col
         *********************/
        /** Body Category */
    }

    /**
     * body category 쓰기
     * */
    public void writeBodyContent() {
        /*********************
         * Style
         *********************/
        /** Body Content */
        CellStyle bodyContentStyle = workbook.createCellStyle();
        //정렬
        bodyContentStyle.setAlignment(HorizontalAlignment.CENTER); // 가로 가운데 정렬
        bodyContentStyle.setVerticalAlignment(VerticalAlignment.CENTER); // 세로 가운데 정렬
        //테두리 선 (우,좌,위,아래)
        bodyContentStyle.setBorderRight(BorderStyle.THIN);
        bodyContentStyle.setBorderLeft(BorderStyle.THIN);
        bodyContentStyle.setBorderTop(BorderStyle.THIN);
        bodyContentStyle.setBorderBottom(BorderStyle.THIN);
        //폰트 설정
        Font bodyContentFont = workbook.createFont();
        bodyContentFont.setFontName("Arial"); // 글씨체
        bodyContentFont.setFontHeightInPoints((short) 10); // 글자 크기
        bodyContentFont.setBold(false); // 글씨 두께
        bodyContentStyle.setFont(bodyContentFont);

        /** Body Amount */
        CellStyle bodyAmountStyle = workbook.createCellStyle();
        //정렬
        bodyAmountStyle.setAlignment(HorizontalAlignment.RIGHT); // 가로 오른쪽 정렬
        bodyAmountStyle.setVerticalAlignment(VerticalAlignment.CENTER); // 세로 가운데 정렬
        //테두리 선 (우,좌,위,아래)
        bodyAmountStyle.setBorderRight(BorderStyle.THIN);
        bodyAmountStyle.setBorderLeft(BorderStyle.THIN);
        bodyAmountStyle.setBorderTop(BorderStyle.THIN);
        bodyAmountStyle.setBorderBottom(BorderStyle.THIN);
        //폰트 설정
        Font bodyAmountFont = workbook.createFont();
        bodyAmountFont.setFontName("Arial"); // 글씨체
        bodyAmountFont.setFontHeightInPoints((short) 10); // 글자 크기
        bodyAmountFont.setBold(false); // 글씨 두께
        bodyAmountStyle.setFont(bodyAmountFont);

        /*********************
         * Set Row and Col
         *********************/
        /** Body Content */
        /** Body Amount */
    }

    /**
     * body total 쓰기
     * */
    public void writeBodyTotal() {
        /*********************
         * Style
         *********************/
        /** Body Total */
        CellStyle bodyTotalStyle = workbook.createCellStyle();
        //정렬
        bodyTotalStyle.setAlignment(HorizontalAlignment.CENTER); // 가로 가운데 정렬
        bodyTotalStyle.setVerticalAlignment(VerticalAlignment.CENTER); // 세로 가운데 정렬
        //배경색
        bodyTotalStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.index);
        bodyTotalStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //테두리 선 (우,좌,위,아래)
        bodyTotalStyle.setBorderRight(BorderStyle.THIN);
        bodyTotalStyle.setBorderLeft(BorderStyle.THIN);
        bodyTotalStyle.setBorderTop(BorderStyle.THIN);
        bodyTotalStyle.setBorderBottom(BorderStyle.THIN);
        //폰트 설정
        Font bodyTotalFont = workbook.createFont();
        bodyTotalFont.setFontName("Arial"); // 글씨체
        bodyTotalFont.setFontHeightInPoints((short) 10); // 글자 크기
        bodyTotalFont.setBold(true); // 글씨 두께
        bodyTotalStyle.setFont(bodyTotalFont);

        /** Body Total Amount */
        CellStyle bodyTotalAmountStyle = workbook.createCellStyle();
        //정렬
        bodyTotalAmountStyle.setAlignment(HorizontalAlignment.RIGHT); // 가로 가운데 정렬
        bodyTotalAmountStyle.setVerticalAlignment(VerticalAlignment.CENTER); // 세로 가운데 정렬
        //테두리 선 (우,좌,위,아래)
        bodyTotalAmountStyle.setBorderRight(BorderStyle.THIN);
        bodyTotalAmountStyle.setBorderLeft(BorderStyle.THIN);
        bodyTotalAmountStyle.setBorderTop(BorderStyle.THIN);
        bodyTotalAmountStyle.setBorderBottom(BorderStyle.THIN);
        //폰트 설정
        Font bodyTotalAmountFont = workbook.createFont();
        bodyTotalAmountFont.setFontName("Arial"); // 글씨체
        bodyTotalAmountFont.setFontHeightInPoints((short) 10); // 글자 크기
        bodyTotalAmountFont.setBold(true); // 글씨 두께
        bodyTotalAmountStyle.setFont(bodyTotalAmountFont);
        /*********************
         * Set Row and Col
         *********************/

        /** Body Total */
        /** Body Total Amount */
    }

    /**
     * position 의 각 cell style 적용
     * */
    public CellRangeAddress setMergeCellStyle(int sheetIdx, String value, CellStyle style, CellRangeAddress position) {
        HSSFCell cell;
        HSSFRow firstRow = sheets.get(sheetIdx).getRow(position.getFirstRow());
        HSSFRow lastRow = sheets.get(sheetIdx).getRow(position.getLastRow());
        for(int i = position.getFirstColumn(); i <= position.getLastColumn(); i++) {
            cell = firstRow.getCell(i);
            cell.setCellStyle(style); // 스타일 셋팅
            if(i == position.getFirstColumn() && !value.isEmpty()) cell.setCellValue(value); // file 이름 변수

            cell = lastRow.getCell(i);
            cell.setCellStyle(style);
        }

       for(int i = position.getFirstRow()+1; i <= position.getLastRow()-1; i++) {
           firstRow = sheets.get(sheetIdx).getRow(i);
           cell = firstRow.getCell(position.getFirstColumn());
           cell.setCellStyle(style);

           cell = firstRow.getCell(position.getLastColumn());
           cell.setCellStyle(style);
       }

        return position;
    }
}

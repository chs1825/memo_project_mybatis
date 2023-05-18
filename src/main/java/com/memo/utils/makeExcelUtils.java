package com.memo.utils;

import org.apache.poi.ss.usermodel.FontUnderline;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class makeExcelUtils {

    public static void main(String[] args) {

        makeExcel();

    }
    public static void makeExcel(){

        // 새로운 워크북 생성
        XSSFWorkbook workbook = new XSSFWorkbook();

        // 시트 생성
        XSSFSheet sheet = workbook.createSheet("Sheet1");

        // 데이터 입력
        Object[][] data = {
                {"이름", "나이", "직업"},
                {"최철수", 35, "마케터인데너무너무마케팅이적성에너무안맞아다른직업을찾아보고싶어하는특징을가지고있다.메롱메롱메롱이다롱메롱메롱메롱메메롱메롱메롱롱메"},
                {"최철수", 35, "마케터인데너무너무마케팅이적성에너무안맞아다른직업을찾아보고싶어하는특징을가지고있다.메롱메롱메롱이다롱메롱메롱메롱메메롱메롱메롱롱메"},
                {"최철수", 35, "마케터인데너무너무마케팅이적성에너무안맞아다른직업을찾아보고싶어하는특징을가지고있다.메롱메롱메롱이다롱메롱메롱메롱메메롱메롱메롱롱메"}
        };

        int rowNum = 0;
        System.out.println("엑셀 파일 생성 중...");

        Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
        map.put(3, new ArrayList<Integer>(Arrays.asList(3,7)));
        map.put(12, new ArrayList<Integer>(Arrays.asList(12,15)));
        map.put(21, new ArrayList<Integer>(Arrays.asList(21,27)));



        for (Object[] rowData : data) {
            XSSFRow row = sheet.createRow(rowNum++);

            int cellNum = 0;
            for (Object field : rowData) {
                XSSFCell cell = row.createCell(cellNum++);
                if (field instanceof String) {
                    cell.setCellValue((String) field);



                    if (cellNum == 3) { // 직업 부분에 대해서만 밑줄 추가

                        String text = (String) field;
                        int textLen = text.length();
                        XSSFRichTextString richTextString = new XSSFRichTextString(text);
                        for (int i= 0; i < textLen; i++){
                            if(map.containsKey(i)){

                                int startIndex = map.get(i).get(0);
                                int endIndex = map.get(i).get(1);
                                if (startIndex >= 0 && endIndex <= text.length()) {
                                    richTextString.applyFont(startIndex, endIndex, getFontWithUnderline(workbook));
                                    cell.setCellValue(richTextString);
                                }
                            }
                        }
                        cell.setCellValue(richTextString);





//                        int startIndex = 7; // 시작 인덱스
//                        int endIndex = 11; // 끝 인덱스
//                        if (startIndex >= 0 && endIndex <= text.length()) {
//                            XSSFRichTextString richTextString = new XSSFRichTextString(text);
//                            richTextString.applyFont(startIndex, endIndex, getFontWithUnderline(workbook));
//                            cell.setCellValue(richTextString);
//                        }









                    }


                } else if (field instanceof Integer) {
                    cell.setCellValue((Integer) field);
                }
            }
        }

        try {
            // 로컬 파일 경로
            String filePath = "/Users/chs/json2Excel/test.xlsx";

            // 엑셀 파일 저장
            FileOutputStream outputStream = new FileOutputStream(filePath);
            workbook.write(outputStream);
            workbook.close();
            System.out.println(filePath + " 파일이 생성되었습니다.");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    private static XSSFFont getFontWithUnderline(XSSFWorkbook workbook) {
        XSSFFont font = workbook.createFont();
        font.setUnderline(FontUnderline.SINGLE);
        return font;
    }

}

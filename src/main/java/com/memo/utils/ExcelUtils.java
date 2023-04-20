package com.memo.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ExcelUtils {

    private DecimalFormat df = new DecimalFormat("#.##");

    public List<List<Map<String, String>>> handleExcel(MultipartFile file) {

        // 결과를 담을 리스트
        List<List<Map<String, String>>> resList = new ArrayList<List<Map<String, String>>>();
        // 컬럼명 추출 리스트
        List<String> columnNameList = new ArrayList<String>();
        //파일 이름
        String fileName = file.getOriginalFilename();

        Workbook workbook;

        //1.파일 버전 체크 및 버전별 객체 생성
        if(fileName.endsWith(".xls")){
            workbook = makeHssf(file);
        }else{
            workbook = makeXssf(file);
        }

        //2.시트 생성 및 시트 데이터 추출
        Sheet sheet = workbook.getSheetAt(0);

        for(int i =0; i < sheet.getLastRowNum(); i++){
            //시트안의 row 별 데이터 추출
            List<String> cellList = getCellDataList(sheet.getRow(i));

            if(i ==0){//row에 대한 데이터가 컬럼명이라면
                columnNameList = cellList;
            }else{//결과값 생성
               resList.add(getRowDataList(columnNameList,cellList));
            }
        }
        return resList;
    }

    private List<Map<String, String>> getRowDataList(List<String> columnNameList, List<String> cellList) {

        List<Map<String, String>> resList = new ArrayList<Map<String, String>>();

        // 컬럼명과 일반 데이터를 조합하고 map에 담아 리스트에 추가하는 과정
        for (int i = 0; i < columnNameList.size(); i++) {
            Map<String, String> paramMap = new HashMap<String, String>();
            paramMap.put(columnNameList.get(i), cellList.get(i));
            resList.add(paramMap);
        }

        return resList;
    }



    //셀 데이터 추출
    public List<String> getCellDataList(Row row){

        List<String> list = new ArrayList<String>();

        for (int i = 0; i < row.getLastCellNum(); i++){
            //셀이 null인지 체크
            Cell cell = row.getCell(i,Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

            String value = "";
            switch (cell.getCellType()) {
                case FORMULA:
                    value = cell.getCellFormula();
                    break;
                case NUMERIC:
                    double v = Double.valueOf(cell.getNumericCellValue()).doubleValue();
                    value = df.format(v);
                    break;
                case STRING:
                    value = cell.getStringCellValue() + "";
                    break;
                case BLANK:
                    value = "";
                    break;
                case ERROR:
                    value = cell.getErrorCellValue() + "";
                    break;
            }
            list.add(value);
        }

        return list;

    }

    private Workbook makeHssf(MultipartFile file) {

        Workbook workbook = null;

        try {
            workbook = new HSSFWorkbook(file.getInputStream());
        } catch (IOException e) {

        }

        return workbook;
    }

    private Workbook makeXssf(MultipartFile file) {

        Workbook workbook = null;
        try {
            workbook = new XSSFWorkbook(file.getInputStream());
        } catch (IOException e) {

        }
        return workbook;
    }

}
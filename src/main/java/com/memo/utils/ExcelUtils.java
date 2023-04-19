package com.memo.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
@Slf4j
public class ExcelUtils {


    private DecimalFormat df = new DecimalFormat("#.##");
    public Map<Integer, List<String>> handleExcel(MultipartFile file) {
        Map<Integer, List<String>> resMap = new HashMap<Integer, List<String>>();

        String fileName = file.getOriginalFilename();

        if (fileName.endsWith(".xls")) {
            resMap = hssfMethod(file);
        } else {
            resMap = xssfMethod(file);
        }
        return resMap;
    }

    private Map<Integer, List<String>> xssfMethod(MultipartFile file) {

        Map<Integer, List<String>> resMap = new HashMap<Integer, List<String>>();

        try {
            InputStream inputStream = file.getInputStream();

            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);

            int cnt = 0;
            for (Row row : sheet) {
                List<String> list = new ArrayList<String>();
                log.debug("sss:,{}",row.getCell(2));
                for (Cell cell : row) {
                    log.debug("cell:,{}" , cell.getCellType());


                    String value = "";
                    log.debug("cell.toString():,{}", cell.toString());
                    log.debug("cell.getCellType() : , {}" , cell.getCellType());
                    if(cell.getCellType() == null){
                        value="a";
                    }else{
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
                                value = "a";
                                break;
                            case ERROR:
                                value = cell.getErrorCellValue() + "";
                                break;
                        }
                    }
                    list.add(value);
                }
                resMap.put(cnt, list);
                cnt++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return resMap;
    }

    private Map<Integer, List<String>> hssfMethod(MultipartFile file) {
        Map<Integer, List<String>> resMap = new HashMap<Integer, List<String>>();

        try {
            InputStream inputStream = file.getInputStream();

            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);

            int cnt = 0;
            for (Row row : sheet) {
                List<String> list = new ArrayList<String>();
                for (Cell cell : row) {
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
                            value = "a";
                            break;
                        case ERROR:
                            value = cell.getErrorCellValue() + "";
                            break;
                    }
                    list.add(value);
                }
                resMap.put(cnt, list);
                cnt++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return resMap;
    }
}

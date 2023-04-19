package  com.memo.controller;

public class Taaa {

}
//package com.example.excel_project.Controller;

//
//import com.example.excel_project.Domain.ExcelVo;
//import org.apache.poi.xssf.usermodel.XSSFCell;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.text.DecimalFormat;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//
//@Controller
//public class ExcelDataGet {
//
//    @RequestMapping(value = "/upload",method = RequestMethod.POST)
//    @ResponseBody
//    public ArrayList<HashMap<Integer, String>> excelResult(Model model) throws IOException {
//        String filePath ="/Users/insungpark/Downloads/인물리스트.xlsx";
//        ArrayList<HashMap<Integer, String>> excelList = new ArrayList<HashMap<Integer,String>>();
//        try {
//            FileInputStream fis = new FileInputStream(filePath);
//            XSSFWorkbook workbook = new XSSFWorkbook(fis);
//            //값을 담을 변수
//            HashMap<Integer, String> excelMap = new HashMap<Integer,String>();
//            //값을 담은 맵을 리스트화
//
//            int rowIndex=0;
//            int columIndex=0;
//            DecimalFormat df = new DecimalFormat(); // 숫자포맷
//            //시트 읽기
//            XSSFSheet sheet = workbook.getSheetAt(0);
//
//            //행의 수 체크
//            int rows = sheet.getPhysicalNumberOfRows();
//            for(rowIndex =0; rowIndex < rows; rowIndex++){
//                /*if(rowIndex > 2) {
//                    excelList.add(excelMap);
//                    excelMap = new HashMap<Integer,String>();
//                }*/
//
//                // 행 읽기
//                XSSFRow row = sheet.getRow(rowIndex);
//                if(row != null){
//                    //셀의 수 체크
//                    int cells = row.getPhysicalNumberOfCells();
//                    for(columIndex=0; columIndex<cells; columIndex++){
//                        //셀값 확인
//                        XSSFCell cell = row.getCell(columIndex);
//                        String value="";
//                        if(cell==null){
//                            excelMap.put(columIndex,value);
//                            continue;
//                        }else {
//                            //타입별 밸류값 넣기
//                            switch (cell.getCellType()){
//                                case FORMULA:
//                                    value =cell.getCellFormula();
//                                    break;
//                                case NUMERIC:
//                                    double v = Double.valueOf(cell.getNumericCellValue()).doubleValue();
//                                    value=df.format(v);
//                                    break;
//                                case STRING:
//                                    value=cell.getStringCellValue()+"";
//                                    break;
//                                case BLANK:
//                                    value=cell.getBooleanCellValue()+"";
//                                    break;
//                                case ERROR:
//                                    value=cell.getErrorCellValue()+"";
//                                    break;
//                            }
//                        }
//                        //map에 데이터를 담는다.
//                        excelMap.put(columIndex,value);
//
//                        System.out.println(rowIndex+"행 : " +columIndex+"번째의 값은: " + value);
//                    }
//                }
//                excelList.add(excelMap);
//                System.out.println("excelList = " + excelList);
//            }
//            //데이터 추출
//            model.addAttribute("list",excelList);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return excelList;
//    }
//}

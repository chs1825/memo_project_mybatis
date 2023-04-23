package com.memo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.memo.utils.ExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@ComponentScan(basePackages = {"com.memo"})
public class JsonServiceImpl implements JsonService{

    private ExcelUtils excelUtils;

    public JsonServiceImpl(ExcelUtils excelUtils) {
        this.excelUtils = excelUtils;
    }

    public void convertJson(MultipartFile excelFile) {
        //1.엑셀 변환
        List<Map<String, String>> dataList = excelUtils.handleExcel(excelFile);

        //2.데이터 처리

        //2-1.아이디 생성
        List<String> idList = makeJsonID(dataList.size());

        //2-2. 어절수 세기
//        int wordCnt = countWord();

        //2-3 본문 분리시켜주기

        //2-4 메타데이터 부분 만들기

        //3. 2번 목록 하나로 합치기



        //4.제이슨 파일 생성
        makeJsonFile(dataList);
    }


//    private int countWord(Map<String, String> dataMap) {
//
//        dataMap.get("보도자료 본문");
//
//    }


    private List<String> makeJsonID(int dataSize) {
        List<String> idList = new ArrayList<String>();

        for (int i = 1; i <= dataSize; i++ ){
            String id = "JSON"+ i;

            idList.add(id);
        }

        return idList;
    }

    public void makeJsonFile(List<Map<String, String>> dataList){
        try {
            String filePath = "/Users/chs/excelToJson/jsonFolder/example.json";
            ObjectMapper objectMapper = new ObjectMapper();

            File file = new File(filePath);
            FileOutputStream fileOutputStream = new FileOutputStream(file);

            objectMapper.writeValue(fileOutputStream, dataList);
            fileOutputStream.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

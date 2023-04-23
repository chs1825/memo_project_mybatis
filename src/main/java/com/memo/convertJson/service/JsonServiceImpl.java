package com.memo.convertJson.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.memo.convertJson.vo.JSonVO;
import com.memo.convertJson.vo.MetaDataVO;
import com.memo.convertJson.vo.UtteranceVO;
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
public class JsonServiceImpl implements JsonService {

    private ExcelUtils excelUtils;

    public JsonServiceImpl(ExcelUtils excelUtils) {
        this.excelUtils = excelUtils;
    }

    public void convertJson(MultipartFile excelFile) {
        //1.엑셀 변환
        List<Map<String, String>> dataList = excelUtils.handleExcel(excelFile);

        List<JSonVO> jsonList = new ArrayList<JSonVO>();
        for(int i =0; i < dataList.size(); i++){

            //2.데이터 처리
            JSonVO jSonVO = new JSonVO();
            MetaDataVO metaDataVO = new MetaDataVO();
            UtteranceVO utteranceVO = new UtteranceVO();
            String text = dataList.get(i).get("보도자료 본문");

            //2-1.아이디 생성
            jSonVO.setId("JSON"+ i);

            // 2-2 MetaDataVO 생성
            metaDataVO.setDate(Long.parseLong(dataList.get(i).get("생성일")));
            metaDataVO.setOrgan_name(dataList.get(i).get("기관명"));
            metaDataVO.setOrgan_class(dataList.get(i).get("기관 특성"));
            metaDataVO.setTitle(dataList.get(i).get("보도자료 제목"));
            metaDataVO.setCharge(dataList.get(i).get("평가 모둠"));
            //어절수 세기
            int wordCnt = countWord(text);
            metaDataVO.setWord_cnt(String.valueOf(wordCnt));

            //2-3 UtteranceVO 생성

            utteranceVO.setUtteranceList(makeUtteranceList(text));


            jSonVO.setMetaDataVO(metaDataVO);
            jSonVO.setUtteranceVO(utteranceVO);

            jsonList.add(jSonVO);
        }


        //4.제이슨 파일 생성
        makeJsonFile(dataList);
    }

    private List<Map<String, String>> makeUtteranceList(String text) {

        List<Map<String, String>> resList = new ArrayList<Map<String, String>>();


        return resList;
    }

    private int countWord(String text) {

        int res  = 0;

        return res;
    }


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

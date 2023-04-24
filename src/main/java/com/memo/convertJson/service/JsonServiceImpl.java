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

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@Service
@Slf4j
@ComponentScan(basePackages = {"com.memo"})
public class JsonServiceImpl implements JsonService {

    private ExcelUtils excelUtils;

    public JsonServiceImpl(ExcelUtils excelUtils) {
        this.excelUtils = excelUtils;
    }

    public void convertJson(MultipartFile excelFile, HttpServletResponse res) {
        //1.엑셀 변환
        List<Map<String, String>> dataList = excelUtils.handleExcel(excelFile);

        log.debug(dataList.toString());
        log.debug("데이터 개수 : {}", dataList.size());
        List<JSonVO> jsonList = new ArrayList<JSonVO>();
        for(int i =0; i < dataList.size(); i++){

            //2.데이터 처리
            JSonVO jSonVO = new JSonVO();
            MetaDataVO metaDataVO = new MetaDataVO();
            String text = dataList.get(i).get("보도자료 본문");

            //2-1.아이디 생성
            jSonVO.setId("JSON"+ (i+1));

            // 2-2 MetaDataVO 생성
            metaDataVO.setDate(Long.parseLong(dataList.get(i).get("생성일")));
            metaDataVO.setOrgan_name(dataList.get(i).get("기관명"));
            metaDataVO.setOrgan_class(dataList.get(i).get("기관 특성"));
            metaDataVO.setTitle(dataList.get(i).get("보도자료 제목"));
            metaDataVO.setCharge(dataList.get(i).get("평가 모둠"));
            //어절수 세기
            int wordCnt = countWord(text);
            metaDataVO.setWord_cnt(String.valueOf(wordCnt));

            //3. jsonVO 완성
            jSonVO.setMetaDataVO(metaDataVO);
            jSonVO.setUtteranceVOList(makeUtteranceList(text));

            //4. 리스트에 넣어주기
            jsonList.add(jSonVO);
        }

        log.debug("처리된 데이터 개수 : {}" , jsonList.size());

        //4.제이슨 파일 생성
        String path = makeJsonFile(jsonList, res);

        //5.파일 다운로드
        downLoadFile(path);


    }

    private List<UtteranceVO> makeUtteranceList(String text) {

        List<UtteranceVO> resList = new ArrayList<UtteranceVO>();

        String[] textArr = text.split("\\n\n");

        int cnt = 1;
        for(String item : textArr){
            UtteranceVO utteranceVO = new UtteranceVO();
            utteranceVO.setId(String.valueOf(cnt));
            utteranceVO.setForm(item);
            resList.add(utteranceVO);
            cnt++;
        }
        return resList;
    }

    private int countWord(String text) {
        int res  = 0;

        if(text == null || text.isEmpty()){
            return 0;
        }

        String[] words = text.split("\\s+");
//        log.debug("어절어절어절수: , {}" , words.length);
        res = words.length;

        return res;
    }


    public String makeJsonFile(List<JSonVO> dataList, HttpServletResponse res){
        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//            String filePath = "/Users/chs/excelToJson/jsonFolder/excel2json.json";
//            File file = new File(filePath);
//            FileOutputStream fileOutputStream = new FileOutputStream(file);
//
//            objectMapper.writeValue(fileOutputStream, dataList);
//            fileOutputStream.close();
            ////----------------------
            ObjectMapper objectMapper = new ObjectMapper();
            // .json 파일 생성
            String filePath = "/Users/chs/excelToJson/jsonFolder/excel2json.json";
            File file = new File(filePath);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            objectMapper.writeValue(fileOutputStream, dataList);
            fileOutputStream.close();

            return filePath;

            //다운로드
            String fileName = file.getName();
            FileInputStream fileInputStream = new FileInputStream(file);


//            byte[] jsonData = fileInputStream.readAllBytes();
            byte[] buffer = new byte[1024];
            int bytesRead;
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            byte[] jsonData = outputStream.toByteArray();
            fileInputStream.close();

            res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            res.setContentType("application/json");

            res.setContentLength(jsonData.length);

            ServletOutputStream servletOutputStream = res.getOutputStream();
            servletOutputStream.write(jsonData);
            servletOutputStream.flush();
            servletOutputStream.close();



        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

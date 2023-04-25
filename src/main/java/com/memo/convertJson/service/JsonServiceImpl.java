package com.memo.convertJson.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
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

    public String convertJson(MultipartFile excelFile) throws IOException {
        //1.엑셀 변환
        List<Map<String, String>> dataList = excelUtils.handleExcel(excelFile);

//        log.debug(dataList.toString());
        log.debug("데이터 개수 : {}", dataList.size());
        List<JSonVO> jsonList = new ArrayList<JSonVO>();
        for(int i =0; i < dataList.size(); i++){

            //2.데이터 처리
            JSonVO jSonVO = new JSonVO();
            MetaDataVO metaDataVO = new MetaDataVO();
            String text = dataList.get(i).get("보도자료 본문");

            //2-1.아이디 생성
            jSonVO.setId("JSON"+ (i+1));
            log.debug("날짜: {}", dataList.get(i).get("생성일"));
            // 2-2 MetaDataVO 생성
            metaDataVO.setDate(dataList.get(i).get("생성일"));
            metaDataVO.setOrgan_name(dataList.get(i).get("기관명"));
            metaDataVO.setOrgan_class(dataList.get(i).get("기관 특성"));
            metaDataVO.setTitle(dataList.get(i).get("보도자료 제목"));
            metaDataVO.setCharge(dataList.get(i).get("평가 모둠"));
            //어절수 세기
            int wordCnt = countWord(text);
            metaDataVO.setWord_cnt(String.valueOf(wordCnt));

            //3. jsonVO 완성
            jSonVO.setMetaData(metaDataVO);
            jSonVO.setUtterance(makeUtteranceList(text));

            //4. 리스트에 넣어주기
            jsonList.add(jSonVO);
        }

        log.debug("처리된 데이터 개수 : {}" , jsonList.size());

        //4.제이슨 파일 생성
        String path = makeJsonFile(jsonList);

        return path;

        //5.파일 다운로드
//        downLoadFile(path, res);


    }

    @Override
    public void downloadJson(String path, HttpServletResponse res) throws IOException {
        File file = new File(path);
        String fileName = file.getName();

        res.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        res.setContentType("application/json");

        InputStream fileInputStream = new FileInputStream(file);
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        ServletOutputStream outputStream = res.getOutputStream();
        OutputStream outputStream = res.getOutputStream();

        byte[] buffer = new byte[1024];
        int bytesRead;

        while ((bytesRead = fileInputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

//        byte[] jsonData = outputStream.toByteArray();
        outputStream.flush();
        outputStream.close();
        fileInputStream.close();
    }

    private void downLoadFile(String path, HttpServletResponse res) throws IOException {

        File file = new File(path);
        String fileName = file.getName();

        res.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        res.setContentType("application/json");

        InputStream fileInputStream = new FileInputStream(file);
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        ServletOutputStream outputStream = res.getOutputStream();
        OutputStream outputStream = res.getOutputStream();

        byte[] buffer = new byte[1024];
        int bytesRead;

        while ((bytesRead = fileInputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

//        byte[] jsonData = outputStream.toByteArray();
        outputStream.flush();
        outputStream.close();
        fileInputStream.close();
        //다운로드

//        res.setContentLength(jsonData.length);

//        ServletOutputStream servletOutputStream = res.getOutputStream();
//        servletOutputStream.write(jsonData);
//        servletOutputStream.flush();
//        servletOutputStream.close();

        // 파일을 삭제합니다.
//        file.delete();

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


    public String makeJsonFile(List<JSonVO> dataList){
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
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.enable(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED);
            objectMapper.enable(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN);

            // .json 파일 생성
            String filePath = "/Users/chs/excelToJson/jsonFolder/excel2json.json";
            File file = new File(filePath);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            objectMapper.writeValue(fileOutputStream, dataList);
            fileOutputStream.close();

            return filePath;
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
}

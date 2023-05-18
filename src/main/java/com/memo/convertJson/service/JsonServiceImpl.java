package com.memo.convertJson.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.memo.convertJson.vo.InfoAboutJsonVO;
import com.memo.convertJson.vo.JSonVO;
import com.memo.convertJson.vo.MetaDataVO;
import com.memo.convertJson.vo.UtteranceVO;
import com.memo.utils.Excel2JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@ComponentScan(basePackages = {"com.memo"})
public class JsonServiceImpl implements JsonService {

    private Excel2JsonUtils excel2JsonUtils;

    public JsonServiceImpl(Excel2JsonUtils excel2JsonUtils) {
        this.excel2JsonUtils = excel2JsonUtils;
    }

    public InfoAboutJsonVO convertJson(MultipartFile excelFile) throws IOException {
        //1.엑셀 변환
        List<Map<String, String>> dataList = excel2JsonUtils.handleExcel(excelFile);

//        log.debug(dataList.toString());
        log.debug("데이터 개수 : {}", dataList.size());
        List<JSonVO> jsonList = new LinkedList<JSonVO>(); // 삽입만 할 리스트이기 때문에 LinkedList 사용
        int cntForUtterence = 0; // 사이즈 1인 utterance 개수
        int cntForUtterenceAndEnter = 0; // 사이즈 1이면서 개행없는 utterance 개수
        List<String> chkList1 = new ArrayList<String>(); // 사이즈 1인 utterance 아이디 담는 리스트
        List<String> chkList2 = new ArrayList<String>(); // 사이즈가 1이면서 개행이 없는 utterance 다는 아이디 리스트

        for(int i =0; i < dataList.size(); i++){

            //2.데이터 처리
            JSonVO jSonVO = new JSonVO();
            MetaDataVO metaDataVO = new MetaDataVO();
            String text = dataList.get(i).get("보도자료 본문");

            //2-1.아이디 생성
            jSonVO.setId("JSON"+ (i+1));
            log.debug(jSonVO.getId());
//            log.debug("날짜: {}", dataList.get(i).get("생성일"));

            // 2-2 MetaDataVO 생성
            metaDataVO.setDate(dataList.get(i).get("생성일"));
            metaDataVO.setOrgan_name(dataList.get(i).get("기관명"));
            metaDataVO.setOrgan_class(dataList.get(i).get("기관 특성"));
            metaDataVO.setTitle(dataList.get(i).get("보도자료 제목"));
            metaDataVO.setCharge(dataList.get(i).get("평가 모둠"));


            //3. jsonVO 완성
            jSonVO.setMetaData(metaDataVO);

            //utterance 만들기
            List<String> sList = splitTextForUtternce(text);
            List<UtteranceVO> utteranceVOList = makeUtterenceVOList(sList);

            //어절수 세기
            int wordCnt = getWord_cnt(sList);
            metaDataVO.setWord_cnt(String.valueOf(wordCnt));

            //utterence 사이즈 확인하기
            if(utteranceVOList.size() == 1) {
                cntForUtterence++;
                chkList1.add(jSonVO.getId());
                if(!utteranceVOList.get(0).getForm().contains("\n")){
                    cntForUtterenceAndEnter++;
                    chkList2.add(jSonVO.getId());
                }
            }
            jSonVO.setUtterance(utteranceVOList);

            //4. 리스트에 넣어주기
            jsonList.add(jSonVO);
        }

        //4.제이슨 파일 생성
        String path = makeJsonFile(jsonList);

        //5. 반환 정보 세팅
        InfoAboutJsonVO infoVO = new InfoAboutJsonVO();

        infoVO.setPath(path);
        infoVO.setDataSize(jsonList.size());
        infoVO.setUtterenceNum(cntForUtterence);
        infoVO.setUtterenceNumId(chkList1);
        infoVO.setNoEnterUtterenceNum(cntForUtterenceAndEnter);
        infoVO.setNoEnterUtterenceNumId(chkList2);

        return infoVO;

    }



    private List<String> splitTextForUtternce(String text) {

        String[] textArr = text.split("\\r+\\n+");

        List<String> sList = new ArrayList<String>();
        for (String item : textArr) {
            String[] arr = item.split("\n\\s*\n");

            for(int i =0; i < arr.length; i++){
                if(arr[i].matches("\\s*")){
                    continue;
                }else{
                    arr[i] = arr[i].replaceAll("\\s+$", "");
                    sList.add(arr[i]);
                }
            }
        }

        int cnt = 1;
        List<String> resList = new ArrayList<String>();
        for (int i = 0; i < sList.size(); i++) {
            String item = sList.get(i);
            if(item.startsWith("\n")){
                item = item.substring(1);
            }

            if(item.endsWith("\n")){
                item = item.substring(0, item.length() - 1);
            }

            resList.add(item);
        }

        return resList;
    }


    public List<UtteranceVO> makeUtterenceVOList(List<String> sList){
        List<UtteranceVO> resList = new ArrayList<UtteranceVO>();
        int cnt = 1;
        for(String item : sList){
            UtteranceVO utteranceVO = new UtteranceVO();

            utteranceVO.setId(String.valueOf(cnt));
            utteranceVO.setForm(item);
            resList.add(utteranceVO);
            cnt++;
        }

        return resList;
    }


    public int getWord_cnt(List<String> sList){


        int res = 0;
        for (int i = 0; i < sList.size(); i++) {

            String item = sList.get(i);
//            System.out.println(String.valueOf(countWord(item)));
            res += countWord(item);
        }
//        System.out.println("총합 = " + res);
        return res;
    }

    private int countWord(String text) {
        int res  = 0;


        if(text == null || text.isEmpty()){
            return 0;
        }

        String[] words = text.split("\\ +");
//        String[] words = text.split("\\s+|\\n");
//        String[] words = text.split("\\s+");  //나중 업무 대응용
        res = words.length;

        return res;
    }


    public String makeJsonFile(List<JSonVO> dataList){
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
//            objectMapper.enable(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED);
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

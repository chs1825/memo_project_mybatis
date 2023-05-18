package com.memo.service;

import com.memo.mapper.ExcelMapper;
import com.memo.utils.Excel2JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
@Slf4j
@ComponentScan(basePackages = {"com.memo"})
public class ExcelServiceImpl implements ExcelService {

    //    private ExcelMapper excelMapper;
    private Excel2JsonUtils excel2JsonUtils;
    private ExcelMapper excelMapper;

    public ExcelServiceImpl(ExcelMapper excelMapper, Excel2JsonUtils excel2JsonUtils) {
        this.excelMapper = excelMapper;
        this.excel2JsonUtils = excel2JsonUtils;
    }

    public void insertExcel(MultipartFile excelFile) {


        List<Map<String, String>> processedList = new ArrayList<Map<String, String>>();
        //1. 엑셀 처리
        List<Map<String, String>> dataList = excel2JsonUtils.handleExcel(excelFile);

        for (int i = 0; i < dataList.size(); i++) {

            Map<String, String> dataMap = dataList.get(i);
            log.debug("dataMap 검사:,{}", dataMap);
            log.debug("검사:,{}", dataMap.get("nation"));

            if (dataMap.get("nation").contains(",")) {
                //',' 존재시 분리해주는 로직
                processedList.addAll(splitMapByComma(dataMap));
            } else {
                processedList.add(dataMap);
            }
        }

//        log.debug("잘 들어 갔을까?:,{}", processedList);


        excelMapper.insertUserByExcel(dataList);
        excelMapper.insertNationByExcel(processedList);
    }





    private List<Map<String, String>> splitMapByComma(Map<String, String> dataMap) {
        List<Map<String, String>> resList = new ArrayList<Map<String, String>>();
        String[] arr = dataMap.get("nation").split(",");
        List<String> keyList = new ArrayList<String>();

        for (Map.Entry<String, String> pair : dataMap.entrySet()) {
            if (!pair.getKey().equals("nation")) {
                keyList.add(pair.getKey());
            }
        }

        log.debug("키 꺼내오기 작업 끝:,{}", keyList);

        for (String s : arr) {
            Map<String, String> map = new HashMap<String, String>();
            for (String ss : keyList) {
                map.put(ss, dataMap.get(ss));
            }
            map.put("nation", s);
            resList.add(map);
        }
        return resList;

    }
}

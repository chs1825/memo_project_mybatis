package com.memo.service;

import com.memo.mapper.ExcelMapper;
import com.memo.utils.ExcelUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ExcelServiceImpl implements ExcelService{

    private ExcelMapper excelMapper;
    private ExcelUtils excelUtils;

    public ExcelServiceImpl(ExcelMapper excelMapper
                            ,ExcelUtils excelUtils
    ) {
        this.excelUtils = excelUtils;
        this.excelMapper = excelMapper;
    }

    public void insertExcel(MultipartFile excelFile) {




        //1. 엑셀 처리
        List<Map<String, String>> DataList = excelUtils.handleExcel(excelFile);

        for (int i = 0; i < DataList.size(); i++){
            Map<String, String> mapList = DataList.get(i);
            log.debug("검사:,{}",mapList.get("nation"));

            if(mapList.get("nation").contains(",")){

            }else{

            }

        }


//        excelMapper.insertExcel(map);
    }
}

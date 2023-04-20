package com.memo.service;

import com.memo.mapper.ExcelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
public class ExcelServiceImpl implements ExcelService{

    private ExcelMapper excelMapper;

    public ExcelServiceImpl(ExcelMapper excelMapper) {
        this.excelMapper = excelMapper;
    }

    public void insertExcel(MultipartFile excelFile) {

        excelMapper.insertExcel(map);
    }
}

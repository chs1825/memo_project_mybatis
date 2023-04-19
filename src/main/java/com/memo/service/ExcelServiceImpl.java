package com.memo.service;

import com.memo.mapper.ExcelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ExcelServiceImpl implements ExcelService{

    private ExcelMapper excelMapper;

    public ExcelServiceImpl(ExcelMapper excelMapper) {
        this.excelMapper = excelMapper;
    }

    public void insertExcel(Map<String, List<String>> map) {
        excelMapper.insertExcel(map);
    }
}

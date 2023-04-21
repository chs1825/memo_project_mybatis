package com.memo.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


public interface ExcelService {

    public void insertExcel(MultipartFile excelFile);

}

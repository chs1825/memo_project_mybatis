package com.memo.convertJson.service;

import org.springframework.web.multipart.MultipartFile;

public interface JsonService {

    public void convertJson(MultipartFile excelFile);

}

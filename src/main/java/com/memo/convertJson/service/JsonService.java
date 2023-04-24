package com.memo.convertJson.service;

import com.memo.convertJson.vo.JSonVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface JsonService {

    public void convertJson(MultipartFile excelFile, HttpServletResponse res);

}

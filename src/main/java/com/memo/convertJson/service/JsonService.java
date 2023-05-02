package com.memo.convertJson.service;

import com.memo.convertJson.vo.InfoAboutJsonVO;
import com.memo.convertJson.vo.JSonVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface JsonService {

    public InfoAboutJsonVO convertJson(MultipartFile excelFile) throws IOException;

//    public void downloadJson(String path, HttpServletResponse res) throws IOException;

}

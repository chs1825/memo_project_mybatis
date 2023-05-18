package com.memo.controller;

import com.memo.signhand.service.SignhandService;
import com.memo.signhand.vo.SearchKeywordVO;
import com.memo.signhand.vo.SignWordVO;
import com.memo.signhand.vo.SignhandVO;
import com.memo.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/")
public class SignHandleController {

    @Autowired
    private SignhandService signhandService;


    @RequestMapping("singHandPage.do")
    public String signPage(Model model){

        return "signPage";
    }


    @ResponseBody
    @RequestMapping("sendData.do")
    public Map<String,List<SignhandVO>> sendData(){

        Map<String,List<SignhandVO>> resMap = signhandService.bindSignhand();
        return resMap;
    }

    @RequestMapping("searchWord.do")
    @ResponseBody
    public List<SignWordVO> searchWord(
            @RequestBody SearchKeywordVO searchKeywordVO
//            @RequestBody  String abc
    ){
        log.debug("체크");
        log.debug(String.valueOf(searchKeywordVO));
//        log.debug(abc);
        List<SignWordVO> resList = signhandService.searchWord(searchKeywordVO);

        return resList;

    }






}

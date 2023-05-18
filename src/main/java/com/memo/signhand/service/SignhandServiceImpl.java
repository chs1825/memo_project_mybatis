package com.memo.signhand.service;

import com.memo.signhand.mapper.SignhandMapper;
import com.memo.signhand.vo.SearchKeywordVO;
import com.memo.signhand.vo.SignWordVO;
import com.memo.signhand.vo.SignhandVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class SignhandServiceImpl implements SignhandService{

    @Autowired
    private SignhandMapper signhandMapper;


    @Override
    public List<SignhandVO> getAllSignhand() {

        return signhandMapper.selectAllSignhand();

    }

    @Override
    public Map<String, List<SignhandVO>> bindSignhand() {


        Map<String, List<SignhandVO>> resMap = new HashMap<String, List<SignhandVO>>();


        resMap.put("hand", signhandMapper.selectSignhandByVariation("hand"));
        resMap.put("position", signhandMapper.selectSignhandByVariation("hand_position"));
        resMap.put("number", signhandMapper.selectSignhandByVariation("hand_number"));
        resMap.put("matchingType", signhandMapper.selectSignhandByVariation("hand_matching_type"));


        log.debug("체크");
        log.debug(resMap.get("hand").toString());
        log.debug(String.valueOf(resMap.get("hand").size()));
        log.debug(resMap.get("position").toString());
        log.debug(String.valueOf(resMap.get("position").size()));
        log.debug(resMap.get("number").toString());
        log.debug(String.valueOf(resMap.get("number").size()));
        log.debug(resMap.get("matchingType").toString());
        log.debug(String.valueOf(resMap.get("matchingType").size()));

        return resMap;

    }

    @Override
    public List<SignWordVO> searchWord(SearchKeywordVO searchKeywordVO) {

        List<SignWordVO> resList = signhandMapper.selectWordByKeyword(searchKeywordVO);
        log.debug("resList : {}" , resList);
        return resList;
    }

}

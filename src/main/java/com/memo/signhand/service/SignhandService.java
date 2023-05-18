package com.memo.signhand.service;

import com.memo.signhand.vo.SearchKeywordVO;
import com.memo.signhand.vo.SignWordVO;
import com.memo.signhand.vo.SignhandVO;

import java.util.List;
import java.util.Map;

public interface SignhandService {

    public List<SignhandVO> getAllSignhand();

    public Map<String, List<SignhandVO>> bindSignhand();

    List<SignWordVO> searchWord(SearchKeywordVO searchKeywordVO);
}

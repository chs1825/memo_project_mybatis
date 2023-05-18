package com.memo.signhand.mapper;

import com.memo.signhand.vo.SearchKeywordVO;
import com.memo.signhand.vo.SignWordVO;
import com.memo.signhand.vo.SignhandVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SignhandMapper {

    public List<SignhandVO> selectAllSignhand();

    public List<SignhandVO> selectSignhandByVariation(String variation);

    public List<SignWordVO> selectWordByKeyword(SearchKeywordVO searchKeywordVO);
}

package com.memo.mapper;

import com.memo.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ExcelMapper {


    public void insertUserByExcel(List<Map<String, String>> processedList);

    public void insertNationByExcel(List<Map<String, String>> processedList);
}

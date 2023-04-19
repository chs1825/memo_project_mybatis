package com.memo.mapper;

import com.memo.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ExcelMapper {

    public void insertExcel(Map<String, List<String>> map);

}

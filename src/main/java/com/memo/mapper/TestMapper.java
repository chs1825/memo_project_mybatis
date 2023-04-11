package com.memo.mapper;

import com.memo.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestMapper {

    public List<UserVO> selectUserAll();
}

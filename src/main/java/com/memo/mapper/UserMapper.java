package com.memo.mapper;

import com.memo.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    public UserVO selectById(String id);
}

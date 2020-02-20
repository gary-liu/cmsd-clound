package com.lxjy.user.service.dao;

import com.lxjy.user.service.moudle.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {

    User  findUserById(Integer id);
}

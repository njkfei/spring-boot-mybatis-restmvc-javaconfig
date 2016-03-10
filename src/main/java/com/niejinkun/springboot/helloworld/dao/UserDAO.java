package com.niejinkun.springboot.helloworld.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.niejinkun.springboot.helloworld.model.User;


/**
 * 用户表相关操作
 * @author sanhao
 *
 */
public interface UserDAO {

	@Select("select * from ysyy_user where user_id=#{user_id} and user_type=#{user_type}")
	User getUser(@Param("user_id")int user_id,@Param("user_type")int user_type);
}

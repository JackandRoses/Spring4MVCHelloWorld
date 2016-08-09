package com.javahash.spring.mapper;

import com.javahash.spring.po.User;

public interface SelectUserMapper {
	public User selectUserById(Integer id);
}

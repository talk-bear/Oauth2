package com.qiyun.qy.mapper.mysql;

import com.qiyun.qy.entity.User;

public interface IUserDao {
	
	User findByUserKey(String key);
	
}

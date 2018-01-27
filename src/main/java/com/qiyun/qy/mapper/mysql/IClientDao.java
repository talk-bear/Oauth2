package com.qiyun.qy.mapper.mysql;

import com.qiyun.qy.entity.Client;

public interface IClientDao {

	Client loadClientByClientId(String clientId);
	
}

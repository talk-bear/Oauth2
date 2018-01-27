package com.qiyun.qy.config.data.sql;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
public class DataSourceConfig {

	@Primary
	@Bean("mysqlDataSource")
	@ConfigurationProperties("datasource.sql.mysql")
	public DataSource mysqlDataSource() {
		DruidDataSource dataSource = new DruidDataSource();
	    return dataSource;
	}

	@Bean("oracleDataSource")
	@ConditionalOnProperty(prefix = "datasource.sql.oracle",  name = "enable", havingValue = "false")
	@ConfigurationProperties("datasource.sql.oracle")
	public DataSource oracleDataSource() {
		DruidDataSource dataSource = new DruidDataSource();
	    return dataSource;
	}
}

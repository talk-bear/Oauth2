package com.qiyun.qy.config.mybatis;

import java.util.Properties;

//import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
//import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.github.pagehelper.PageInterceptor;

//import com.github.pagehelper.PageInterceptor;

@Configuration  
@MapperScan(basePackages = MysqlConfig.PACKAGE, 
			sqlSessionFactoryRef = "mysqlSessionFactory") 
public class MysqlConfig {
	
	static final String PACKAGE = "com.qiyun.qy.mapper.mysql";  
    static final String MAPPER_LOCATION = "classpath:mapper/mysql/*.xml";  
    
    @Autowired
    private DataSource mysql;
    
    @Primary
    @Bean(name = "mysqlTransactionManager")  
    public DataSourceTransactionManager mysqlManager() {  
        return new DataSourceTransactionManager(mysql);  
    }  

    @Primary
    @Bean(name = "mysqlSessionFactory")  
    public SqlSessionFactory mysqlSessionFactory() throws Exception {  
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();  
        sessionFactory.setDataSource(mysql);  
        sessionFactory.setMapperLocations(
        		new PathMatchingResourcePatternResolver()  
                	.getResources(MAPPER_LOCATION));  
        
        //分页插件
        Properties properties = new Properties();
        properties.setProperty("helperDialect", "mysql");
        properties.setProperty("offsetAsPageNum", "true");
        properties.setProperty("rowBoundsWithCount", "true");
        properties.setProperty("reasonable", "true");
        Interceptor interceptor = new PageInterceptor();
        interceptor.setProperties(properties);
        sessionFactory.setPlugins(new Interceptor[] {interceptor});
        
        return sessionFactory.getObject();  
    }  

}

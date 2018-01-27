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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.github.pagehelper.PageInterceptor;

//import com.github.pagehelper.PageInterceptor;

@Configuration  
@ConditionalOnBean(name = "oracleDataSource")
@MapperScan(basePackages = OracleConfig.PACKAGE, 
			sqlSessionFactoryRef = "oracleSessionFactory")
public class OracleConfig {
	static final String PACKAGE = "com.qiyun.qy.mapper.oracle";  
    static final String MAPPER_LOCATION = "classpath:mapper/oracle/*.xml";  
    
    @Autowired
    @Qualifier("oracleDataSource")
    private DataSource oracle;
    
    @Bean(name = "oracleTransactionManager")  
    public DataSourceTransactionManager mysqlManager() {  
        return new DataSourceTransactionManager(oracle);  
    }  

    @Bean(name = "oracleSessionFactory")  
    public SqlSessionFactory oracleSessionFactory() throws Exception {  
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();  
        sessionFactory.setDataSource(oracle);  
        sessionFactory.setMapperLocations(
        		new PathMatchingResourcePatternResolver()  
                	.getResources(MAPPER_LOCATION));  
        
        //分页插件
        Properties properties = new Properties();
        properties.setProperty("helperDialect", "oracle");
        properties.setProperty("offsetAsPageNum", "true");
        properties.setProperty("rowBoundsWithCount", "true");
        properties.setProperty("reasonable", "true");
        Interceptor interceptor = new PageInterceptor();
        interceptor.setProperties(properties);
        sessionFactory.setPlugins(new Interceptor[] {interceptor});
        
        return sessionFactory.getObject();  
    }  

}

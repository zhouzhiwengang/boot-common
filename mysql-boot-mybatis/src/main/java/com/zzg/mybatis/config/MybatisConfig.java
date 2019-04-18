package com.zzg.mybatis.config;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;
import com.zzg.mybatis.pool.entity.PoolEntity;

@Configuration
public class MybatisConfig {
	@Autowired
	private PoolEntity entity;
	
	// alibaba 数据库连接池
	@Bean
    public DruidDataSource dataSource() {
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName(entity.getDriverClassName().trim());
        ds.setUrl(entity.getUrl().trim());
        ds.setUsername(entity.getUsername().trim());
        ds.setPassword(entity.getPassword().trim());
        ds.setInitialSize(Integer.valueOf(entity.getInitialSize()));
        System.out.println("---------------com.zz.mybatis.config.DruidDataSource---------------");
        return ds;
    }
	
	// 数据库事务管理器
	@Bean
    public DataSourceTransactionManager dataSourceTransactionManager() {
		 System.out.println("---------------com.zz.mybatis.config.DataSourceTransactionManager---------------");
        return new DataSourceTransactionManager(dataSource());
    }
	
	// SqlSessionFactory 工厂实例对象
	@Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        // 设置数据库连接池
        sessionFactory.setDataSource(dataSource());
        // 设置扫描mapper文件(各功能模块Mapper 文件存放规则定义 )
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/**/*Mapper.xml"));
        System.out.println("---------------com.zz.mybatis.config.SqlSessionFactory---------------");
        return sessionFactory.getObject();
    }
	
	// 数据库连接
	@Bean(name="sessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		 System.out.println("---------------com.zz.mybatis.config.SqlSessionTemplate---------------");
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}

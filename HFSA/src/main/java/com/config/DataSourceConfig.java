package com.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DataSourceConfig {
	//WHO 用来区分是小组谁在用，以控制数据库信息，请修改时只修改WHO的信息
	//不要动源码内容
	private final String WHO = "lx";
	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		if (WHO.equals("icecity")) {
			dataSource.setUrl("jdbc:mysql://localhost:3306/HFSA");
			dataSource.setUsername("root");
			dataSource.setPassword("lmy19960521");
		} else if (WHO.equals("lx")) {
			//lx 修改个人配置区
			dataSource.setUrl("jdbc:mysql://localhost:3306/HFSA");
			dataSource.setUsername("root");
			dataSource.setPassword("5221");
		} else {
			//gaoxy 修改个人配置区域
			dataSource.setUrl("jdbc:mysql://localhost:3306/HFSA");
			dataSource.setUsername("root");
			dataSource.setPassword("lmy19960521");
		}
		return dataSource;
	}
}


package com.github.honourednihilist.gradle.postgresql.embedded.example;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

import lombok.Setter;

@SpringBootApplication(exclude = LiquibaseAutoConfiguration.class)
@EnableTransactionManagement
public class Application {

	@Setter
	@Value("${gradle-postgresql-embedded-example.db.driver}")
	private String driver;

	@Setter
	@Value("${gradle-postgresql-embedded-example.db.url}")
	private String url;

	@Setter
	@Value("${gradle-postgresql-embedded-example.db.username}")
	private String username;

	@Setter
	@Value("${gradle-postgresql-embedded-example.db.password}")
	private String password;

	@Bean
	public static PropertySourcesPlaceholderConfigurer properties(ConfigurableEnvironment environment) {
		Config config = ConfigFactory.load().resolve();
		TypesafePropertySource propertySource = new TypesafePropertySource("typesafeSource", config);

		MutablePropertySources propertySources = environment.getPropertySources();
		propertySources.addLast(propertySource);

		PropertySourcesPlaceholderConfigurer ret = new PropertySourcesPlaceholderConfigurer();
		ret.setPropertySources(propertySources);
		return ret;
	}

	@Bean(destroyMethod = "close")
	public HikariDataSource dataSource() {
		HikariConfig configuration = new HikariConfig();
		configuration.setDriverClassName(driver);
		configuration.setJdbcUrl(url);
		configuration.setUsername(username);
		configuration.setPassword(password);
		return new HikariDataSource(configuration);
	}

	@Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean
	public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) {
		SqlSessionFactoryBean ret = new SqlSessionFactoryBean();
		ret.setDataSource(dataSource);
		return ret;
	}
}

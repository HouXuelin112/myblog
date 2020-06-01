package it.hxl.myblogprod.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

//@Configuration
//@PropertySource("classpath:db.properties")
public class JdbcConfig {

    @Bean
    @ConfigurationProperties(prefix = "jdbc")
    public DataSource getDataSource(){
        BasicDataSource dataSource = new BasicDataSource();

        return dataSource;
    }


    public JdbcTemplate getJdbcTemplate(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
        return jdbcTemplate;
    }

}

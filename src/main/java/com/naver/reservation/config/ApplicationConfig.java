package com.naver.reservation.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"com.naver.reservation.dao", "com.naver.reservation.service"})
@Import({DBConfig.class})
public class ApplicationConfig {

}

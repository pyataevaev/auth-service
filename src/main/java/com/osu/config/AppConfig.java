package com.osu.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * Created by Ekaterina Pyataeva
 */
@Configuration
@EnableSwagger2
@EnableAutoConfiguration(exclude = LiquibaseAutoConfiguration.class)
public class AppConfig {

}

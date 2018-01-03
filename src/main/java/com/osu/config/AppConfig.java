package com.osu.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.context.annotation.Configuration;


/**
 * Created by Ekaterina Pyataeva
 */
@Configuration
@EnableAutoConfiguration(exclude = LiquibaseAutoConfiguration.class)
public class AppConfig {

}

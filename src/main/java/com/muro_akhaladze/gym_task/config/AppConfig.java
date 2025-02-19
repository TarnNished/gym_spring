package com.muro_akhaladze.gym_task.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "com.muro_akhaladze.gym_task")
@PropertySource("classpath:application.properties")
public class AppConfig {
}

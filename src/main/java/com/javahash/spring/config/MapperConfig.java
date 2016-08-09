package com.javahash.spring.config;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan({ "com.javahash.spring" })
@PropertySource({ "classpath:/db.properties", "classpath:/host.properties" })
public class MapperConfig {
	@Bean(name = "MapperScannerConfigurer")
	public MapperScannerConfigurer MapperScannerConfigurer() {
		MapperScannerConfigurer MapperScannerConfigurer = new MapperScannerConfigurer();
		MapperScannerConfigurer.setBasePackage("com.javahash.spring.mapper");
		return MapperScannerConfigurer;
	}
}

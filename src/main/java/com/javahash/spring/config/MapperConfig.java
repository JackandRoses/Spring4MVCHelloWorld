package com.javahash.spring.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({ "com.javahash.spring" })
@MapperScan("com.javahash.spring.mapper")
// 扫描mapper.java,也可用下面注释掉的方法MapperScannerConfigurer
public class MapperConfig {
	// @Bean(name = "MapperScannerConfigurer")
	// public MapperScannerConfigurer MapperScannerConfigurer() {
	// MapperScannerConfigurer MapperScannerConfigurer = new
	// MapperScannerConfigurer();
	// MapperScannerConfigurer.setBasePackage("com.javahash.spring.mapper");
	// return MapperScannerConfigurer;
	// }
}

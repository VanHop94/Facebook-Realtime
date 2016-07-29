package com.datasection.web.mvc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = { SecurityConfig.class, BoneCPConfig.class, ProjectConfig.class })
public class MvcConfiguration{
	

}

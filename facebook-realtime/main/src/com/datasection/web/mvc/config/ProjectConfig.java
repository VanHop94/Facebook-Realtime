package com.datasection.web.mvc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:/config.properties")
public class ProjectConfig {

	@Value("${real.maxThread}")
	private Integer maxThread;
	@Value("${real.maxFollowingTime}")
	private Long maxFollowingTime;
	@Value("${real.maxUserIsViewed}")
	private Integer maxUserIsViewed;
	@Value("${real.maxCommentIsViewed}")
	private Integer maxCommentIsViewed;
	@Value("${real.timeToRepeat}")
	private Integer timeToRepeat;
	@Value("${real.timeCallServer}")
	private Integer timeCallServer;

	@Bean
	public ArgsConstant argsConstant() {
		return new ArgsConstant(maxThread, maxFollowingTime, maxUserIsViewed, maxCommentIsViewed, timeToRepeat,
				timeCallServer);
	}
}

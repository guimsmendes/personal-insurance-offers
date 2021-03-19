package com.mendes.insuranceoffers.common.cache;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


@Configuration
@EnableCaching
@EnableScheduling
public class CachingConfig {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CachingConfig.class);
	
	public static final String COVERS = "COVERS";
	
	@Bean
	public CacheManager cacheManager() {
		return new ConcurrentMapCacheManager(COVERS);
	}
	
	@CacheEvict(allEntries = true, value = {COVERS})
	@Scheduled(fixedDelayString = "${mendes.cache.time-expire:86400000}", initialDelay = 500)
	public void limparCacheCoberturas() {
		LOGGER.info("Insurance covers text cache clean at {}", LocalDateTime.now());
	}

}

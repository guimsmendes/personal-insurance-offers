package com.mendes.insuranceoffers.dataprovider.http.configuration;

import feign.Feign;
import feign.Retryer;

import static java.util.concurrent.TimeUnit.SECONDS;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfiguration {
	
	
	@Value("1000")
	private long retriesInterval;
	@Value("3")
	private int maxRetries;
	
	@Bean
	@ConditionalOnMissingBean
	public Feign.Builder feignBuilder(Retryer retryer) {
		return Feign.builder().retryer(retryer);
	}
	
	@Bean
	@ConditionalOnMissingBean
	public Retryer feignRetryer() {
		return new Retryer.Default(retriesInterval, SECONDS.toMillis(1), maxRetries);
	}

}

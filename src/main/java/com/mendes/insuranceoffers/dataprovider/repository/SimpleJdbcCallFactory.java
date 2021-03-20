package com.mendes.insuranceoffers.dataprovider.repository;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

@Component
public class SimpleJdbcCallFactory {
	
	public SimpleJdbcCall createInstance(JdbcTemplate jdbcTemplate) {
		return new SimpleJdbcCall(jdbcTemplate);
	}
	
	public SimpleJdbcCall createInstance(DataSource dataSource) {
		return new SimpleJdbcCall(dataSource);
	}

}

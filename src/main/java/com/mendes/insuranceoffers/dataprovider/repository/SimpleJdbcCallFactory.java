package com.mendes.insuranceoffers.dataprovider.repository;

@Component
public class SimpleJdbcCallFactory {
	
	public SimpleJdbcCall createInstance(JdbcTemplate jdbcTemplate) {
		return new SimpleJdbcCall(jdbcTemplate);
	}
	
	public SimpleJdbcCall createInstance(DataSource dataSource) {
		return new SimpleJdbcCall(dataSource);
	}

}

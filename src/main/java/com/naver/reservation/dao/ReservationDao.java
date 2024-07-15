package com.naver.reservation.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.naver.reservation.dto.ReservationDto;
import static com.naver.reservation.dao.ReservationDaoSql.*;

@Repository
public class ReservationDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ReservationDto> rowMapper = BeanPropertyRowMapper.newInstance(ReservationDto.class);
	
	public ReservationDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public ReservationDto productInfo(Integer id){
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
		
		return jdbc.queryForObject(PRODUCT_DETAIL, params, rowMapper);
	}
	
	public List<ReservationDto> productPrice(Integer id){
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
		
		return jdbc.query(PRODUCT_PRICE, params, rowMapper);
	}
	
	
}

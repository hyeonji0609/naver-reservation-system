package com.naver.reservation.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.naver.reservation.dto.DetailDto;
import static com.naver.reservation.dao.DetailDaoSql.*;

@Repository
public class DetailDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<DetailDto> rowMapper = BeanPropertyRowMapper.newInstance(DetailDto.class);
	
	public DetailDao(DataSource dataSource) {
		// ? 대신 파라미터를 사용해서 작성하는 것을 적용
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	
	public DetailDto headerDetail(Integer id){
		Map<String, Integer> params = new HashMap<>();
		params.put("id", id);
		
		// query : 여러개의 레코드를 낼 때 적합
		return jdbc.queryForObject(HEADER_DETAIL, params, rowMapper);
	}
	
	public List<DetailDto> mainImg(Integer id){
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
		
		return jdbc.query(MAIN_IMG, params , rowMapper);
	}
	
	public List<DetailDto> commentAll(Integer id){
		Map<String, Integer> params = new HashMap<>();
		params.put("id", id);
		
		return jdbc.query(COMMENT_ALL, params, rowMapper);
	}
	
	// 오시는 길
	public List<DetailDto> directions (Integer id){
		Map<String, Integer> params = new HashMap<>();
		params.put("id", id);
		
		return jdbc.query(DIRECTIONS, params, rowMapper);
	}
	
}

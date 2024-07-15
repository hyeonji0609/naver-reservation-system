package com.naver.reservation.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.naver.reservation.dto.*;
import static com.naver.reservation.dao.ProductDaoSql.*;

@Repository
public class ProductDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ProductDto> rowMapper = BeanPropertyRowMapper.newInstance(ProductDto.class);

	public ProductDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);

	}

	public List<ProductDto> productAll(Integer start, Integer limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", limit);
		
		// query : 여러개의 레코드를 낼 때 적합
		return jdbc.query(PRODUCT_ALL, params, rowMapper);
	}

	public List<ProductDto> productInCategory(Integer category, Integer start, Integer limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("category", category);
		params.put("start", start);
		params.put("limit", limit);
		
		return jdbc.query(PRODUCT_IN_CATEGORY, params, rowMapper);
	}

	public int countAll() {
		// queryForObject : 단일 행 결과를 반환
		return jdbc.queryForObject(COUNT_ALL, Collections.emptyMap(), Integer.class);
	}

	public int countWithinCategory(Integer category) {
		Map<String, Integer> params = new HashMap<>();
		params.put("category", category);

		return jdbc.queryForObject(COUNT_WITHIN_CATEGORY, params, Integer.class);
	}

	public List<ProductDto> promotion() {
		return jdbc.query(PROMOTION, rowMapper);
	}
}

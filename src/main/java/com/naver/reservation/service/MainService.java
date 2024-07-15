package com.naver.reservation.service;

import java.util.List;

import com.naver.reservation.dto.ProductDto;

public interface MainService {
	// Main
	public static final Integer LIMIT = 4;
	
	public List<ProductDto> getProducts(Integer start);
	public List<ProductDto> getProductsInCategory(Integer start, Integer category);
	public int getCountAll();
	public int getCountWithinCategory(Integer category);
	public List<ProductDto> getPromotion();
	
}

package com.naver.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naver.reservation.dao.ProductDao;
import com.naver.reservation.dto.ProductDto;
import com.naver.reservation.service.MainService;

@Service
public class MainServiceImpl implements MainService {
	@Autowired
	ProductDao productDao;
	
	@Override
	public List<ProductDto> getProducts(Integer start) {
		List<ProductDto> list = productDao.productAll(start, MainService.LIMIT);
		return list;
	}

	@Override
	public List<ProductDto> getProductsInCategory(Integer start, Integer category) {
		List<ProductDto> list = productDao.productInCategory(category, start, MainService.LIMIT);
		return list;
	}

	@Override
	public int getCountAll() {
		return productDao.countAll();
	}

	@Override
	public int getCountWithinCategory(Integer category) {
		return productDao.countWithinCategory(category);
	}

	@Override
	public List<ProductDto> getPromotion() {
		List<ProductDto> list = productDao.promotion();
		return list;
	}

}

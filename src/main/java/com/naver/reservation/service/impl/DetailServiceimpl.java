package com.naver.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naver.reservation.dao.DetailDao;
import com.naver.reservation.dto.DetailDto;
import com.naver.reservation.service.DetailService;

@Service
public class DetailServiceimpl implements DetailService {
	@Autowired
	DetailDao detailDao;
	
	@Override
	public DetailDto getHeaderDetail(Integer id) {
		DetailDto list = detailDao.headerDetail(id);
		return list;
	}
	
	@Override
	public List<DetailDto> getMainImg(Integer id) {
		List<DetailDto> list = detailDao.mainImg(id);
		return list;
	}

	@Override
	public List<DetailDto> getCommentAll(Integer id) {
		List<DetailDto> list = detailDao.commentAll(id);
		return list;

	}

	@Override
	public List<DetailDto> getDirections(Integer id) {
		List<DetailDto> list = detailDao.directions(id);
		return list;
	}

}

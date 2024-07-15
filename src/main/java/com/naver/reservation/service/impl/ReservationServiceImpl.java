package com.naver.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naver.reservation.dao.ReservationDao;
import com.naver.reservation.dto.ReservationDto;
import com.naver.reservation.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {
	@Autowired
	ReservationDao reservationDao;
	
	@Override
	public ReservationDto getProductInfo(Integer id) {
		ReservationDto list = reservationDao.productInfo(id);
		return list;
	}

	@Override
	public List<ReservationDto> getProductPrice(Integer id) {
		List<ReservationDto> list = reservationDao.productPrice(id);
		return list;
	}

}

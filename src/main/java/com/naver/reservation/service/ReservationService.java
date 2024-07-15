package com.naver.reservation.service;

import java.util.List;

import com.naver.reservation.dao.ReservationDao;
import com.naver.reservation.dto.ReservationDto;

public interface ReservationService {
	public ReservationDto getProductInfo(Integer id);
	public List<ReservationDto> getProductPrice(Integer id);
}

package com.naver.reservation.dto;

import java.math.BigDecimal;
import java.util.Date;

public class ReservationDto {
	// product
	private int id;
	private String description;
	private int category_id;
	
	// file_info
	private String save_file_name;
	
	// product_price
	private int price;
	private String price_type_name;
	private BigDecimal discount_rate;
	
	// reservation_info_price
	private int count;
	
	// display_info
	private String opening_hours;
	private String place_name;
	
	// reservation_info
	private String reservation_name;
	private String reservation_tel;
	private String reservation_email;
	private Date reservation_date;
	private int reservation_price;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public String getSave_file_name() {
		return save_file_name;
	}
	public void setSave_file_name(String save_file_info) {
		this.save_file_name = save_file_info;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getPrice_type_name() {
		return price_type_name;
	}
	public void setPrice_type_name(String price_type_name) {
		this.price_type_name = price_type_name;
	}
	public BigDecimal getDiscount_rate() {
		return discount_rate;
	}
	public void setDiscount_rate(BigDecimal discount_rate) {
		this.discount_rate = discount_rate;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getOpening_hours() {
		return opening_hours;
	}
	public void setOpening_hours(String opening_hours) {
		this.opening_hours = opening_hours;
	}
	public String getPlace_name() {
		return place_name;
	}
	public void setPlace_name(String place_name) {
		this.place_name = place_name;
	}
	public String getReservation_name() {
		return reservation_name;
	}
	public void setReservation_name(String reservation_name) {
		this.reservation_name = reservation_name;
	}
	public String getReservation_tel() {
		return reservation_tel;
	}
	public void setReservation_tel(String reservation_tel) {
		this.reservation_tel = reservation_tel;
	}
	public String getReservation_email() {
		return reservation_email;
	}
	public void setReservation_email(String reservation_email) {
		this.reservation_email = reservation_email;
	}
	public Date getReservation_date() {
		return reservation_date;
	}
	public void setReservation_date(Date reservation_date) {
		this.reservation_date = reservation_date;
	}
	public int getReservation_price() {
		return reservation_price;
	}
	public void setReservation_price(int reservation_price) {
		this.reservation_price = reservation_price;
	}
}

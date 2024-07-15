package com.naver.reservation.dao;

public class ReservationDaoSql {
	// 상품 상세 정보 조회
	public static final String PRODUCT_DETAIL = "SELECT P.id, P.category_id, PP.price, P.description, DI.opening_hours, DI.place_name, FI.save_file_name"
			+ " FROM product P"
			+ " JOIN product_price PP ON (P.id = PP.product_id)"
			+ " JOIN display_info DI ON (P.id = DI.product_id)"
			+ " JOIN product_image PI ON (P.id = PI.product_id)"
			+ "	JOIN file_info FI ON (PI.file_id = FI.id)"
			+ " WHERE PI.type = 'ma'"
			+ " AND P.id = :id"
			+ "	ORDER BY PP.price ASC"
			+ "	LIMIT 1";

	public static final String PRODUCT_PRICE = "SELECT P.id, P.category_id, PP.price, PP.price_type_name, PP.discount_rate"
			+ " FROM product P"
			+ " JOIN product_price PP ON (P.id = PP.product_id)"
			+ "	WHERE P.id = :id";
}

package com.joh.lhms.service;

import com.joh.lhms.model.DiscountType;

public interface DiscountTypeService {

	DiscountType save(DiscountType discountType);

	Iterable<DiscountType> findAll();

	DiscountType findOne(int id);

	DiscountType update(DiscountType discountType);

	void delete(int id);

}

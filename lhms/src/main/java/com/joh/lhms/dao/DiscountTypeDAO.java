package com.joh.lhms.dao;

import org.springframework.data.repository.CrudRepository;

import com.joh.lhms.model.DiscountType;

public interface DiscountTypeDAO extends CrudRepository<DiscountType, Integer> {
}

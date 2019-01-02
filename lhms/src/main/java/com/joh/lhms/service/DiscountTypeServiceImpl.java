package com.joh.lhms.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joh.lhms.dao.DiscountTypeDAO;
import com.joh.lhms.model.DiscountType;

@Service
public class DiscountTypeServiceImpl implements DiscountTypeService {

	@Autowired
	private DiscountTypeDAO discountTypeDAO;

	@Override
	public DiscountType save(DiscountType discountType) {
		return discountTypeDAO.save(discountType);
	}

	@Override
	public Iterable<DiscountType> findAll() {
		return discountTypeDAO.findAll();
	}

	@Override
	public DiscountType findOne(int id) {
		return discountTypeDAO.findOne(id);
	}

	@Override
	public DiscountType update(DiscountType discountType) {
		if (discountTypeDAO.findOne(discountType.getId()) == null) {
			throw new EntityNotFoundException();
		}
		return discountTypeDAO.save(discountType);
	}

	@Override
	public void delete(int id) {
		discountTypeDAO.delete(id);

	}

}

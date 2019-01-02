package com.joh.lhms.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joh.lhms.dao.ExaminationGroupDAO;
import com.joh.lhms.model.ExaminationGroup;

@Service
public class ExaminationGroupServiceImpl implements ExaminationGroupService {

	@Autowired
	private ExaminationGroupDAO examinationGroupDAO;

	@Override
	public ExaminationGroup save(ExaminationGroup examinationGroup) {
		return examinationGroupDAO.save(examinationGroup);
	}

	@Override
	public Iterable<ExaminationGroup> findAll() {
		return examinationGroupDAO.findAll();
	}

	@Override
	public ExaminationGroup findOne(int id) {
		return examinationGroupDAO.findOne(id);
	}

	@Override
	public ExaminationGroup update(ExaminationGroup examinationGroup) {
		if (examinationGroupDAO.findOne(examinationGroup.getId()) == null) {
			throw new EntityNotFoundException();
		}
		return examinationGroupDAO.save(examinationGroup);
	}

	@Override
	public void delete(int id) {
		examinationGroupDAO.delete(id);
	}

}

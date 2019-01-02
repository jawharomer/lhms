package com.joh.lhms.service;

import com.joh.lhms.model.Examination;

public interface ExaminationService {

	Iterable<Examination> findAll();

	Examination save(Examination examination);

	Examination findOne(int id);

	Examination update(Examination examination);

	void delete(int id);

}

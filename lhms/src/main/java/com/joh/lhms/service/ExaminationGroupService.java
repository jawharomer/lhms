package com.joh.lhms.service;

import com.joh.lhms.model.ExaminationGroup;

public interface ExaminationGroupService {

	ExaminationGroup save(ExaminationGroup examinationGroup);

	Iterable<ExaminationGroup> findAll();

	ExaminationGroup update(ExaminationGroup examinationGroup);

	ExaminationGroup findOne(int id);

	void delete(int id);

}

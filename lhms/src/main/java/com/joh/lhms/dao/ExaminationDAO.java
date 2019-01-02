package com.joh.lhms.dao;

import org.springframework.data.repository.CrudRepository;

import com.joh.lhms.model.Examination;

public interface ExaminationDAO extends CrudRepository<Examination, Integer> {
}

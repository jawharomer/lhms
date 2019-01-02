package com.joh.lhms.dao;

import java.util.Date;

import org.springframework.data.repository.CrudRepository;

import com.joh.lhms.model.Patient;

public interface PatientDAO extends CrudRepository<Patient, Integer> {
	Iterable<Patient> findAllByTimeBetweenOrderByTimeDesc(Date from, Date to);
}

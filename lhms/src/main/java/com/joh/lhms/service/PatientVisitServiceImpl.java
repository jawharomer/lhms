package com.joh.lhms.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joh.lhms.dao.ExaminationDAO;
import com.joh.lhms.dao.PatientVisitDAO;
import com.joh.lhms.model.Examination;
import com.joh.lhms.model.PatientVisit;

@Service
public class PatientVisitServiceImpl implements PatientVisitService {

	@Autowired
	private PatientVisitDAO patientVisitDAO;

	@Autowired
	private ExaminationDAO examinationDAO;

	@Override
	public PatientVisit save(PatientVisit patientVisit) {

		patientVisit.getPatientExaminations().forEach(e -> {
			Examination examination = e.getExamination();
			examination = examinationDAO.findOne(examination.getId());
			e.setPrice(examination.getPrice());
		});

		return patientVisitDAO.save(patientVisit);

	}

	@Override
	public List<PatientVisit> findAllByTimeBetween(Date from, Date to) {
		return patientVisitDAO.findAllByTimeBetween(from, to);

	}

	@Override
	public PatientVisit findOne(int id) {
		return patientVisitDAO.findOne(id);

	}

	@Override
	public void delete(int id) {
		patientVisitDAO.delete(id);
	}

}

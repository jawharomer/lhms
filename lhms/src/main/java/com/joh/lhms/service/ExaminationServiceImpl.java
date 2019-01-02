package com.joh.lhms.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joh.lhms.dao.ExaminationDAO;
import com.joh.lhms.domain.model.ExaminationType;
import com.joh.lhms.exception.CusDataIntegrityViolationException;
import com.joh.lhms.model.Examination;

@Service
public class ExaminationServiceImpl implements ExaminationService {

	@Autowired
	private ExaminationDAO examinationDAO;

	@Override
	public Iterable<Examination> findAll() {
		return examinationDAO.findAll();
	}

	@Override
	@Transactional
	public Examination save(Examination examination) {

		if (examination.getExaminationType() == ExaminationType.Paragraph) {
			if (examination.getParagraph() == null || examination.getParagraph().isEmpty())
				throw new CusDataIntegrityViolationException("Paragraph is required");
			examination.setNormal(null);
			examination.setUnit(null);
			examination.setUpper(null);
			examination.setLower(null);
		} else if (examination.getExaminationType() == ExaminationType.Normal) {

			if (examination.getNormal() == null || examination.getNormal().isEmpty())
				throw new CusDataIntegrityViolationException("Normal is required");
			if (examination.getUnit() == null || examination.getUnit().isEmpty())
				throw new CusDataIntegrityViolationException("Unit is required");
			if (examination.getUpper() == null || examination.getUpper().isEmpty())
				throw new CusDataIntegrityViolationException("Upper is required");
			if (examination.getLower() == null || examination.getLower().isEmpty())
				throw new CusDataIntegrityViolationException("Lower is required");

			examination.setParagraph(null);

		} else if (examination.getExaminationType() == ExaminationType.Logical) {

			examination.setNormal(null);
			examination.setUnit(null);
			examination.setUpper(null);
			examination.setLower(null);
			examination.setParagraph(null);

		}

		try {
			return examinationDAO.save(examination);
		} catch (DataIntegrityViolationException e) {
			throw new CusDataIntegrityViolationException("Code is already exist");
		}

	}

	@Override
	@Transactional
	public Examination update(Examination examination) {

		if (examinationDAO.findOne(examination.getId()) == null)
			throw new EntityNotFoundException("Examination not found id=" + examination.getId());

		if (examination.getExaminationType() == ExaminationType.Paragraph) {
			if (examination.getParagraph() == null || examination.getParagraph().isEmpty())
				throw new CusDataIntegrityViolationException("Paragraph is required");
			examination.setNormal(null);
			examination.setUnit(null);
			examination.setUpper(null);
			examination.setLower(null);
		} else if (examination.getExaminationType() == ExaminationType.Normal) {

			if (examination.getNormal() == null || examination.getNormal().isEmpty())
				throw new CusDataIntegrityViolationException("Normal is required");
			if (examination.getUnit() == null || examination.getUnit().isEmpty())
				throw new CusDataIntegrityViolationException("Unit is required");
			if (examination.getUpper() == null || examination.getUpper().isEmpty())
				throw new CusDataIntegrityViolationException("Upper is required");
			if (examination.getLower() == null || examination.getLower().isEmpty())
				throw new CusDataIntegrityViolationException("Lower is required");

			examination.setParagraph(null);

		} else if (examination.getExaminationType() == ExaminationType.Logical) {

			examination.setNormal(null);
			examination.setUnit(null);
			examination.setUpper(null);
			examination.setLower(null);
			examination.setParagraph(null);

		}

		try {
			return examinationDAO.save(examination);
		} catch (DataIntegrityViolationException e) {
			throw new CusDataIntegrityViolationException("Code is already exist");
		}

	}

	@Override
	public Examination findOne(int id) {
		return examinationDAO.findOne(id);
	}

	@Override
	public void delete(int id) {
		 examinationDAO.delete(id);
	}

}

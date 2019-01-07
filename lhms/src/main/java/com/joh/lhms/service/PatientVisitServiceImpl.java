package com.joh.lhms.service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joh.lhms.dao.DiscountTypeDAO;
import com.joh.lhms.dao.ExaminationDAO;
import com.joh.lhms.dao.PatientVisitDAO;
import com.joh.lhms.exception.CusDataIntegrityViolationException;
import com.joh.lhms.model.DiscountType;
import com.joh.lhms.model.Examination;
import com.joh.lhms.model.PatientVisit;

@Service
public class PatientVisitServiceImpl implements PatientVisitService {

	@Autowired
	private PatientVisitDAO patientVisitDAO;

	@Autowired
	private ExaminationDAO examinationDAO;

	@Autowired
	private DiscountTypeDAO discountTypeDAO;

	@Override
	public PatientVisit save(PatientVisit patientVisit) {

		patientVisit.getPatientExaminations().forEach(e -> {
			Examination examination = e.getExamination();
			examination = examinationDAO.findOne(examination.getId());
			e.setPrice(examination.getPrice());
		});

		// Prevent Payment by regular user
		patientVisit.setDiscountAmount(null);
		patientVisit.setTotalPayment(null);
		patientVisit.setDiscountAmount(null);

		return patientVisitDAO.save(patientVisit);

	}

	@Override
	public PatientVisit update(PatientVisit patientVisit) {

		if (patientVisitDAO.findOne(patientVisit.getId()) == null)
			throw new EntityNotFoundException("PatientVisit not found with id=" + patientVisit.getId());

		PatientVisit orginal = patientVisitDAO.findOne(patientVisit.getId());

		patientVisit.getPatientExaminations().forEach(e -> {
			Examination examination = e.getExamination();
			examination = examinationDAO.findOne(examination.getId());
			e.setPrice(examination.getPrice());
		});

		// Prevent Update Payment by regular user
		patientVisit.setDiscountAmount(orginal.getDiscountAmount());
		patientVisit.setTotalPayment(orginal.getTotalPayment());
		patientVisit.setDiscountAmount(orginal.getDiscountAmount());

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

	@Override
	@Transactional
	public PatientVisit payment(PatientVisit patientVisit) {

		PatientVisit orginal = patientVisitDAO.findOne(patientVisit.getId());

		if (patientVisit.getDiscountType() != null)
			orginal.setDiscountType(patientVisit.getDiscountType());
		else {
			orginal.setDiscountType(null);
		}

		if (patientVisit.getDiscountType() != null) {
			DiscountType discountType = discountTypeDAO.findOne(patientVisit.getDiscountType().getId());
			if (discountType.isFix()) {
				orginal.setDiscountAmount(discountType.getLimit());
			} else {
				if (patientVisit.getDiscountAmount() != null)
					orginal.setDiscountAmount(patientVisit.getDiscountAmount());
				else
					throw new CusDataIntegrityViolationException(
							"You are tring discount but discount amount is not set");
			}
		} else {
			orginal.setDiscountAmount(null);
		}

		double totalPrice = orginal.getPatientExaminations().stream().mapToDouble(i -> i.getPrice()).sum();

		if (orginal.getDiscountAmount() != null) {
			orginal.setTotalPayment(totalPrice - totalPrice * orginal.getDiscountAmount().doubleValue());
		} else {
			orginal.setTotalPayment(totalPrice);
		}

		System.err.println(orginal);

		return patientVisitDAO.save(orginal);
	}

}

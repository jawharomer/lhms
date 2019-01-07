package com.joh.lhms.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joh.lhms.domain.model.JsonResponse;
import com.joh.lhms.exception.CusDataIntegrityViolationException;
import com.joh.lhms.model.DiscountType;
import com.joh.lhms.model.Examination;
import com.joh.lhms.model.Patient;
import com.joh.lhms.model.PatientVisit;
import com.joh.lhms.service.DiscountTypeService;
import com.joh.lhms.service.ExaminationService;
import com.joh.lhms.service.PatientService;
import com.joh.lhms.service.PatientVisitService;
import com.joh.lhms.validator.PatientVisitValidation;

@Controller()
@RequestMapping(path = "/patientVisits")
public class PatientVisitController {

	private static final Logger logger = Logger.getLogger(PatientVisitController.class);

	@Autowired
	private PatientService patientService;

	@Autowired
	private PatientVisitService patientVisitService;

	@Autowired
	private ExaminationService examinationService;

	@Autowired
	private DiscountTypeService discountTypeService;

	@GetMapping()
	public String getAllPatientVisit(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date to, Model model) {
		logger.info("getAllDoctorPatientVisits->fired");

		logger.info("from=" + from);
		logger.info("to=" + to);

		List<PatientVisit> patientVisits = patientVisitService.findAllByTimeBetween(from, to);

		logger.info("patientVisits=" + patientVisits);

		model.addAttribute("patientVisits", patientVisits);
		model.addAttribute("from", from);
		model.addAttribute("to", to);

		return "patientVisits";
	}

	@GetMapping(path = "/add/{patientId}")
	public String getAddingPatientVisit(@PathVariable int patientId, Model model) throws JsonProcessingException {
		logger.info("getAddingPatientVisit->fired");
		logger.info("patientId=" + patientId);

		ObjectMapper mapper = new ObjectMapper();

		Patient patient = patientService.findOne(patientId);

		PatientVisit patientVisit = new PatientVisit();
		patientVisit.setPatient(patient);

		Iterable<Examination> examinations = examinationService.findAll();

		model.addAttribute("jsonExaminations", mapper.writeValueAsString(examinations));

		model.addAttribute("jsonPatientVisit", mapper.writeValueAsString(patientVisit));

		return "addPatientVisit";
	}

	@PostMapping(path = "/add")
	@ResponseBody
	public JsonResponse addPatientVisit(
			@RequestBody @Validated(PatientVisitValidation.Insert.class) PatientVisit patientVisit,
			BindingResult result, Model model) {
		logger.info("addPatientVisit->fired");
		logger.info("patientVisit=" + patientVisit);

		if (result.hasErrors()) {
			throw new CusDataIntegrityViolationException("bad input is entered");
		} else {
			patientVisit = patientVisitService.save(patientVisit);

			JsonResponse jsonResponse = new JsonResponse();
			jsonResponse.setStatus(200);
			jsonResponse.setMessage("success");
			jsonResponse.setEtc("" + patientVisit.getId());

			return jsonResponse;
		}

	}

	@GetMapping(path = "/edit/{id}")
	public String getEditingPatientVisit(@PathVariable int id, Model model) throws JsonProcessingException {
		logger.info("getEditingPatientVisit->fired");
		logger.info("id=" + id);

		ObjectMapper mapper = new ObjectMapper();

		PatientVisit patientVisit = patientVisitService.findOne(id);

		Iterable<Examination> examinations = examinationService.findAll();

		model.addAttribute("jsonExaminations", mapper.writeValueAsString(examinations));

		model.addAttribute("jsonPatientVisit", mapper.writeValueAsString(patientVisit));

		return "editPatientVisit";
	}

	@PostMapping(path = "/update")
	public String updatePatientVisit(@RequestBody @Valid PatientVisit patientVisit, BindingResult result, Model model,
			HttpServletResponse response) throws JsonProcessingException {
		logger.info("updatePatientVisit->fired");
		logger.info("patientVisit=" + patientVisit);

		logger.info("errors" + result.getAllErrors());

		if (result.hasErrors()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

			ObjectMapper mapper = new ObjectMapper();

			model.addAttribute("jsonPatientVisit", mapper.writeValueAsString(patientVisit));

			return "editPatientVisit";

		} else {
			patientVisitService.update(patientVisit);
			return "success";
		}

	}

	@GetMapping(path = "/payment/{id}")
	public String getAddingVisitPayment(@PathVariable int id, Model model) throws JsonProcessingException {
		logger.info("getAddingVisitPayment->fired");
		logger.info("id=" + id);

		ObjectMapper mapper = new ObjectMapper();

		PatientVisit patientVisit = patientVisitService.findOne(id);

		Iterable<DiscountType> discountTypes = discountTypeService.findAll();

		model.addAttribute("jsonPatientVisit", mapper.writeValueAsString(patientVisit));

		model.addAttribute("jsonDiscountTypes", mapper.writeValueAsString(discountTypes));

		return "addVisitPayment";

	}

	@GetMapping(path = "/payment/{id}/print")
	public String getPrintVisitPayment(@PathVariable int id, Model model) {
		logger.info("getPrintVisitPayment->fired");
		logger.info("id=" + id);

		PatientVisit patientVisit = patientVisitService.findOne(id);

		model.addAttribute("patientVisit", patientVisit);

		return "printVisitPayment";

	}

	@PostMapping(path = "/payment")
	public String addPaymentPatientVisit(@RequestBody @Valid PatientVisit patientVisit, BindingResult result,
			Model model, HttpServletResponse response) throws JsonProcessingException {
		logger.info("addPaymentPatientVisit->fired");
		logger.info("patientVisit=" + patientVisit);
		patientVisitService.payment(patientVisit);
		return "success";

	}

	@PostMapping(path = "/delete/{id}")
	public String deletePatientVisit(@PathVariable int id) {
		logger.info("deletePatientVisit->fired");
		logger.info("id=" + id);

		patientVisitService.delete(id);

		return "success";

	}

}

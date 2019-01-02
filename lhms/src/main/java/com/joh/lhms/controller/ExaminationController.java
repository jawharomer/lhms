package com.joh.lhms.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.joh.lhms.domain.model.ExaminationType;
import com.joh.lhms.domain.model.JsonResponse;
import com.joh.lhms.model.Examination;
import com.joh.lhms.model.ExaminationGroup;
import com.joh.lhms.service.ExaminationGroupService;
import com.joh.lhms.service.ExaminationService;

@Controller()
@RequestMapping(path = "/examinations")
public class ExaminationController {

	private static final Logger logger = Logger.getLogger(ExaminationController.class);

	@Autowired
	private ExaminationService examinationService;

	@Autowired
	private ExaminationGroupService examinationGroupService;

	@GetMapping()
	public String getAllExamination(Model model) {

		logger.info("getAllExaminationGroups->fired");

		Iterable<Examination> examinations = examinationService.findAll();

		logger.info("examinations=" + examinations);

		model.addAttribute("examinations", examinations);

		return "examinations";
	}

	@GetMapping(path = "/add")
	public String getAddingExamination(Model model) throws JsonProcessingException {
		logger.info("getAddingExamination->fired");
		ObjectMapper mapper = new ObjectMapper();

		Examination examination = new Examination();

		Iterable<ExaminationGroup> examinationGroups = examinationGroupService.findAll();

		model.addAttribute("jsonExamination", mapper.writeValueAsString(examination));

		model.addAttribute("jsonExaminationGroups", mapper.writeValueAsString(examinationGroups));
		ExaminationType[] examinationTypes = ExaminationType.values();
		model.addAttribute("jsonExaminationTypes", mapper.writeValueAsString(examinationTypes));

		return "addExamination";
	}

	@PostMapping(path = "/add")
	@ResponseBody
	public JsonResponse addExamination(@RequestBody Examination examination, Model model)
			throws JsonProcessingException {
		logger.info("addExamination->fired");
		logger.info("examination=" + examination);
		Examination savedExamination = examinationService.save(examination);

		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setStatus(200);
		jsonResponse.setMessage("success");
		jsonResponse.setEtc("" + savedExamination.getId());

		return jsonResponse;
	}

	@GetMapping(path = "/edit/{id}")
	public String getEditingExamination(@PathVariable int id, Model model) throws JsonProcessingException {
		logger.info("getEditingExamination->fired");
		ObjectMapper mapper = new ObjectMapper();

		Examination examination = examinationService.findOne(id);

		logger.info("examination=" + examination);

		Iterable<ExaminationGroup> examinationGroups = examinationGroupService.findAll();

		model.addAttribute("jsonExamination", mapper.writeValueAsString(examination));

		model.addAttribute("jsonExaminationGroups", mapper.writeValueAsString(examinationGroups));
		ExaminationType[] examinationTypes = ExaminationType.values();
		model.addAttribute("jsonExaminationTypes", mapper.writeValueAsString(examinationTypes));

		return "editExamination";
	}

	@PostMapping(path = "/update")
	@ResponseBody
	public JsonResponse update(@RequestBody Examination examination, Model model) throws JsonProcessingException {
		logger.info("addExamination->fired");
		logger.info("examination=" + examination);
		Examination savedExamination = examinationService.update(examination);

		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setStatus(200);
		jsonResponse.setMessage("success");
		jsonResponse.setEtc("" + savedExamination.getId());

		return jsonResponse;
	}

	@PostMapping(path = "/delete/{id}")
	public String deleteExamination(@PathVariable int id) {
		logger.info("deleteExamination->fired");
		logger.info("id=" + id);

		examinationService.delete(id);

		return "success";
	}

}

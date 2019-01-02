package com.joh.lhms.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.joh.lhms.model.DiscountType;
import com.joh.lhms.model.Examination;
import com.joh.lhms.model.ExaminationGroup;
import com.joh.lhms.service.DiscountTypeService;
import com.joh.lhms.service.ExaminationGroupService;

@Controller()
@RequestMapping(path = "/examinationGroups")
public class ExaminationGroupController {

	private static final Logger logger = Logger.getLogger(ExaminationGroupController.class);

	@Autowired
	private ExaminationGroupService examinationGroupService;

	@GetMapping()
	public String getAllExaminationGroups(Model model) {

		logger.info("getAllExaminationGroups->fired");

		Iterable<ExaminationGroup> examinationGroups = examinationGroupService.findAll();

		logger.info("examinationGroups=" + examinationGroups);

		model.addAttribute("examinationGroups", examinationGroups);

		return "examinationGroups";
	}

	@GetMapping(path = "/add")
	public String getAddingExaminationGroup(Model model) {
		logger.info("getAddingExaminationGroup->fired");

		ExaminationGroup examinationGroup = new ExaminationGroup();

		model.addAttribute("examinationGroup", examinationGroup);

		return "examinationGroup/addExaminationGroup";
	}

	@PostMapping(path = "/add")
	public String addExaminationGroup(@RequestBody @Valid ExaminationGroup examinationGroup, BindingResult result,
			Model model) {
		logger.info("addExaminationGroup->fired");

		logger.info("examinationGroup=" + examinationGroup);

		logger.info("errors=" + result.getAllErrors());

		if (result.hasErrors()) {
			model.addAttribute("examinationGroup", examinationGroup);
			return "examinationGroup/addExaminationGroup";
		} else {
			examinationGroupService.save(examinationGroup);
			return "success";
		}

	}

	@GetMapping(path = "/edit/{id}")
	public String getEditingExaminationGroup(@PathVariable int id, Model model) {
		logger.info("getEditingExaminationGroup->fired");

		logger.info("id=" + id);

		ExaminationGroup examinationGroup = examinationGroupService.findOne(id);

		model.addAttribute("examinationGroup", examinationGroup);

		return "examinationGroup/editExaminationGroup";
	}

	@PostMapping(path = "/update")
	public String updateExaminationGroup(@RequestBody @Valid ExaminationGroup examinationGroup, BindingResult result,
			Model model) {
		logger.info("updateDiscountType->fired");

		logger.info("updateExaminationGroup=" + examinationGroup);

		logger.info("errors=" + result.getAllErrors());

		if (result.hasErrors()) {
			model.addAttribute("examinationGroup", examinationGroup);
			return "discountType/editExaminationGroup";
		} else {
			examinationGroupService.update(examinationGroup);
			return "success";
		}

	}

	@PostMapping(path = "/delete/{id}")
	public String deleteExaminationGroup(@PathVariable int id, Model model) {
		logger.info("deleteExaminationGroup->fired");
		examinationGroupService.delete(id);
		return "success";
	}
}

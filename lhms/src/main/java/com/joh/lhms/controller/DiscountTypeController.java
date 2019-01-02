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
import com.joh.lhms.service.DiscountTypeService;

@Controller()
@RequestMapping(path = "/discountTypes")
public class DiscountTypeController {

	private static final Logger logger = Logger.getLogger(DiscountTypeController.class);

	@Autowired
	private DiscountTypeService discountTypeService;

	@GetMapping()
	public String getAllDiscountTypes(Model model) {

		logger.info("getAllDiscountTypes->fired");

		Iterable<DiscountType> discountTypes = discountTypeService.findAll();

		logger.info("discountTypes=" + discountTypes);

		model.addAttribute("discountTypes", discountTypes);

		return "discountTypes";
	}

	@GetMapping(path = "/add")
	public String getAddingDiscountType(Model model) {
		logger.info("getAddingDiscountType->fired");

		DiscountType discountType = new DiscountType();

		model.addAttribute("discountType", discountType);

		return "discountType/addDiscountType";
	}

	@PostMapping(path = "/add")
	public String addDiscountType(@RequestBody @Valid DiscountType discountType, BindingResult result, Model model) {
		logger.info("addDiscountType->fired");

		logger.info("discountType=" + discountType);

		logger.info("errors=" + result.getAllErrors());

		if (result.hasErrors()) {
			model.addAttribute("discountType", discountType);
			return "discountType/addDiscountType";
		} else {
			discountTypeService.save(discountType);
			return "success";
		}

	}

	@GetMapping(path = "/edit/{id}")
	public String getEditingDiscountType(@PathVariable int id, Model model) {
		logger.info("getEditingDiscountType->fired");

		logger.info("id=" + id);

		DiscountType discountType = discountTypeService.findOne(id);

		model.addAttribute("discountType", discountType);

		return "discountType/editDiscountType";
	}

	@PostMapping(path = "/update")
	public String updateDiscountType(@RequestBody @Valid DiscountType discountType, BindingResult result, Model model) {
		logger.info("updateDiscountType->fired");

		logger.info("discountType=" + discountType);

		logger.info("errors=" + result.getAllErrors());

		if (result.hasErrors()) {
			model.addAttribute("discountType", discountType);
			return "discountType/editDiscountType";
		} else {
			discountTypeService.update(discountType);
			return "success";
		}

	}

	@PostMapping(path = "/delete/{id}")
	public String deleteDiscountType(@PathVariable int id, Model model) {
		logger.info("deleteDiscountType->fired");
		discountTypeService.delete(id);
		return "success";
	}

}

package com.joh.lhms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.joh.lhms.domain.model.ExaminationType;

@Entity
@Table(name = "PATIENT_EXAMINATIONS")
public class PatientExamination {

	@Column(name = "I_PATIENT_EXAMINATION")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne()
	@JoinColumn(name = "I_EXAMINATION")
	private Examination examination;

	@NotNull(message = "Price is required")
	@Column(name = "PRICE")
	private Double price;

	@Column(name = "RESULT", length = 255)
	private String result;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Examination getExamination() {
		return examination;
	}

	public void setExamination(Examination examination) {
		this.examination = examination;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "PatientExamination [id=" + id + ", examination=" + examination + ", price=" + price + ", result="
				+ result + "]";
	}

}
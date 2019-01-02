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
@Table(name = "EXAMINATIONS")
public class Examination {

	@Column(name = "I_EXAMINATION")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@NotNull(message = "Examination type is required")
	@Column(name = "EXAMINATION_TYPE")
	private ExaminationType examinationType;

	@NotNull(message = "Examination Group type is required")
	@JoinColumn(name = "I_EXAMINATION_GROUP")
	@ManyToOne
	private ExaminationGroup examinationGroup;

	@NotBlank(message = "Code is required")
	@Column(name = "code", unique = true)
	private String code;

	@NotBlank(message = "Name is required")
	@Column(name = "NAME")
	private String name;

	@NotNull(message = "Price is required")
	@Column(name = "price")
	private Double price;

	@Column(name = "NOTE", length = 255)
	private String note;

	@Column(name = "PARAGRAPH", length = 255)
	private String paragraph;

	@Column(name = "UNIT")
	private String unit;

	@Column(name = "NORMAL")
	private String normal;

	@Column(name = "LOWER")
	private String lower;

	@Column(name = "UPPER")
	private String upper;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ExaminationType getExaminationType() {
		return examinationType;
	}

	public void setExaminationType(ExaminationType examinationType) {
		this.examinationType = examinationType;
	}

	public ExaminationGroup getExaminationGroup() {
		return examinationGroup;
	}

	public void setExaminationGroup(ExaminationGroup examinationGroup) {
		this.examinationGroup = examinationGroup;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getParagraph() {
		return paragraph;
	}

	public void setParagraph(String paragraph) {
		this.paragraph = paragraph;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getNormal() {
		return normal;
	}

	public void setNormal(String normal) {
		this.normal = normal;
	}

	public String getLower() {
		return lower;
	}

	public void setLower(String lower) {
		this.lower = lower;
	}

	public String getUpper() {
		return upper;
	}

	public void setUpper(String upper) {
		this.upper = upper;
	}

	@Override
	public String toString() {
		return "Examination [id=" + id + ", examinationType=" + examinationType + ", examinationGroup="
				+ examinationGroup + ", code=" + code + ", name=" + name + ", price=" + price + ", note=" + note
				+ ", paragraph=" + paragraph + ", unit=" + unit + ", normal=" + normal + ", lower=" + lower + ", upper="
				+ upper + "]";
	}

}
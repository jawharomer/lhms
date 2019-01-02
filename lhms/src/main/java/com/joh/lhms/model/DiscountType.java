package com.joh.lhms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DISCOUNT_TYPES")
public class DiscountType {

	@Column(name = "I_DISCOUNT_TYPE")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "DISCOUNT_TYPE_NAME")
	private String name;

	@Column(name = "DISCOUNT_TYPE_LIMIT")
	private Double limit;

	@Column(name = "FIX", columnDefinition = "TINYINT(1)")
	private boolean fix;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getLimit() {
		return limit;
	}

	public void setLimit(Double limit) {
		this.limit = limit;
	}

	public boolean isFix() {
		return fix;
	}

	public void setFix(boolean fix) {
		this.fix = fix;
	}

	@Override
	public String toString() {
		return "DiscountType [id=" + id + ", name=" + name + ", limit=" + limit + ", fix=" + fix + "]";
	}

}

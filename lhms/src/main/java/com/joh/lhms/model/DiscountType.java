package com.joh.lhms.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.hibernate.engine.transaction.jta.platform.internal.BitronixJtaPlatform;

@Entity
@Table(name = "DISCOUNT_TYPES")
public class DiscountType {

	@Column(name = "I_DISCOUNT_TYPE")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "DISCOUNT_TYPE_NAME")
	private String name;

	@Min(0)
	@Max(1)
	@Column(name = "DISCOUNT_TYPE_LIMIT")
	private BigDecimal limit;

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

	public BigDecimal getLimit() {
		return limit;
	}

	public void setLimit(BigDecimal limit) {
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

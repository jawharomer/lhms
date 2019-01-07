package com.joh.lhms.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "LHMS_PATIENT_VISITS")
public class PatientVisit {

	@Column(name = "I_PATIENT_VISIT")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne()
	@JoinColumn(name = "I_PATIENT")
	private Patient patient;

	@Column(name = "VISIT_TIME", insertable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp()
	@ColumnDefault("CURRENT_TIMESTAMP")
	private Date time;

	@Column(name = "TOTAL_PAYMENT")
	private Double totalPayment;

	@Column(name = "DISCOUNT_AMOUNT")
	@Min(value = 0, message = "minimum discountAmount is 0")
	@Max(value = 1, message = "maximum discountAmount is 1")
	private BigDecimal discountAmount;

	@ManyToOne()
	@JoinColumn(name = "I_DISCOUNT_TYPE")
	private DiscountType discountType;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "I_PATIENT_VISIT")
	private List<PatientExamination> patientExaminations = new ArrayList<>();

	@Column(name = "NOTE")
	private String note;

	@Column(name = "DONE")
	private boolean done;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Double getTotalPayment() {
		return totalPayment;
	}

	public void setTotalPayment(Double totalPayment) {
		this.totalPayment = totalPayment;
	}

	public BigDecimal getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}

	public DiscountType getDiscountType() {
		return discountType;
	}

	public void setDiscountType(DiscountType discountType) {
		this.discountType = discountType;
	}

	public List<PatientExamination> getPatientExaminations() {
		return patientExaminations;
	}

	public void setPatientExaminations(List<PatientExamination> patientExaminations) {
		this.patientExaminations = patientExaminations;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	@Override
	public String toString() {
		return "PatientVisit [id=" + id + ", patient=" + patient + ", time=" + time + ", totalPayment=" + totalPayment
				+ ", discountAmount=" + discountAmount + ", discountType=" + discountType + ", patientExaminations="
				+ patientExaminations + ", note=" + note + ", done=" + done + "]";
	}

}

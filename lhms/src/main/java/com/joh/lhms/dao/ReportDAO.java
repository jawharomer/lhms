package com.joh.lhms.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import com.joh.lhms.domain.model.NotificationD;
import com.joh.lhms.domain.model.NotificationD.NotificationType;

@Component
public class ReportDAO {
	@PersistenceContext
	private EntityManager em;

	public List<NotificationD> findAdminNotifications() {

		List<NotificationD> notificationDs = new ArrayList<>();

		// Notification-1

		Query query = em.createNativeQuery(
				"SELECT COUNT(*) FROM LHMS_PATIENT_VISITS WHERE VISIT_TIME BETWEEN CURDATE() AND CURDATE()+INTERVAL 1 DAY;");

		Object totalPatientVisitResult = query.getSingleResult();

		int totalPatientVisit = 0;
		if (totalPatientVisitResult != null)
			totalPatientVisit = Integer.parseInt("" + totalPatientVisitResult);

		//
		NotificationD not1 = new NotificationD();
		not1.setTitle("Total Today Patient Visit");
		not1.setEtc("" + totalPatientVisit);
		not1.setMessage("Total Number of patients visit in today");

		not1.setNotificationType(NotificationType.INFO);

		notificationDs.add(not1);

		// Notification-2

		query = em.createNativeQuery(
				"SELECT IFNULL(SUM(TOTAL_PAYMENT),0) FROM LHMS_PATIENT_VISITS  WHERE VISIT_TIME BETWEEN CURDATE() AND CURDATE()+INTERVAL 1 DAY;");

		Object toatlTodayPaymentResult = query.getSingleResult();

		double totalTodayPayment = 0;

		if (toatlTodayPaymentResult != null)
			totalTodayPayment = Double.parseDouble("" + toatlTodayPaymentResult);

		//
		NotificationD not2 = new NotificationD();
		not2.setTitle("Today Total  Payment");
		not2.setEtc("" + totalTodayPayment);
		not2.setMessage("Total today payment amount");

		not2.setNotificationType(NotificationType.INFO);

		notificationDs.add(not2);

		return notificationDs;

	}

	public List<String> findAllChronicDisease() {
		List<String> chronicDiseases = new ArrayList<>();

		Query query = em.createNativeQuery("SELECT DISTINCT DISEASE_NAME FROM CHRONIC_DISEASES ORDER BY DISEASE_NAME;");

		List<String> totalChronicDisease = query.getResultList();
		chronicDiseases.addAll(totalChronicDisease);
		return chronicDiseases;
	}

}

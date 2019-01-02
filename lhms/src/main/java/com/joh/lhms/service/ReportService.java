package com.joh.lhms.service;

import java.util.List;

import com.joh.lhms.domain.model.NotificationD;

public interface ReportService {

	List<NotificationD> findAdminNotifications();

	List<String> findAllChronicDisease();

}

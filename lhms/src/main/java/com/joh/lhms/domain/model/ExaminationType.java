package com.joh.lhms.domain.model;

public enum ExaminationType {
	Normal, Logical, Paragraph;

	public String[] getAllExaminationType() {
		String[] list = new String[ExaminationType.values().length];
		int i = 0;
		for (ExaminationType examinationType : ExaminationType.values()) {
			list[i++] = examinationType.name();
		}
		return list;
	}
}

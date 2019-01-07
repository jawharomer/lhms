<%@ page import="java.util.Date"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:useBean id="now" class="java.util.Date" />
<c:set var="tomorrow"
	value="<%=new Date(new Date().getTime() + 60 * 60 * 24 * 1000)%>" />
<fmt:formatDate var="currentDate" value="${now}"
	pattern="yyyy-MM-dd HH:mm:ss" />


<div id="section-to-print">
	<table class="table table-bordered">
		<tr>
			<td>#</td>
			<td>${patientVisit.id}</td>
		</tr>
		<tr>
			<td>Time</td>
			<td>${currentDate}</td>
		</tr>
		<tr>
			<td>Patient Name</td>
			<td>${patientVisit.patient.fullName}</td>
		</tr>
		<tr>
			<td>Total Price</td>
			<c:set var="totalPrice" value="${0}" />
			<c:forEach items="${patientVisit.patientExaminations}" var="eItem">
				<c:set var="totalPrice" value="${totalPrice+eItem.price}" />
			</c:forEach>
			<td><fmt:formatNumber maxFractionDigits="3">${totalPrice}</fmt:formatNumber>
			</td>
		</tr>
		<c:if test="${patientVisit.discountAmount!=null}">
			<tr>
				<td>Discount Amount</td>
				<td>${patientVisit.discountAmount}</td>
			</tr>
		</c:if>
		<tr>
			<td>Total Payment</td>
			<td><fmt:formatNumber maxFractionDigits="3">
			${patientVisit.totalPayment}
			</fmt:formatNumber></td>
		</tr>


	</table>
</div>
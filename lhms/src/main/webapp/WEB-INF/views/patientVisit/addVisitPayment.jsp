<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<script type="text/javascript">
	var jsonPatientVisit = '${jsonPatientVisit}';
	var jsonDiscountTypes = '<spring:escapeBody  javaScriptEscape="true">${jsonDiscountTypes}</spring:escapeBody>';
</script>

<div ng-app="addPatientVisit" ng-controller="addPatientVisit"
	ng-init="init()">

	<table class="table table-bordered" ng-form name="form">
		<tr>
			<td>#</td>
			<td>{{patientVisit.id}}</td>
		</tr>
		<tr>
			<td>Patient Name</td>
			<td>{{patientVisit.patient.fullName}}</td>
		</tr>

		<tr>
			<td>Total Price</td>
			<td>{{totalPrice()|number}}</td>
		</tr>

		<tr>
			<td>Discount By</td>
			<td><select ng-change="selectDiscountType()"
				class="form-control form-control-sm"
				ng-model="patientVisit.discountType.id">
					<option value="">Choose</option>
					<option ng-repeat="item in discountTypes" ng-value="item.id">{{item.name}}</option>
			</select></td>
		</tr>

		<tr>
			<td>Discount Amount</td>
			<td><span ng-if="patientVisit.discountType.fix==true">
					{{patientVisit.discountType.limit}} </span> <input name="discountAmount"
				class="form-control form-control-sm" type="number"
				ng-max="patientVisit.discountType.limit" min="0"
				ng-if="patientVisit.discountType.fix==false"
				ng-model="patientVisit.discountAmount"
				placeholder="{{patientVisit.discountType.limit}}" /></td>
		</tr>

		<tr>
			<td>Total Payment</td>
			<td>{{patientVisit.totalPayment|number}}</td>
		</tr>


	</table>
	<div class="p-1">
		<button class="btn btn-success" ng-disabled="form.$invalid"
			ng-click="save()">
			<i class="fa fa-save"></i>
		</button>


		<a
			href="<c:url value="/patientVisits/payment/" />{{patientVisit.id}}/print"
			class="btn btn-info" ng-disabled="form.$invalid" ng-click="save()">
			<i class="fa fa-print"></i>
		</a>
	</div>

</div>

<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<script type="text/javascript">
	var jsonPatientVisit = '${jsonPatientVisit}';
	var jsonExaminations = '<spring:escapeBody  javaScriptEscape="true">${jsonExaminations}</spring:escapeBody>';
</script>

<div id="add-patient-visit-contaner" ng-app="addPatientVisit"
	ng-controller="addPatientVisit" ng-init="init()">

	<table>
		<tr>
			<td>Patient</td>

			<td>{{patientVisit.patient.fullName}} <a
				href="<c:url value="/patients/edit/"/>{{patientVisit.patient.id}} "
				class="btn btn-sm btn-warning"> <i class="fa fa-edit"></i>
			</a>
			</td>
		</tr>
	</table>

	<table class="table table-borderd">
		<thead>
			<tr>
				<th>Code</th>
				<th>Name</th>
				<th>Price</th>
				<th>Group</th>
				<th>Result</th>
				<th>F</th>
			</tr>
			<tr ng-form name="form">
				<th><input class="form-control form-control-sm"
					ng-model="selectedExamination.code" id="examination-autocomplete"></th>
				<th><input readonly="readonly" required="required"
					ng-model="selectedExamination.name" name="name"
					class="form-control form-control-sm"></th>
				<th><input readonly="readonly" required="required"
					ng-model="selectedExamination.price" name="price"
					class="form-control form-control-sm"></th>
				<th><input readonly="readonly" required="required"
					ng-model="selectedExamination.examinationGroup.name" name="name"
					class="form-control form-control-sm"></th>
				<th>&nbsp;</th>
				<th>
					<button ng-disabled="form.$invalid||!patientExamination.result"
						class="btn btn-sm btn-success rounded-circle"
						ng-click="addPatientExamination()">
						<i class="fa fa-plus"></i>
					</button>
				</th>
			</tr>
			<tr ng-if="examinationType=='Paragraph'">
				<td colspan="6"><textarea ng-model="patientExamination.result"
						class="form-control form-control-sm" maxlength="255"></textarea></td>
			</tr>
			<!-- S-Normal -->
			<tr ng-if="examinationType=='Normal'">
				<td>Unit</td>
				<td>Lower</td>
				<td>Normal</td>
				<td>Upper</td>
				<td>Result</td>
			</tr>
			<tr ng-if="examinationType=='Normal'">
				<td><input readonly="readonly"
					ng-model="selectedExamination.unit"
					class="form-control form-control-sm"></td>
				<td><input readonly="readonly"
					ng-model="selectedExamination.lower"
					class="form-control form-control-sm"></td>
				<td><input readonly="readonly"
					ng-model="selectedExamination.normal"
					class="form-control form-control-sm"></td>
				<td><input readonly="readonly"
					ng-model="selectedExamination.upper"
					class="form-control form-control-sm"></td>
				<td><input ng-model="patientExamination.result" required
					class="form-control form-control-sm"></td>
			</tr>
			<!-- E-Normal -->

			<!-- S-Logical -->
			<tr ng-if="examinationType=='Logical'">
				<td colspan="6"><label for="posative"> Posative<input
						type="radio" value="Posative" name="logical" id="posative"
						ng-model="patientExamination.result" required> </lable> <label
						for="nagative"> Negative <input type="radio"
							name="logical" value="Negative" id="nagative"
							ng-model="patientExamination.result" required> </lable></td>
			</tr>


		</thead>

		<tbody>
			<tr ng-repeat="item in patientVisit.patientExaminations">
				<td>{{item.examination.code}}</td>
				<td>{{item.examination.name}}</td>
				<td>{{item.examination.price}}</td>
				<td><span ng-if="item.examination.examinationType=='Normal'">
						Unit:{{item.examination.unit}}<br>
						Lower:{{item.examination.unit}}<br>
						Normal:{{item.examination.unit}}<br>
						Upper:{{item.examination.unit}}<br>
				</span></td>
				<td>{{item.result}}</td>
				<td>
					<button class="btn btn-sm btn-danger rounded-circle"
						ng-click="deletePatientExamination($index)">
						<i class="fa fa-times"></i>
					</button>
				</td>
			</tr>

		</tbody>

	</table>
	<hr>
	<div class="card card-body bg-secondary text-white">
		<table>
			<tr>
				<td>Total Price</td>
				<td>{{totalPrice()|number}}</td>
			</tr>
			<tr>
				<td>Total Payment</td>
				<td>{{patientVisit.totalPayment}}</td>
			</tr>
		</table>
	</div>
	<div>
		Note:
		<textarea class="form-control" maxlength="255"
			ng-model="patientVisit.note">
			    
			
			</textarea>
	</div>
	<div class="p-1">
		<button class="btn btn-success" ng-click="save()">
			<i class="fa fa-save"></i>
		</button>
	</div>



</div>

<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<script type="text/javascript">
	var jsonExamination = '<spring:escapeBody  javaScriptEscape="true">${jsonExamination}</spring:escapeBody>';
	var jsonExaminationGroups = '<spring:escapeBody  javaScriptEscape="true">${jsonExaminationGroups}</spring:escapeBody>';
	var jsonExaminationTypes = '<spring:escapeBody  javaScriptEscape="true">${jsonExaminationTypes}</spring:escapeBody>';
</script>

<div id="add-patient-visit-contaner" ng-app="app" ng-controller="ctrl"
	ng-init="init()">
	<h4 class="text-warning">Edit Examination</h4>
	<table class="table table-bordered" ng-form name="form">

		<tr>
			<td>Code</td>
			<td><input required name="code"
				class="form-control form-control-sm" ng-model="examination.code" /></td>
		</tr>

		<tr>
			<td>Name</td>
			<td><input required name="name"
				class="form-control form-control-sm" ng-model="examination.name" /></td>
		</tr>

		<tr>
			<td>Price</td>
			<td><input type="number" min="0" required name="price"
				class="form-control form-control-sm" ng-model="examination.price" /></td>
		</tr>

		<tr>
			<td>ExaminationType</td>
			<td><select required name="examinationType"
				class="form-control form-control-sm"
				ng-model="examination.examinationType">
					<option value="" selected="selected">Choose</option>
					<option ng-repeat="item in examinationTypes" ng-value="item">
						{{item}}</option>
			</select></td>
		</tr>

		<tr>
			<td>ExaminationGroup</td>
			<td><select required class="form-control form-control-sm"
				ng-model="examination.examinationGroup.id"
				ng-options="item.id as item.name for item in examinationGroups">
					<option value=""></option>
			</select></td>
		</tr>

		<tr ng-if="examination.examinationType=='Normal'">
			<td>Unit</td>
			<td><input required name="unit"
				class="form-control form-control-sm" ng-model="examination.unit" /></td>
		</tr>

		<tr ng-if="examination.examinationType=='Normal'">
			<td>Normal</td>
			<td><input required name="normal"
				class="form-control form-control-sm" ng-model="examination.normal" /></td>
		</tr>

		<tr ng-if="examination.examinationType=='Normal'">
			<td>Lower</td>
			<td><input required name="lower"
				class="form-control form-control-sm" ng-model="examination.lower" /></td>
		</tr>


		<tr ng-if="examination.examinationType=='Normal'">
			<td>Upper</td>
			<td><input required name="upper"
				class="form-control form-control-sm" ng-model="examination.upper" /></td>
		</tr>

		<tr ng-if="examination.examinationType=='Paragraph'">
			<td>Paragraph</td>
			<td><textarea name="paragraph"
					class="form-control form-control-sm"
					ng-model="examination.paragraph">
				</textarea></td>
		</tr>

		<tr>
			<td>Note</td>
			<td><textarea style="max-width: 200px; height: 200px"
					maxlength="255" name="note" class="form-control form-control-sm"
					ng-model="examination.note">
				</textarea></td>
		</tr>



	</table>

	<button class="btn btn-warning" ng-disabled="form.$invalid"
		ng-click="save()">
		<i class="fa fa-save"></i>
	</button>

</div>

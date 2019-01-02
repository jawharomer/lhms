<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script>
	var reportTitle = 'Examinations';
</script>

<div>
	<div class="py-2">
		<h3>Examinations</h3>
		<button class="btn btn-success" onclick="getAddingExaminationGroup()">
			<i class="fa fa-plus"></i>
		</button>

	</div>

	<table id="examinations-table" class="display">
		<thead>
			<tr>
				<th>Code</th>
				<th>Name</th>
				<th>Price</th>
				<th>Type</th>
				<th>F</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${examinations}" var="item">
				<tr>
					<td>${item.code}</td>
					<td>${item.name}</td>
					<td>${item.price}</td>
					<td>${item.examinationType}</td>
					<td>
						<button class="btn btn-sm btn-danger"
							onclick="deleteExamination(${item.id})">
							<i class="fa fa-times"></i>
						</button> <a class="btn btn-sm btn-warning" target="_blank"
						href="<c:url value="/examinations/edit/"/>${item.id}"> <i
							class="fa fa-edit"></i>
					</a>

					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</div>
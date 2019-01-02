// Angular
app = angular.module("app", []);

app.controller('ctrl', function($scope, $http) {

	$scope.examination = {};

	$scope.examinationTypes = [];

	$scope.selectedOperationNote = "";

	$scope.init = function() {
		console.log("init->fired");
		console.log("jsonExamination=" + jsonExamination);
		console.log("jsonExaminationTypes=" + jsonExaminationTypes);
		console.log("jsonExaminationGroups=" + jsonExaminationGroups);

		$scope.examination = JSON.parse(jsonExamination);
		$scope.examinationTypes = JSON.parse(jsonExaminationTypes);
		$scope.examinationGroups = JSON.parse(jsonExaminationGroups);
	};

	$scope.save = function() {
		console.log("save->fired");
		console.log("$scope.patientVisit=", $scope.patientVisit);

		$http({
			method : 'POST',
			data : $scope.examination,
			url : $$ContextURL + '/examinations/update'
		}).then(
				function(response) {
					console.log(response);
					if (response.data.status == 200) {
						$("#modal-body").html(response.data.message);
						$("#modal").modal("show");
						setTimeout(
								function() {
									window.location.href = $$ContextURL
											+ '/examinations/edit/'
											+ response.data.etc;
								}, 1000);

					}
				}, function(response) {
					$("#modal-body").html(response.data);
					$("#modal").modal("show");
				});

	}

});

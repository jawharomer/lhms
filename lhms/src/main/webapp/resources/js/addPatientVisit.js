// Angular
app = angular.module("addPatientVisit", []);

app
		.controller(
				'addPatientVisit',
				function($scope, $http) {

					$scope.patientVisit = {};

					$scope.examinations;

					//

					$scope.selectedExamination = {};

					$scope.patientExamination = {};

					$scope.examinationType;

					$scope
							.$watch(
									"selectedExamination",
									function(n, o) {
										$scope.examinationType = $scope.selectedExamination.examinationType;
									});

					$scope.newExamination = {
						name : "",
						result : "",
						note : ""
					};

					$scope.resetNewExamination = angular
							.copy($scope.newExamination);

					$scope.init = function() {
						console.log("init->fired");
						console.log("jsonPatientVisit=" + jsonPatientVisit);
						console.log("jsonExaminations=" + jsonExaminations);

						$scope.patientVisit = JSON.parse(jsonPatientVisit);
						$scope.examinations = JSON.parse(jsonExaminations);

						// S-Examination AutoCompletion
						var examinationAuto = [];

						angular.forEach($scope.examinations, function(value,
								key) {
							var obj = {
								label : value.code + " " + value.name,
								value : value.code,
								data : value
							}
							examinationAuto.push(obj);
						});

						$("#examination-autocomplete")
								.autocomplete(
										{
											source : examinationAuto,
											select : function(event, ui) {
												var item = ui.item.data;
												console.log("selected item =",
														item);

												$scope.selectedExamination = item;

												if (item.examinationType == 'Paragraph') {
													$scope.patientExamination.result = item.paragraph;
												} else {
													$scope.patientExamination.result = "";
												}

												$scope.$digest();
											}
										});

						// E-Examination AutoCompletion

					};

					$scope.totalPrice = function() {
						var total = 0;
						for (var i = 0; i < $scope.patientVisit.patientExaminations.length; i++) {
							total = total
									+ $scope.patientVisit.patientExaminations[i].price;
						}
						return total;
					}

					$scope.addPatientExamination = function() {
						console.log("addPatientExamination->fired");
						var item = {};
						item.examination = $scope.selectedExamination;
						item.price = $scope.selectedExamination.price;
						item.result = $scope.patientExamination.result;

						$scope.patientVisit.patientExaminations.push(item);
						$scope.selectedExamination = {};
						$scope.patientExamination.result = "";

					}

					$scope.deletePatientExamination = function(index) {
						console.log("deletePatientExamination->fired");
						$scope.patientVisit.patientExaminations
								.splice(index, 1);
					}

				});

var app = angular.module('bossManager.controllers', []);

app.controller('bossManagerController', ['$scope','bossManagerService', '$location',
	function ($scope, bossManagerService, $location) {
		function checkRights() {			
			bossManagerService.checkRights().then(
				function (response) {
					if(response.data === 'true')
						findAll();
					else {
					    $location.path('login');
					    alert("Access denied!");
				    }
				}
			);
		}
		checkRights();		
		
		function findAll() {
			bossManagerService.findAll().then(
				function (response) {
					$scope.systemMenagers = response.data;
				}
			);
		}
		
		$scope.saveSystemManager = function () {            
			bossManagerService.save($scope.systemManager).then(
				function (response) {
                    alert("Uspesno dodat.");
                    $scope.state = undefined;
                    $location.path('bossManager/list');
                },
                function (response) {
                    alert("Greska pri dodavanju.");
                }
            ); 	
		};
	    $scope.delete = function (systemManager){
	    	bossManagerService.delete(systemManager.id).then(
	    		function (response) {
	    		     $scope.systemMenagers.splice($scope.systemMenagers.indexOf(systemManager), 1);
			    },
		        function (response) {
		        	alert("Error while deleting component.");
		        }
	    	);
		};		 
}]);
var app = angular.module('guest.controllers', []);
 
app.controller('guestController', ['$scope','$window','guestService', '$location',
  	function ($scope,$window, guestService, $location) {
		function checkRights() {			
			guestService.checkRights().then(
				function (response) {
					if(response.data === 'true'){
						
					}
					else {
						$location.path('login');
					}
				}
			);
		}
		checkRights();	
	
		$scope.getLoggedUser = function() {
			guestService.getLoggedUser().then(
				function (response) {
					$scope.loggedUser = response.data;
	            }		
			)
		}
		
		$scope.findFriends = function(){
			if($scope.inputStr !== '') {
				guestService.findFriends($scope.inputStr).then(
					function(response){
						$scope.guests = response.data;
					}
				)
			}
		}
		
		$scope.update = function() {
			guestService.updateGuestProfile($scope.loggedUser).then(
				function (response) {
                    alert("Successfully change.");
                    $scope.state = undefined;
                    $location.path('loggedIn/guest/profile');
                },
                function (response) {
                    alert("Error in changing.");
                }
			);
		}
		
		$scope.sendRequest = function(id) {
			guestService.sendRequest(id);
			$window.location.reload();
		}
		
		$scope.listFriends = function(){
			guestService.listFriends().then(
				function (response) {
					$scope.friends = response.data;
		        }		
			);
		}
}]);
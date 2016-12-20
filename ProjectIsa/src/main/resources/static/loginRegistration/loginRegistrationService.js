var services = angular.module('loginRegistration.services', ['ngResource']);

var baseUrl = 'http://localhost\\:8080';

services.service('loginRegistrationService', ['$http', function($http){
	
	this.logIn = function(user){
		return $http.post("/commonController/logIn",user);
	}
	
	this.logOut = function(){
		return $http.get("/commonController/logOut");
	}
	
}]);
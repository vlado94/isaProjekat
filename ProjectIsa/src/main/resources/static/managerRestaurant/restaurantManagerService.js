var services = angular.module('restaurantManager.services', ['ngResource']);

var baseUrl = 'http://localhost\\:8080';

services.service('restaurantManagerService', ['$http', function($http){
	
	this.findAll = function(){
		return $http.get("/restaurantManager");
	}
	
	this.findAllFreeManagers = function(){
		return $http.get("/restaurantManager/free");
	}
	
	this.findOne = function(id){
		return $http.get("/restaurantManager/"+id);
	}
	
	this.save = function(restaurantManager){
		return $http.post("/restaurantManager",restaurantManager);
	}
	
	this.update = function(restaurantManager){
		return $http.put("/restaurantManager/"+restaurantManager.id,restaurantManager);
	}
	
	this.delete = function(id){
		return $http.delete("/restaurantManager/"+id);
	}
}]);

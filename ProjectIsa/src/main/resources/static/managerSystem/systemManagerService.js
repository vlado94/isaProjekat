var services = angular.module('systemManager.services', ['ngResource']);

var baseUrl = 'http://localhost\\:8080';

services.service('systemManagerService', ['$http', function($http){
	
	this.findAll = function(){
		return $http.get("/systemManager");
	}
	
	this.findOne = function(id){
		return $http.get("/systemManager/"+id);
	}
	
	this.save = function(systemManager){
		return $http.post("/systemManager",systemManager);
	}
	
	this.update = function(systemManager){
		return $http.put("/systemManager/"+systemManager.id,systemManager);
	}
	
	this.delete = function(id){
		return $http.delete("/systemManager/"+id);
	}
}]);

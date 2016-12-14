package app.manager.restaurant;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurantManager")
public class RestaurantManagerController {
	
	private final RestaurantManagerService service; 
	
	@Autowired
	public RestaurantManagerController(final RestaurantManagerService service){
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<List<RestaurantManager>> findAll() {
		return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
	}
	
	@GetMapping(path = "/free")
	public ResponseEntity<List<RestaurantManager>> findAllFreeRestaurantManagers() {
		List<RestaurantManager> list = service.findAll();
		//kad se uvede bidirekciona veza, treba ova lista da se pretrazi i vrate samo slobodni menadzeri,oni koji nisu u nekom restoranu
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@Valid @RequestBody RestaurantManager restaurantManager){
		restaurantManager.setId(null);
		restaurantManager.setRegistrated(false);
		service.save(restaurantManager);
	}
	
	@GetMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public RestaurantManager findOne(@PathVariable Long id){
		RestaurantManager restaurantManager = service.findOne(id);
		Optional.ofNullable(restaurantManager).orElseThrow(() -> new ResourceNotFoundException("resourceNotFound!"));
		return restaurantManager;
	}
	
	@DeleteMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id){
		service.delete(id);
	}
	
	@PutMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public RestaurantManager update(@PathVariable Long id,@Valid @RequestBody RestaurantManager restaurantManager){
		Optional.ofNullable(service.findOne(id))
				.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
		restaurantManager.setId(id);
		return service.save(restaurantManager);
	}
	
}
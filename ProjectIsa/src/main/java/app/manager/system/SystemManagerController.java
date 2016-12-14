package app.manager.system;

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
@RequestMapping("/systemManager")
public class SystemManagerController {

	private SystemManagerService serviceSystemManager;

	@Autowired
	public SystemManagerController(final SystemManagerService serviceSystemManager) {
		this.serviceSystemManager = serviceSystemManager;
	}

	// izlistavanja svih menadzera sistema
	@GetMapping
	public ResponseEntity<List<SystemManager>> findAll() {
		return new ResponseEntity<>(serviceSystemManager.findAll(), HttpStatus.OK);
	}

	// 2.9
	// dodavanje novog menadzera sistema
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public void saveSystemManager(@Valid @RequestBody SystemManager systemManager) {
		// zastita u slucaju da mu npr preko postmana posaljemo id,da se
		// izignorise
		systemManager.setId(null);
		serviceSystemManager.save(systemManager);
	}

	// omogucena izmena nekog menadzera sistema, koju vrsi glavni menadzer
	@PutMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public SystemManager updateSystemManager(@PathVariable Long id, @Valid @RequestBody SystemManager systemManager) {
		Optional.ofNullable(serviceSystemManager.findOne(id))
				.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
		systemManager.setId(id);
		return serviceSystemManager.save(systemManager);
	}

	// nalazenje jednog menadzera sistema
	@GetMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public SystemManager findOne(@PathVariable Long id) {
		SystemManager systemManager = serviceSystemManager.findOne(id);
		Optional.ofNullable(systemManager).orElseThrow(() -> new ResourceNotFoundException("resourceNotFound!"));
		return systemManager;
	}

	// brisanje menadzera sistema
	@DeleteMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		serviceSystemManager.delete(id);
	}

	/*
	 * private fina RestaurantManagerService restaurantManager
	 * 
	 * 
	 * 
	 * public void saveRestaurantManager(@Valid @RequestBody RestaurantManage
	 * restaurantManager) restaurantManager.setId(null)
	 * this.restaurantManager.save(restaurantManager)
	 */
}
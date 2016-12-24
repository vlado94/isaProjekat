package app.manager.system;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import app.manager.restaurant.RestaurantManager;
import app.manager.restaurant.RestaurantManagerService;
import app.restaurant.Restaurant;
import app.restaurant.RestaurantService;

@RestController
@RequestMapping("/systemManager")
public class SystemManagerController {

	private HttpSession httpSession;
	private final RestaurantManagerService restaurantManagerService;
	private final RestaurantService restaurantService;
	private final SystemManagerService systemManagerService;

	@Autowired
	public SystemManagerController(final RestaurantManagerService restaurantManagerService,
			final RestaurantService restaurantService, final HttpSession httpSession,
			SystemManagerService systemManagerService) {
		this.restaurantManagerService = restaurantManagerService;
		this.httpSession = httpSession;
		this.restaurantService = restaurantService;
		this.systemManagerService = systemManagerService;
	}

	@SuppressWarnings("unused")
	@GetMapping("/checkRights")
	public boolean checkRights() {
		try {
			SystemManager systemManager = ((SystemManager) httpSession.getAttribute("user"));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// izlistavanje svih menadzera restorana
	@GetMapping("/restaurantManager")
	public ResponseEntity<List<RestaurantManager>> findAllRestaurantManagers() {
		return new ResponseEntity<>(restaurantManagerService.findAll(), HttpStatus.OK);
	}

	@GetMapping
	public SystemManager findSystemManager() {
		return ((SystemManager) httpSession.getAttribute("user"));
	}

	// izlistavanje svih menadzera restorana koji nemaju radno mesto
	@GetMapping(path = "/freeRestaurantManager")
	public List<RestaurantManager> findAllFreeRestaurantManagers() {
		// ovo ce se kasnije promeniti da ide odmah na bazu, sa posebnim upitom
		List<RestaurantManager> managers = restaurantManagerService.findAll();
		List<Restaurant> restaurants = restaurantService.findAll();

		List<RestaurantManager> result = new ArrayList<RestaurantManager>();
		for (int i = 0; i < managers.size(); i++) {
			for (int j = 0; j < restaurants.size(); j++)
				if (managers.get(i).getId() == restaurants.get(j).getId())
					break;
			result.add(managers.get(i));
		}

		return result;
	}

	// dodavanje novog menadzera restorana
	@PostMapping("/restaurantManager")
	@ResponseStatus(HttpStatus.CREATED)
	public void saveRestaurantManager(@Valid @RequestBody RestaurantManager restaurantManager) {
		restaurantManager.setId(null);
		restaurantManager.setRegistrated("0");
		restaurantManagerService.save(restaurantManager);
	}

	// izlistavanje svih restorana
	@GetMapping(path = "/restaurant")
	@ResponseStatus(HttpStatus.OK)
	public List<Restaurant> findAllRestaurant() {
		return restaurantService.findAll();
	}

	// 2.3
	// registrovanje novih restorana, sa vec postojecim menadzerima
	// isti,mora postojati menadzer da bi
	// bio postavljen za menadzer datog restoran
	@PostMapping(path = "/restaurant")
	@ResponseStatus(HttpStatus.CREATED)
	public void saveRestaurant(@Valid @RequestBody Restaurant restaurant) {
		restaurant.setId(null);
		restaurantService.save(restaurant);
	}

	@PutMapping(path = "/{id}")
	public SystemManager updateSystemManager(@PathVariable Long id, @Valid @RequestBody SystemManager systemManager) {
		Optional.ofNullable(restaurantManagerService.findOne(id))
				.orElseThrow(() -> new ResourceNotFoundException("resourceNotFound!"));
		systemManager.setId(id);
		return systemManagerService.save(systemManager);
	}
}
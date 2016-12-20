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
import org.springframework.web.bind.annotation.DeleteMapping;
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

	@Autowired
	public SystemManagerController(final RestaurantManagerService restaurantManagerService,
			final RestaurantService restaurantService, final HttpSession httpSession) {
		this.restaurantManagerService = restaurantManagerService;
		this.httpSession = httpSession;
		this.restaurantService = restaurantService;
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

	// izlistavanje svih menadzera restorana koji nemaju radno mesto
	@GetMapping(path = "/freeRestaurantManager")
	public ResponseEntity<List<RestaurantManager>> findAllFreeRestaurantManagers() {
		// ovo ce se kasnije promeniti da ide odmah na bazu, sa posebnim upitom
		List<RestaurantManager> list = restaurantManagerService.findAll();
		List<RestaurantManager> result = new ArrayList<RestaurantManager>();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getRestaurant() == null)
				result.add(list.get(i));
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	// dodavanje novog menadzera restorana
	@PostMapping("/restaurantManager")
	@ResponseStatus(HttpStatus.CREATED)
	public void saveRestaurantManager(@Valid @RequestBody RestaurantManager restaurantManager) {
		restaurantManager.setId(null);
		restaurantManager.setRegistrated("0");
		restaurantManagerService.save(restaurantManager);
	}

	// nalazenje trazenog menadzera restorana
	@GetMapping(path = "/restaurantManager/{id}")
	@ResponseStatus(HttpStatus.OK)
	public RestaurantManager findOneRestaurantManager(@PathVariable Long id) {
		RestaurantManager restaurantManager = restaurantManagerService.findOne(id);
		Optional.ofNullable(restaurantManager).orElseThrow(() -> new ResourceNotFoundException("resourceNotFound!"));
		return restaurantManager;
	}

	// brisanje menadzera restorana
	@DeleteMapping(path = "/restaurantManager/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteRestaurantManager(@PathVariable Long id) {
		restaurantManagerService.delete(id);
	}

	// izmena postojeceg menadzera
	@PutMapping(path = "/restaurantManager/{id}")
	@ResponseStatus(HttpStatus.OK)
	public RestaurantManager updateRestaurantManager(@PathVariable Long id,
			@Valid @RequestBody RestaurantManager restaurantManager) {
		Optional.ofNullable(restaurantManagerService.findOne(id))
				.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
		restaurantManager.setId(id);
		return restaurantManagerService.save(restaurantManager);
	}

	
	
	
	// izlistavanje svih restorana
	@GetMapping(path = "/restaurant")
	@ResponseStatus(HttpStatus.OK)
	public List<Restaurant> findAllRestaurant() {
		return restaurantService.findAll();
	}

	// pronalazak bilo kog restorana
	@GetMapping(path = "/restaurant/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Restaurant findOneRestaurant(@PathVariable Long id) {
		Restaurant restaurant = restaurantService.findOne(id);
		Optional.ofNullable(restaurant).orElseThrow(() -> new ResourceNotFoundException("resourceNotFound!"));
		return restaurant;
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

	// brisanje restorana
	@DeleteMapping(path = "/restaurant/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteRestaurant(@PathVariable Long id) {
		Optional.ofNullable(restaurantService.findOne(id))
				.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
		restaurantService.delete(id);
	}

	// izmena restorana
	@PutMapping(path = "/restaurant/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Restaurant updateRestaurant(@PathVariable Long id, @Valid @RequestBody Restaurant restaurant) {
		Optional.ofNullable(restaurantService.findOne(id))
				.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
		restaurant.setId(id);
		return restaurantService.save(restaurant);
	}
}
package app.restaurant;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

@RestController
@RequestMapping("/restaurant")
@SessionAttributes
public class RestaurantController {
	private final RestaurantService service;

	@Autowired
	public RestaurantController(final RestaurantService service) {
		this.service = service;
	}

	// pronalazak bilo kog restorana
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Restaurant> findAll() {
		return service.findAll();
	}

	// pronalazak bilo kog restorana
	@GetMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Restaurant findOne(@PathVariable Long id, HttpSession request) {
		Restaurant restaurant = service.findOne(id);
		Optional.ofNullable(restaurant).orElseThrow(() -> new ResourceNotFoundException("resourceNotFound!"));
		return restaurant;
	}

	// 2.3
	// registrovanje novih restorana, sa vec postojecim menadzerima
	//  isti,mora postojati menadzer da bi
	// bio postavljen za menadzer  datog restoran
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@Valid @RequestBody Restaurant restaurant) {
		restaurant.setId(null);
		service.save(restaurant);
	}

	// brisanje restorana
	@DeleteMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		Optional.ofNullable(service.findOne(id)).orElseThrow(() -> new  ResourceNotFoundException("Resource Not Found!"));
		service.delete(id);
	}


	 // izmena restoran
	 @PutMapping(path = "/{id}")
	 @ResponseStatus(HttpStatus.OK)
	 public Restaurant update(@PathVariable  Long id, @Valid @RequestBody Restaurant restaurant) {
		 Optional.ofNullable(service.findOne(id)).orElseThrow(() -> new  ResourceNotFoundException("Resource Not Found!"));
		 restaurant.setId(id);
		 return service.save(restaurant);
	 }
}
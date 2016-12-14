package app.employed.waiter;

import java.util.ArrayList;
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

import app.drink.Drink;
import app.drink.DrinkService;
import app.order.OrderService;
import app.order.Orderr;

@RestController
@RequestMapping("/waiters")
public class WaiterController {

	private final WaiterService service;
	private final OrderService orderService;
	private final DrinkService drinkService;
	// private List<Waiter> waiter;

	@Autowired
	public WaiterController(final WaiterService service, final OrderService orderService, final DrinkService drinkService) {
		this.service = service;
		this.orderService = orderService;
		this.drinkService = drinkService;
		// this.waiter = service.findAll();
	}

	/*
	 * public boolean check(){ if (waiter.getOrders().size() > 0) { for (int i =
	 * 0; i < waiter.getOrders().size(); i++) { if
	 * (waiter.getOrders().get(i).getRestaurant() != waiter.getRestaurant()) {
	 * return false; } } return true; }else{ return true; } }
	 */

	@GetMapping
	public ResponseEntity<List<Waiter>> findAll() {
		return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@Valid @RequestBody Waiter waiter) {
		waiter.setId(null);
		waiter.setRegistrated(false);
		service.save(waiter);
	}

	@GetMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Waiter findOne(@PathVariable Long id) {
		Waiter waiter = service.findOne(id);
		Optional.ofNullable(waiter).orElseThrow(() -> new ResourceNotFoundException("resourceNotFound!"));
		return waiter;
	}

	@DeleteMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}

	@PutMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Waiter update(@PathVariable Long id, @Valid @RequestBody Waiter waiter) {
		Optional.ofNullable(service.findOne(id))
				.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
		waiter.setId(id);
		return service.save(waiter);
	}

	// sve narudzbine za odredjenog konobara
	@GetMapping(path = "/{id}/order")
	public ResponseEntity<List<Orderr>> findAllOrders(@PathVariable Long id) {
		Waiter waiter = service.findOne(id);
		List<Orderr> orders = new ArrayList<Orderr>();
		Optional.ofNullable(waiter.getOrders()).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));

		// if (!waiter.getOrders().isEmpty()) {
		try {
			for (int i = 0; i < waiter.getOrders().size(); i++) {
				orders.add(waiter.getOrders().get(i));
			}
			return new ResponseEntity<>(orders, HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			new ResourceNotFoundException("Resource Not Found!");
		}

		return null;
	}

	
	//2.4. Konobar izmeni porudzbinu za odredjeni sto
	@PutMapping(path = "/{id}/order/{id1}")
	@ResponseStatus(HttpStatus.OK)
	public Orderr update(@PathVariable Long id, @PathVariable Long id1, @Valid @RequestBody Orderr order) {
		Optional.ofNullable(service.findOne(id))
				.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
		Optional.ofNullable(orderService.findOne(id1))
				.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
	
		order.setId(id1);
		
		return orderService.save(order);
	}
	
	

}

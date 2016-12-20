package app.employed.cook;

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

import app.dish.Dish;
import app.order.DishStatus;
import app.order.OrderService;
import app.order.Orderr;

@RestController
@RequestMapping("/cook")
public class CookController {

	private final CookService cookService;
	private final OrderService orderService;

	@Autowired
	public CookController(final CookService cookService, final OrderService orderService) {
		this.cookService = cookService;
		this.orderService = orderService;
	}

	@GetMapping
	public ResponseEntity<List<Cook>> findAll() {
		return new ResponseEntity<>(cookService.findAll(), HttpStatus.OK);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@Valid @RequestBody Cook cook) {
		cook.setId(null);
		cook.setRegistrated("0");
		cookService.save(cook);
	}

	@GetMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Cook findOne(@PathVariable Long id) {
		Cook cook = cookService.findOne(id);
		Optional.ofNullable(cook).orElseThrow(() -> new ResourceNotFoundException("resourceNotFound!"));
		return cook;
	}

	@DeleteMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		cookService.delete(id);
	}

	// 2.4. kuvar ima mogucnost da azurira podatke
	@PutMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Cook update(@PathVariable Long id, @Valid @RequestBody Cook cook) {
		Optional.ofNullable(cookService.findOne(id))
				.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
		cook.setId(id);
		return cookService.save(cook);
	}

	// 2.4 vidi listu porudzbina jela koje je potrebno pripremiti
	@GetMapping(path = "/{id}/order")
	public ResponseEntity<List<Dish>> findAllOrders(@PathVariable Long id) {

		List<Orderr> orders = cookService.findOne(id).getOrders();
		Optional.ofNullable(orders).orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
		List<Dish> food = new ArrayList<Dish>();

		for (int i = 0; i < orders.size(); i++) {
			if (orders.get(i).getFood().size() != 0 && orders.get(i).getDishStatus() == null) {
				for (int j = 0; j < orders.get(i).getFood().size(); j++) {
					food.add(orders.get(i).getFood().get(j));
				}
			}
		}

		return new ResponseEntity<>(food, HttpStatus.OK);

	}

	// 2.4. signalizira da su jela sa porudzbine prihvacena za spremanje
	@GetMapping(path = "/{cookId}/foodReceived/{orderId}")
	@ResponseStatus(HttpStatus.OK)
	public Orderr foodReceived(@PathVariable Long cookId, @PathVariable Long orderId) {
		Optional.ofNullable(cookService.findOne(cookId))
				.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));

		Optional.ofNullable(orderService.findOne(orderId))
				.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));

		Orderr order = orderService.findOne(orderId);

		orderService.findOne(orderId).setDishStatus(DishStatus.received);
		order.setId(orderId);
		return orderService.save(order);
	}

	// 2.4 signazilira da je odgovarajuce jelo gotovo
	@GetMapping(path = "/{cookId}/foodReady/{orderId}")
	@ResponseStatus(HttpStatus.OK)
	public Orderr foodReady(@PathVariable Long cookId, @PathVariable Long orderId) {
		Optional.ofNullable(cookService.findOne(cookId))
				.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));

		Optional.ofNullable(orderService.findOne(orderId))
				.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));

		Orderr order = orderService.findOne(orderId);

		orderService.findOne(orderId).setDishStatus(DishStatus.finished);
		order.setId(orderId);
		return orderService.save(order);
	}

}

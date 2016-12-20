package app.manager.restaurant;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import app.bidder.Bidder;
import app.dish.Dish;
import app.drink.Drink;
import app.drink.DrinkService;
import app.employed.bartender.Bartender;
import app.employed.cook.Cook;
import app.employed.waiter.Waiter;
import app.restaurant.Restaurant;
import app.restaurant.RestaurantService;

@RestController
@RequestMapping("/restaurantManager")
public class RestaurantManagerController {

	private HttpSession httpSession;
	private RestaurantService restaurantService;
	
	@Autowired
	public RestaurantManagerController(final HttpSession httpSession, final RestaurantService restaurantService,final DrinkService drinkService) {
		this.httpSession = httpSession;
		this.restaurantService = restaurantService;
	}

	@SuppressWarnings("unused")
	@GetMapping("/checkRights")
	public boolean checkRights() {
		try {
			RestaurantManager restaurantManager = ((RestaurantManager) httpSession.getAttribute("user"));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@GetMapping("/restaurant")
	public ResponseEntity<Restaurant> findManager() {
		Long restaurantId = ((RestaurantManager) httpSession.getAttribute("user")).getRestaurant().getId();
		Restaurant restaurant = restaurantService.findOne(restaurantId);
		return new ResponseEntity<>(restaurant, HttpStatus.OK);
	}

	@PostMapping(path = "/restaurant/saveDrink")
	@ResponseStatus(HttpStatus.CREATED)
	public void saveDrink(@Valid @RequestBody Drink drink) {
		Long restaurantId = ((RestaurantManager) httpSession.getAttribute("user")).getRestaurant().getId();
		Restaurant restaurant = restaurantService.findOne(restaurantId);
		//drink.setRestaurant(restaurant);
		restaurant.getDrinks().add(drink);
		restaurantService.save(restaurant);
	}

	@PostMapping(path = "/restaurant/saveDish")
	@ResponseStatus(HttpStatus.CREATED)
	public void saveDish(@Valid @RequestBody Dish dish) {
		Long restaurantId = ((RestaurantManager) httpSession.getAttribute("user")).getRestaurant().getId();
		Restaurant restaurant = restaurantService.findOne(restaurantId);
		//dish.setRestaurant(restaurant);
		restaurant.getFood().add(dish);
		restaurantService.save(restaurant);
	}

	@PostMapping(path = "/restaurant/saveWaiter")
	@ResponseStatus(HttpStatus.CREATED)
	public void saveWaiter(@Valid @RequestBody Waiter waiter) {
		Long restaurantId = ((RestaurantManager) httpSession.getAttribute("user")).getRestaurant().getId();
		Restaurant restaurant = restaurantService.findOne(restaurantId);
		//waiter.setRestaurant(restaurant);
		restaurant.getWaiters().add(waiter);
		restaurantService.save(restaurant);
	}

	@PostMapping(path = "/restaurant/saveCook")
	@ResponseStatus(HttpStatus.CREATED)
	public void saveCook(@Valid @RequestBody Cook cook) {
		Long restaurantId = ((RestaurantManager) httpSession.getAttribute("user")).getRestaurant().getId();
		Restaurant restaurant = restaurantService.findOne(restaurantId);
		//cook.setRestaurant(restaurant);
		restaurant.getCooks().add(cook);
		restaurantService.save(restaurant);
	}

	@PostMapping(path = "/restaurant/saveBartender")
	@ResponseStatus(HttpStatus.CREATED)
	public void saveCook(@Valid @RequestBody Bartender bartender) {
		Long restaurantId = ((RestaurantManager) httpSession.getAttribute("user")).getRestaurant().getId();
		Restaurant restaurant = restaurantService.findOne(restaurantId);
		//bartender.setRestaurant(restaurant);
		restaurant.getBartenders().add(bartender);
		restaurantService.save(restaurant);
	}

	@PostMapping(path = "/restaurant/saveBidder")
	@ResponseStatus(HttpStatus.CREATED)
	public void saveBidder(@Valid @RequestBody Bidder bidder) {
		Long restaurantId = ((RestaurantManager) httpSession.getAttribute("user")).getRestaurant().getId();
		Restaurant restaurant = restaurantService.findOne(restaurantId);
		//bidder.setRestaurant(restaurant);
		restaurant.getBidders().add(bidder);
		restaurantService.save(restaurant);
	}

	// mora da postoji zbog json igrnore sa strane restorana
	@GetMapping(path = "/restaurant/waitres")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Waiter> findAllWaitresInRestaurant() {
		Long restaurantId = ((RestaurantManager) httpSession.getAttribute("user")).getRestaurant().getId();
		Restaurant restaurant = restaurantService.findOne(restaurantId);
		return restaurant.getWaiters();
	}

	// mora da postoji zbog json igrnore sa strane restorana
	@GetMapping(path = "/restaurant/cooks")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Cook> findAllCooksInRestaurant() {
		Long restaurantId = ((RestaurantManager) httpSession.getAttribute("user")).getRestaurant().getId();
		Restaurant restaurant = restaurantService.findOne(restaurantId);
		return restaurant.getCooks();
	}

	// mora da postoji zbog json igrnore sa strane restorana
	@GetMapping(path = "/restaurant/bartenders")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Bartender> findAllBartendersInRestaurant() {
		Long restaurantId = ((RestaurantManager) httpSession.getAttribute("user")).getRestaurant().getId();
		Restaurant restaurant = restaurantService.findOne(restaurantId);
		return restaurant.getBartenders();
	}

	// mora da postoji zbog json igrnore sa strane restorana
	@GetMapping(path = "/restaurant/bidders")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Bidder> findAllBiddersInRestaurant() {
		Long restaurantId = ((RestaurantManager) httpSession.getAttribute("user")).getRestaurant().getId();
		Restaurant restaurant = restaurantService.findOne(restaurantId);
		return restaurant.getBidders();
	}
}
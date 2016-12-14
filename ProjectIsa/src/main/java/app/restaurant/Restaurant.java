package app.restaurant;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import app.dish.Dish;
import app.drink.Drink;
import app.employed.waiter.Waiter;
import app.manager.restaurant.RestaurantManager;
import app.order.Orderr;
import lombok.Data;

@Data
@Entity
public class Restaurant {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "RESTAURANT_ID")
	private Long id;

	@Column
	@NotBlank
	private String name;

	@NotNull
	@OneToOne
	@JoinColumn(name = "RESTAURANT_MANAGER_ID")
	private RestaurantManager restaurantManager;

	@OneToMany
	@JoinTable(name = "RESTAURANT_DISH", joinColumns = @JoinColumn(name = "RESTAURANT_ID"), inverseJoinColumns = @JoinColumn(name = "DISH_ID"))
	private List<Dish> food;

	@OneToMany
	@JoinTable(name = "RESTAURANT_DRINK", joinColumns = @JoinColumn(name = "RESTAURANT_ID"), inverseJoinColumns = @JoinColumn(name = "DRINK_ID"))
	private List<Drink> drinks;

	@OneToMany(mappedBy = "restaurant")
	private List<Segment> segments;

	// proveriti kardinalitete
	@OneToMany
	@JoinTable(name = "RESTUARANT_STAFF", joinColumns = @JoinColumn(name = "RESTAURANT_ID"), inverseJoinColumns = @JoinColumn(name = "WAITER_ID"))
	private List<Waiter> waiters;

	@Column
	private Integer summRate;

	@Column
	private Integer numRate;

	@OneToMany
	@JoinTable(name = "RESTAURANT_ORDER", joinColumns = @JoinColumn(name = "RESTAURANT_ID"), inverseJoinColumns = @JoinColumn(name = "ORDER_ID"))
	private List<Orderr> order;

}
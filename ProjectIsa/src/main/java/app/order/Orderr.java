package app.order;
                           
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import app.dish.Dish;
import app.drink.Drink;
import app.restaurant.Restaurant;
import lombok.Data;

@Data
@Entity
public class Orderr {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ORDER_ID")
	private Long id;
	
	@Column
	private boolean status;

	@ManyToMany
	@JoinTable(name = "ORDER_DRINKS", joinColumns = @JoinColumn(name = "ORDER_ID"), inverseJoinColumns = @JoinColumn(name = "DRINK_ID"))
	private List<Drink> drinks;

	@ManyToMany
	@JoinTable(name = "ORDER_FOOD", joinColumns = @JoinColumn(name = "ORDER_ID"), inverseJoinColumns = @JoinColumn(name = "DISH_ID"))
	private List<Dish> food;
	
	@ManyToOne
	@JoinTable(name = "ORDER_RESTAURANT", joinColumns = @JoinColumn(name = "ORDER_ID"), inverseJoinColumns = @JoinColumn(name = "RESTAURANT_ID"))
	private Restaurant restaurant;

	public void Order(List<Drink> drinks, List<Dish> food, Restaurant restaurant) {
		this.drinks = drinks;
		this.food = food;
		this.restaurant = restaurant;
	}

	public void Order() {

	}

}

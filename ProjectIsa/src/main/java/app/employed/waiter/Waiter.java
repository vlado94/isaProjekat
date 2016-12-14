package app.employed.waiter;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import app.employed.Employed;
import app.order.Orderr;
import app.restaurant.Restaurant;
import lombok.Data;

@Data
@Entity
public class Waiter extends Employed{
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) //automatsko generisanje kljuca
	@Column(name = "WAITER_ID")
	private Long id;
	
	@OneToMany
	@JoinTable(name = "WAITER_ORDERS", joinColumns = @JoinColumn(name = "WAITER_ID"), inverseJoinColumns = @JoinColumn(name = "ORDER_ID"))
	private List<Orderr> orders;
	
	@ManyToOne
	@JoinTable(name = "WAITER_RESTAURANT", joinColumns = @JoinColumn(name = "WAITER_ID"), inverseJoinColumns = @JoinColumn(name = "RESTAURANT_ID"))
	private Restaurant restaurant;
}
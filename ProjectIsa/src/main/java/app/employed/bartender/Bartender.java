package app.employed.bartender;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import app.employed.Employed;
import app.order.Orderr;
import app.restaurant.Restaurant;
import lombok.Data;

@Data
@Entity
public class Bartender extends Employed {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "BARTENDER_ID")
	private Long id;

	@OneToMany
	@JoinTable(name = "BARTENDER_ORDERS", joinColumns = @JoinColumn(name = "BARTENDER_ID"), inverseJoinColumns = @JoinColumn(name = "ORDER_ID"))
	private List<Orderr> orders;

	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "RESTAURANT_ID")
	private Restaurant restaurant;
}

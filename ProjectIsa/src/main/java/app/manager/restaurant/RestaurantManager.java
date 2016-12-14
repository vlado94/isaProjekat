package app.manager.restaurant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import app.common.User;
import app.restaurant.Restaurant;
import lombok.Data;

@Data
@Entity
public class RestaurantManager extends User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "RESTAURANT_MANAGER_ID")
	private Long id;

	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "RESTAURANT_ID")
	private Restaurant restaurant;
}

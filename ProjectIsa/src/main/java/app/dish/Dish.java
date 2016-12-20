package app.dish;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import app.restaurant.Restaurant;
import lombok.Data;

@Data
@Entity
public class Dish {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "DISH_ID")
	private Long id;

	@NotNull
	@Column
	private String name;

	@Column
	private String text;

	@NotNull
	@Column
	private Integer price;

	@NotNull
	@Column
	private Integer count;

	@Column
	private Integer summRate;

	@Column
	private Integer numRate;

	@JsonIgnore
	// @NotNull
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "RESTAURANT_ID")
	private Restaurant restaurant;
}
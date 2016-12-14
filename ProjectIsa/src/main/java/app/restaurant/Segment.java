package app.restaurant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
public class Segment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SEGMENT_ID")
	private Long id;

	@Column
	private String name;

	@Enumerated(EnumType.STRING)
	@NotNull
	private Configuration cardinality;

	@ManyToOne
	@JoinColumn(name = "RESTAURANT_ID")
	private Restaurant restaurant;
}
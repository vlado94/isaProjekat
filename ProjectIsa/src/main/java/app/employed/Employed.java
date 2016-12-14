package app.employed;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import app.common.User;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class Employed extends User{

	
	@Enumerated(EnumType.STRING)
	@NotNull
	@Column
	private ClothesSize clothesSize;

	@Enumerated(EnumType.STRING)
	@NotNull
	@Column
	private ShoesSize shoesSize;
	/*@Email
	@NotBlank
	@Column(unique = true)
	private String mail;

	@NotBlank
	@Column
	private String password;

	@NotBlank
	@Column
	private String firstname;

	@NotBlank
	@Column
	private String lastname;

	// flag koji je inicijalno false, i kad korisnik klikne na link pri
	// registraciji postaje true
	@Column
	private boolean registrated;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	@Column
	private ClothesSize clothesSize;

	@Enumerated(EnumType.STRING)
	@NotNull
	@Column
	private ShoesSize shoesSize;*/
	

	/*@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "EMPLOYED_ID")
	private Long id;



	// Treba organizovati i raspored rada
	// private Date datumRodjenja;
	// @Column
	@Enumerated(EnumType.STRING)
	@NotNull
	@Column
	private ClothesSize clothesSize;

	@Enumerated(EnumType.STRING)
	@NotNull
	@Column
	private ShoesSize shoesSize;

	@ManyToOne
	@JoinTable(name = "EMPLOYED_RESTAURANT", joinColumns = @JoinColumn(name = "EMPLOYED_ID"), inverseJoinColumns = @JoinColumn(name = "RESTAURANT_ID"))
	private Restaurant restaurant;*/
}

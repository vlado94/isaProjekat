package app.guest;

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

@RestController
@RequestMapping("/guests")
public class GuestController {
	private final GuestService service;

	@Autowired
	public GuestController(final GuestService service) {
		this.service = service;
	}

	// izlistavanje svih gostiju
	@GetMapping
	public ResponseEntity<List<Guest>> findAll() {
		return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
	}

	// registracija gosta
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@Valid @RequestBody Guest guest) {
		guest.setId(null);
		guest.setRegistrated(false);
		service.save(guest);
	}

	//pretraga gostiju
	@GetMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Guest findOne(@PathVariable Long id) {
		Guest guest = service.findOne(id);
		Optional.ofNullable(guest).orElseThrow(() -> new ResourceNotFoundException("resourceNotFound!"));
		return guest;
	}
	
	//brisanje gostiju
	@DeleteMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}

	//2.2
	//izmena informacija o gostu
	@PutMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Guest update(@PathVariable Long id, @Valid @RequestBody Guest guest) {
		Optional.ofNullable(service.findOne(id))
				.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
		guest.setId(id);
		return service.save(guest);
	}
}
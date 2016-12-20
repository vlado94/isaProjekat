package app.bidder;

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
@RequestMapping("/bidders")
public class BidderController {
	private final BidderService service;

	@Autowired
	public BidderController(final BidderService service) {
		this.service = service;
	}

	// izlistavanje svih gostiju
	@GetMapping
	public ResponseEntity<List<Bidder>> findAll() {
		return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
	}

	// registracija gosta
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@Valid @RequestBody Bidder bidder) {
		bidder.setId(null);
		bidder.setRegistrated("0");
		service.save(bidder);
	}

	// pretraga gostiju
	@GetMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Bidder findOne(@PathVariable Long id) {
		Bidder bidder = service.findOne(id);
		Optional.ofNullable(bidder).orElseThrow(() -> new ResourceNotFoundException("resourceNotFound!"));
		return bidder;
	}

	// brisanje gostiju
	@DeleteMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}

	// 2.2
	// izmena informacija o gostu
	@PutMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Bidder update(@PathVariable Long id, @Valid @RequestBody Bidder bidder) {
		Optional.ofNullable(service.findOne(id))
				.orElseThrow(() -> new ResourceNotFoundException("Resource Not Found!"));
		bidder.setId(id);
		return service.save(bidder);
	}
}
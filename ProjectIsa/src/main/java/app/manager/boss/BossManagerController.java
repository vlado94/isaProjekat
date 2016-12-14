package app.manager.boss;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/boss")
public class BossManagerController {

	private final BossManagerService serviceBoss;

	@Autowired
	public BossManagerController(final BossManagerService serviceBoss) {
		this.serviceBoss = serviceBoss;
	}

	// dodavanje novog boss menadzera, ako ima potrebe
	@PostMapping(path = "/boss")
	@ResponseStatus(HttpStatus.CREATED)
	public void saveBoss(@Valid @RequestBody BossManager bossManager) {
		bossManager.setId(null);
		serviceBoss.save(bossManager);
	}
}
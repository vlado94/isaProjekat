package app.manager.boss;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class BossManagerServiceImpl implements BossManagerService {
	private final BossManagerRepository repository;

	@Autowired
	public BossManagerServiceImpl(final BossManagerRepository repository) {
		this.repository = repository;
	}

	@Override
	public BossManager save(BossManager boss) {
		return repository.save(boss);
	}
}
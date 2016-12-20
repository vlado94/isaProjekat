package app.manager.boss;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

@Service
@Transactional
public class BossManagerServiceImpl implements BossManagerService {
	private final BossManagerRepository repository;

	@Autowired
	public BossManagerServiceImpl(final BossManagerRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public List<BossManager> findAll() {
		return Lists.newArrayList(repository.findAll());
	}

	@Override
	public BossManager save(BossManager boss) {
		return repository.save(boss);
	}

	@Override
	public BossManager findOne(String mail,String password) {
		List<BossManager> list = findAll();
		BossManager bossManager = list.get(0);
		if(bossManager.getMail().equals(mail) && bossManager.getPassword().equals(password))
			return bossManager;
		return null;
	}
}
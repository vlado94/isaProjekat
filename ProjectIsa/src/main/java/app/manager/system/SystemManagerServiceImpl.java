package app.manager.system;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

@Service
@Transactional
public class SystemManagerServiceImpl implements SystemManagerService {
	private final SystemManagerRepository repository;

	@Autowired
	public SystemManagerServiceImpl(final SystemManagerRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<SystemManager> findAll() {
		return Lists.newArrayList(repository.findAll());
	}

	@Override
	public SystemManager save(SystemManager systemManager) {
		return repository.save(systemManager);
	}

	@Override
	public SystemManager findOne(Long id) {
		return repository.findOne(id);
	}

	// ovo se kasnije na repo spusta
	@Override
	public SystemManager findOne(String mail, String password) {
		List<SystemManager> systemManagers = (List<SystemManager>) repository.findAll();
		for (int i = 0; i < systemManagers.size(); i++) {
			if (systemManagers.get(i).getMail().equals(mail)
					&& systemManagers.get(i).getMail().equals(password))
				return systemManagers.get(i);
		}
		return null;
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}
}
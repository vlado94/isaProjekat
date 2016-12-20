package app.manager.system;

import java.util.List;

import app.manager.system.SystemManager;

public interface SystemManagerService {
	List<SystemManager> findAll();

	SystemManager save(SystemManager systemManager);

	SystemManager findOne(Long id);

	SystemManager findOne(String mail, String password);

	void delete(Long id);
}

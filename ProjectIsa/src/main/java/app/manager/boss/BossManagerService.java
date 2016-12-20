package app.manager.boss;

import java.util.List;

public interface BossManagerService {

	List <BossManager> findAll();
	
	BossManager save(BossManager guest);
	
	BossManager findOne(String mail,String password);
}
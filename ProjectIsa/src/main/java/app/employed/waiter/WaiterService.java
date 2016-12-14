package app.employed.waiter;

import java.util.List;

public interface WaiterService {
	//boolean check();
	
	List<Waiter> findAll();
	
	Waiter save(Waiter waiter);
	
	Waiter findOne(Long id);
	
	void delete(Long id);
}

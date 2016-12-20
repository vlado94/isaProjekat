package app.employed.cook;

import java.util.List;

public interface CookService {

	List<Cook> findAll();
	
	Cook save(Cook cook);
	
	Cook findOne(Long id);
	
	void delete(Long id);
}

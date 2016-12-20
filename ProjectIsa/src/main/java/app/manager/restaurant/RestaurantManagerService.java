package app.manager.restaurant;

import java.util.List;

public interface RestaurantManagerService {
	
	List<RestaurantManager> findAll();
	
	RestaurantManager save(RestaurantManager restaurantManager);
	
	RestaurantManager findOne(Long id);
	
	RestaurantManager findOne(String mail,String password);
	
	void delete(Long id);

}

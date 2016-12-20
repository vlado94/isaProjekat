package app.guest;

import java.util.List;

import app.manager.restaurant.RestaurantManager;

public interface GuestService {
	List<Guest> findAll();

	Guest save(Guest guest);

	Guest findOne(Long id);

	Guest findOne(String mail,String password);
	
	void delete(Long id);
}

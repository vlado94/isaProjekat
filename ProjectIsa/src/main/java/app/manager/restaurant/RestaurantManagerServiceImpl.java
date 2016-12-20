package app.manager.restaurant;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

@Service
@Transactional
public class RestaurantManagerServiceImpl implements RestaurantManagerService{

	private final RestaurantManagerRepository repository;
	
	@Autowired
	public RestaurantManagerServiceImpl(final RestaurantManagerRepository repository){
		this.repository = repository;
	}
	
	@Override
	public List<RestaurantManager> findAll() {
		return Lists.newArrayList(repository.findAll());
	}

	@Override
	public RestaurantManager save(RestaurantManager restaurantManager) {
		return repository.save(restaurantManager);
	}

	@Override
	public RestaurantManager findOne(Long id) {
		return repository.findOne(id);
	}

	//ovo se kasnije na repo spusta
	@Override
	public RestaurantManager findOne(String mail, String password) {
		List<RestaurantManager> restaurantManagers = (List<RestaurantManager>) repository.findAll();
		for(int i = 0; i <restaurantManagers.size();i++) {
			if(restaurantManagers.get(i).getMail().equals(mail) && restaurantManagers.get(i).getMail().equals(password))
				return restaurantManagers.get(i);
		}
		return null;
	}
	
	@Override
	public void delete(Long id) {
		repository.delete(id);		
	}
}
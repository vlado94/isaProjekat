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
		// TODO Auto-generated method stub
		return Lists.newArrayList(repository.findAll());
	}

	@Override
	public RestaurantManager save(RestaurantManager restaurantManager) {
		// TODO Auto-generated method stub
		return repository.save(restaurantManager);
	}

	@Override
	public RestaurantManager findOne(Long id) {
		// TODO Auto-generated method stub
		return repository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);		
	}

}

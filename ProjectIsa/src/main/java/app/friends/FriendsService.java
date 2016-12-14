package app.friends;

import java.util.List;

public interface FriendsService {
	void save(Friends friends);
	
	List<Friends> findAll();

}

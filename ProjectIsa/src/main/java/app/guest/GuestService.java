package app.guest;

import java.util.List;

public interface GuestService {
	List<Guest> findAll();

	Guest save(Guest guest);

	Guest findOne(Long id);

	void delete(Long id);
}

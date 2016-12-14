package app.guest;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

@Service
@Transactional
public class GuestServiceImpl implements GuestService {
	private final GuestRepository repository;

	@Autowired
	public GuestServiceImpl(final GuestRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<Guest> findAll() {
		return Lists.newArrayList(repository.findAll());
	}

	@Override
	public Guest save(Guest guest) {
		return repository.save(guest);
	}

	@Override
	public Guest findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}
}

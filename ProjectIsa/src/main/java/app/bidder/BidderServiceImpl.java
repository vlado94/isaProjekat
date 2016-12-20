package app.bidder;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

@Service
@Transactional
public class BidderServiceImpl implements BidderService {
	private final BidderRepository repository;

	@Autowired
	public BidderServiceImpl(final BidderRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<Bidder> findAll() {
		return Lists.newArrayList(repository.findAll());
	}

	@Override
	public Bidder save(Bidder bidder) {
		return repository.save(bidder);
	}

	@Override
	public Bidder findOne(Long id) {
		return repository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}
}

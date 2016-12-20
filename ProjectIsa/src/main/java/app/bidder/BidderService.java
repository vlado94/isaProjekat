package app.bidder;

import java.util.List;

public interface BidderService {
	List<Bidder> findAll();

	Bidder save(Bidder bidder);

	Bidder findOne(Long id);

	void delete(Long id);
}

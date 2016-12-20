package app.employed.bartender;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;


public interface BartenderService {

	List<Bartender> findAll();

	Bartender save(Bartender bartender);

	Bartender findOne(Long id);

	void delete(Long id);

}

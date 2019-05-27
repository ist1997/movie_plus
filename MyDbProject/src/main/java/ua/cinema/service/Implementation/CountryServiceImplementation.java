package ua.cinema.service.Implementation;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ua.cinema.dao.CountryDao;
import ua.cinema.entity.Country;
import ua.cinema.filter.SimpleFilter;
import ua.cinema.service.CountryService;
import ua.cinema.service.FileWriter.Folder;
import ua.cinema.service.FileWriter;
import ua.cinema.specification.CountrySpecification;

@Service
public class CountryServiceImplementation implements CountryService {
	
	@Autowired
	private CountryDao countryDao;
	
	@Autowired
	private FileWriter fileWriter;

	public List<Country> findAll() {
		return countryDao.findAll();
	}

	public void delete(int id) {
		countryDao.delete(id);
		
	}

	public Country findOne(int id) {
		return countryDao.findOne(id);
	}

	public void save(Country entity) {
		
		MultipartFile file = entity.getFile();
		entity = countryDao.saveAndFlush(entity);
		 if(fileWriter.write(Folder.COUNTRY, file, entity.getId())){
			 entity.setVersion(entity.getVersion()+1);
			 countryDao.save(entity);
		 	}
		
	}

	@Override
	public Country findByName(String name) {
		
		return countryDao.findByName(name);
		
	}
	@Override
	
	public Page<Country> findAll(Pageable pageable, SimpleFilter filter) {

		return countryDao.findAll(new CountrySpecification(filter),pageable);
	}


	

}

package ua.cinema.service.Implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ua.cinema.dao.ProducerDao;
import ua.cinema.entity.Country;
import ua.cinema.entity.Producer;
import ua.cinema.filter.ActorAndProducerFilter;
import ua.cinema.form.ProducerForm;
import ua.cinema.service.FileWriter;
import ua.cinema.service.ProducerService;
import ua.cinema.service.FileWriter.Folder;
import ua.cinema.specification.ProducerSpecification;

@Service
public class ProducerServiceImplementation implements ProducerService {
	@Autowired
	private ProducerDao producerDao;
	
	@Autowired
	private FileWriter fileWriter;
	

	public List<Producer> findAll() {
		return producerDao.findAll();
	}

	public void delete(int id) {
		producerDao.delete(id);
	}

	public Producer findOne(int id) {
		return producerDao.findOne(id);
	}

	@Override
	public void save(ProducerForm form) {
		Producer entity = new Producer();
		entity.setName(form.getName());
		entity.setAge(Integer.valueOf(form.getAge()));
		entity.setNationality(form.getNationality());
		entity.setId(form.getId());
		entity.setFile(form.getFile());
		MultipartFile file = entity.getFile();
		entity = producerDao.saveAndFlush(entity);
		 if(fileWriter.write(Folder.PRODUCER, file, entity.getId())){
			 entity.setVersion(entity.getVersion()+1);
			 producerDao.saveAndFlush(entity);
		 	}
		 	
		
	}

	@Override
	public ProducerForm findForm(int id) {
		ProducerForm form = new ProducerForm();
		Producer entity = producerDao.findOne(id);
		form.setName(entity.getName());
		form.setAge(String.valueOf(entity.getAge()));
		form.setNationality(entity.getNationality());
		form.setId(entity.getId());		
		return form;
	}

	@Override
	public Producer findUnique(String name, String age, Country country) {
		return producerDao.findUnique(name, Integer.valueOf(age), country.getId());
	}
	
	@Override
	public Page<Producer> findAll(Pageable pageable, ActorAndProducerFilter filter) {
		
		return producerDao.findAll(new ProducerSpecification(filter), pageable);
	}

}

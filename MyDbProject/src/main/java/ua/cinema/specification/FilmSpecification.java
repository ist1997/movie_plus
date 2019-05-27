package ua.cinema.specification;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.cinema.entity.Film;
import ua.cinema.filter.FilmFilter;

public class FilmSpecification implements Specification<Film>{
	
	private final FilmFilter filter;
	
	private final List<Predicate> predicates = new ArrayList<>();
	
	private final static Pattern PATTERN = Pattern.compile("^[0-9]+$");
	
	public FilmSpecification(FilmFilter filter) {
		
		this.filter = filter;
		if(PATTERN.matcher(filter.getMax()).matches()){
			filter.setMaxValue(new Integer(filter.getMax()));
		}
		if(PATTERN.matcher(filter.getMin()).matches()){
			filter.setMinValue(new Integer(filter.getMin()));
		}
	}
	
	private void filterByGenre(Root<Film> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(!filter.getGenresId().isEmpty()){
			predicates.add(root.get("ganre").in(filter.getGenresId()));
		}
	}
	
	private void filterByName(Root<Film> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(!filter.getSearch().isEmpty()){
			predicates.add(cb.like(cb.lower(root.get("name")), filter.getSearch().toLowerCase()+"%"));
		}
	}
	
	private void filterByPrice(Root<Film> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(filter.getMaxValue()!=null){
			predicates.add(cb.le(root.get("price"), filter.getMaxValue()));
		}
		if(filter.getMinValue()!=null){
			predicates.add(cb.ge(root.get("price"), filter.getMinValue()));
		}
	}
	
	private void fetch(Root<Film> root, CriteriaQuery<?> query){
		if(!query.getResultType().equals(Long.class)){
			query.distinct(true);
			root.fetch("ganre");
			root.fetch("producer");
			root.fetch("nationality");
			
		
		}
	}

	@Override
	public Predicate toPredicate(Root<Film> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		fetch(root, query);
		filterByGenre(root, query, cb);
		filterByName(root, query, cb);
		filterByPrice(root, query, cb);
		if(predicates.isEmpty())return null;
		Predicate[] array = new Predicate[predicates.size()];
		array = predicates.toArray(array);
		return cb.and(array);
	}
	
	
	

}

package ua.cinema.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.cinema.entity.Actor;
import ua.cinema.filter.ActorAndProducerFilter;


public class ActorSpecification implements Specification<Actor>{
	
	private final ActorAndProducerFilter filter;
	
	private final List<Predicate> predicates = new ArrayList<>();

	public ActorSpecification(ActorAndProducerFilter filter) {
		this.filter = filter;
	}
	
	private void filterByCountry(Root<Actor> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(!filter.getCountriesId().isEmpty()){
			predicates.add(root.get("nationality").in(filter.getCountriesId()));
		}
	}
	private void filterByName(Root<Actor> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(!filter.getSearch().isEmpty()){
			predicates.add(cb.like(cb.lower(root.get("name")), filter.getSearch().toLowerCase()+"%"));
		}
	}
	
	private void fetch(Root<Actor> root, CriteriaQuery<?> query){
		if(!query.getResultType().equals(Long.class)){
			query.distinct(true);
			root.fetch("nationality");
		}
	}
	

	@Override
	public Predicate toPredicate(Root<Actor> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		
		fetch(root,query);
		filterByName(root, query, cb);
		filterByCountry(root, query, cb);
		if(predicates.isEmpty())return null;
		Predicate[] array = new Predicate[predicates.size()];
		array = predicates.toArray(array);
		return cb.and(array);
	}
	

}

package ua.cinema.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.cinema.entity.Country;
import ua.cinema.filter.SimpleFilter;

public class CountrySpecification implements Specification<Country> {
	
	private final SimpleFilter filter;

	public CountrySpecification(SimpleFilter filter) {
		this.filter = filter;
	}

	@Override
	public Predicate toPredicate(Root<Country> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if(filter.getSearch().isEmpty()){
			return null;
		}
		return cb.like(cb.lower(root.get("name")), filter.getSearch().toLowerCase()+"%");
	}
	
	
	

}

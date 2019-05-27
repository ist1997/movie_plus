package ua.cinema.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ua.cinema.entity.Country;

public interface CountryDao extends JpaRepository<Country, Integer>, JpaSpecificationExecutor<Country> {
	Country findByName(String name);
}

package ua.cinema.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ua.cinema.entity.Genre;

public interface GenreDao extends JpaRepository<Genre, Integer>,JpaSpecificationExecutor<Genre> {
	Genre findByType(String type);
}

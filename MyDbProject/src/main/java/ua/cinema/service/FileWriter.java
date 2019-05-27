package ua.cinema.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileWriter {
	
	enum Folder{
		COUNTRY,
		ACTOR,
		PRODUCER,
		FILM
		
	}
	
	boolean write(Folder folder, MultipartFile file, Integer id);

}

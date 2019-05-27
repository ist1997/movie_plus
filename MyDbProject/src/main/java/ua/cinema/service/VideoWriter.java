package ua.cinema.service;

import org.springframework.web.multipart.MultipartFile;



public interface VideoWriter {
	
	enum FolderVideo{
		FILM
		
	}
	
	boolean write(FolderVideo folder, MultipartFile file, Integer id);

}

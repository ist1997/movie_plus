package ua.cinema.service.Implementation;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ua.cinema.service.VideoWriter;

@Service
public class VideoWriterImplementation implements VideoWriter {

	@Override
	public boolean write(FolderVideo folder, MultipartFile file, Integer id) {
		if(file!=null&&!file.isEmpty()){
			File pathToHome = new File(System.getProperty("catalina.home"));
			File pathToFolder = new File(pathToHome, "videos" + File.separator + folder.name().toLowerCase());
			if(!pathToFolder.exists()){
				pathToFolder.mkdirs();
			}
			try {
			 	File pathToFile = new File(pathToFolder, String.valueOf(id)+".mp4");
				file.transferTo(pathToFile);
			 	return true;
				} catch (IOException e) {
			 		e.printStackTrace();
			 	}
			}
			return false;
	}
}

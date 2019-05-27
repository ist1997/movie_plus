package ua.cinema.service.Implementation;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ua.cinema.service.FileWriter;
import ua.cinema.util.Engine;
@Service
public class FileWriterImplementation implements FileWriter {

	@Override
	public boolean write(Folder folder, MultipartFile file, Integer id) {
		if(file!=null&&!file.isEmpty()){
			File pathToHome = new File(System.getProperty("catalina.home"));
			File pathToFolder = new File(pathToHome, "images" + File.separator + folder.name().toLowerCase());
			if(!pathToFolder.exists()){
				pathToFolder.mkdirs();
			}
			try {
				InputStream in = new ByteArrayInputStream(file.getBytes());
			 	BufferedImage old = ImageIO.read(in);
			 	Engine engine = new Engine(old);
			 	BufferedImage present = engine.crop();
			 	File pathToFile = new File(pathToFolder, String.valueOf(id)+".jpg");
			 	ImageIO.write(present, "jpg", pathToFile);
			 	return true;
				} catch (IOException e) {
			 		e.printStackTrace();
			 	}
			}
			return false;
	}
}



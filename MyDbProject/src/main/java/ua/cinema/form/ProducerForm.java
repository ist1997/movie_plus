package ua.cinema.form;

import org.springframework.web.multipart.MultipartFile;

import ua.cinema.entity.Country;

public class ProducerForm {

		private int id;
		private String name;
		private String age;
		private Country nationality;
		private int version;
		private MultipartFile file;

		public MultipartFile getFile() {
			return file;
		}
		public void setFile(MultipartFile file) {
			this.file = file;
		}
		public int getVersion() {
			return version;
		}
		public void setVersion(int version) {
			this.version = version;
		}
		
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getAge() {
			return age;
		}
		public void setAge(String age) {
			this.age = age;
		}
		public Country getNationality() {
			return nationality;
		}
		public void setNationality(Country nationality) {
			this.nationality = nationality;
		}
		
		


}

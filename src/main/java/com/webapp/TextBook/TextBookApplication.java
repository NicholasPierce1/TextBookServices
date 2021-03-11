package com.webapp.TextBook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TextBookApplication {

	public static void main(String[] args) {
		SpringApplication.run(TextBookApplication.class, args);
	}

	/*@Bean
	public DataSource getDataSource(){
		return DataSourceBuilder.create()
				.url("jdbc:oracle:thin@localhost:1521/orcl")
				.username("textbookservices")
				.password("Textbookpassword2021")
				.build();
	}*/

}

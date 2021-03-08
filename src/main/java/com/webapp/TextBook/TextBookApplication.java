package com.webapp.TextBook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import javax.sql.DataSource;

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

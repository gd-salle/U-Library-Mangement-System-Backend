package com.university.librarymanagementsystem;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.university.librarymanagementsystem.config.GoogleBooksProperties;

@SpringBootApplication
@EnableConfigurationProperties(GoogleBooksProperties.class)
public class LibraryManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementSystemApplication.class, args);

		TimeZone.setDefault(TimeZone.getTimeZone("GMT+08:00"));
	}

}

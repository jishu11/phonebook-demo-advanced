package com.hackajob.phonebook.phonebookdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = PhonebookDemoApplication.class)
public class PhonebookDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhonebookDemoApplication.class, args);
	}
}

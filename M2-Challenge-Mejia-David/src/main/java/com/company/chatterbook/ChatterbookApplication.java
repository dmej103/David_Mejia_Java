package com.company.chatterbook;

import nonapi.io.github.classgraph.json.JSONUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.company.chatterbook.models.User;
import com.company.chatterbook.models.ChatterPost;

@SpringBootApplication
public class ChatterbookApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatterbookApplication.class, args);
	}

}

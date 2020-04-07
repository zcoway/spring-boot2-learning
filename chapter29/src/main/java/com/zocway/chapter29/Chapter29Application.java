package com.zocway.chapter29;

import org.springframework.boot.SpringApplication;


import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Chapter29Application {


	public static void main(String[] args) {
		
		// start embedded zookeeper server
		new com.zocway.chapter29.EmbeddedZooKeeper(2181, false).start();

		
		SpringApplication.run(Chapter29Application.class, args);
	}
	
}

package com.seol.HW3SpringBoard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Hw3SpringBoardApplication {
	public static void main(String[] args) {
		try { SpringApplication.run(Hw3SpringBoardApplication.class, args); }
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}

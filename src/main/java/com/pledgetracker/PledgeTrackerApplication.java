package com.pledgetracker;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PledgeTrackerApplication {

	public static void main(String[] args) {
		// .env 설정
		Dotenv dotenv = Dotenv.configure()
				.ignoreIfMissing()
				.load();

		// 환경변수를 시스템 프로퍼티로 설정
		System.setProperty("DB_URL", dotenv.get("DB_URL"));
		System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
		System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));

		SpringApplication.run(PledgeTrackerApplication.class, args);
	}
}

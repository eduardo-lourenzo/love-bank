package br.edu.zup.love_bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoveBankApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoveBankApplication.class, args);

		System.out.print(
				"\n" +
						"==========================================\n" +
						"The Love Bank is running...\n" +
						"==========================================\n" +
						"\n"
		);
    }

}

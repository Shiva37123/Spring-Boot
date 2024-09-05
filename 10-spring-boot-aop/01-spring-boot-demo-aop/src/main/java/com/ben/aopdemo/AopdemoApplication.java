package com.ben.aopdemo;

import com.ben.aopdemo.dao.AccountDAO;
import com.ben.aopdemo.dao.MembershipDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AopdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopdemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AccountDAO theAccountDAO, MembershipDAO membershipDAO){
		return runner -> {
			demoTheBeforAdvice(theAccountDAO, membershipDAO);
		};
	}

	private void demoTheBeforAdvice(AccountDAO theAccountDAO, MembershipDAO membershipDAO) {

		// call the account business method
		theAccountDAO.addAccount(new Account(), true);
		theAccountDAO.doWork();

		// call the membership business method
		System.out.println("calling Membership addAccount");
		membershipDAO.addAccount();
		membershipDAO.goToSleep();
	}

}

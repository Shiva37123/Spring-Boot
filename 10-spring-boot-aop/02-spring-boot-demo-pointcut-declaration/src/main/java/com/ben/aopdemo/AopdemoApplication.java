package com.ben.aopdemo;

import com.ben.aopdemo.dao.AccountDAO;
import com.ben.aopdemo.dao.MembershipDAO;
import com.ben.aopdemo.service.TrafficFortuneService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AopdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopdemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AccountDAO theAccountDAO, MembershipDAO membershipDAO,
											   TrafficFortuneService trafficFortuneService){
		return runner -> {
			// demoTheBeforAdvice(theAccountDAO, membershipDAO);

			// demoTheAfterReturningAdvice(theAccountDAO);

			// demoTheAfterThrowAdvice(theAccountDAO);
			
			// demoTheAfterAdvice(theAccountDAO);

			// demoTheAroundAdvice(trafficFortuneService);

			// demoTheAdviceHandleException(trafficFortuneService);

			demoTheAroundAdviceRethrowException(trafficFortuneService);
		};
	}

	private void demoTheAroundAdviceRethrowException(TrafficFortuneService trafficFortuneService) {
		System.out.println("\nMain Program: demoTheAroundAdviceRethrowException");

		System.out.println("Calling getFortune()");

		boolean tripWire = true;
		String data = trafficFortuneService.getFortune(tripWire);

		System.out.println("\nMy fortune is: "+ data);

		System.out.println("Finished");
	}

	private void demoTheAdviceHandleException(TrafficFortuneService trafficFortuneService) {
		System.out.println("\nMain Program: demoTheAdviceHandleException");

		System.out.println("Calling getFortune()");

		boolean tripWire = true;
		String data = trafficFortuneService.getFortune(tripWire);

		System.out.println("\nMy fortune is: "+ data);

		System.out.println("Finished");
	}

	private void demoTheAroundAdvice(TrafficFortuneService trafficFortuneService) {

		System.out.println("\nMain Program: demoTheAroundAdvice");

		System.out.println("Calling getFortune()");

		String data = trafficFortuneService.getFortune();

		System.out.println("\nMy fortune is: "+ data);

		System.out.println("Finished");

	}

	private void demoTheAfterAdvice(AccountDAO theAccountDAO) {
		List<Account> theAccounts = null;
		try {

			//add a boolean flag to simulate exception
			boolean tripWire = false;
			theAccounts = theAccountDAO.findAccounts(tripWire);
		}

		catch (Exception exc){
			System.out.println("\n\nMain Program: .. . cought exception " + exc);
		}

		//display the account

		System.out.println("\nMain Program: demoTheAfterReturningAdvice");
		System.out.println("------");
		System.out.println(theAccounts+"\n");
	}

	private void demoTheAfterThrowAdvice(AccountDAO theAccountDAO) {


		List<Account> theAccounts = null;
		try {

			//add a boolean flag to simulate exception
			boolean tripWire = true;
			theAccounts = theAccountDAO.findAccounts(tripWire);
		}

		catch (Exception exc){
			System.out.println("\n\nMain Program: .. . cought exception " + exc);
		}

		//display the account

		System.out.println("\nMain Program: demoTheAfterReturningAdvice");
		System.out.println("------");
		System.out.println(theAccounts+"\n");
	}

	private void demoTheAfterReturningAdvice(AccountDAO theAccountDAO) {

		List<Account> theAccounts = theAccountDAO.findAccounts();

		//display the account

		System.out.println("\nMain Program: demoTheAfterReturningAdvice");
		System.out.println("------");
		System.out.println(theAccounts+"\n");
	}

	private void demoTheBeforAdvice(AccountDAO theAccountDAO, MembershipDAO membershipDAO) {

		// call the account business method
		Account account = new Account();
		account.setName("Madhu");
		account.setLevel("Platinum");
		theAccountDAO.addAccount(account, true);
		theAccountDAO.doWork();

		// call the accountDao getter/setter method

		theAccountDAO.setName("foobar");
		theAccountDAO.setServiceCode("silver");

		theAccountDAO.getName();
		theAccountDAO.getServiceCode();

		// call the membership business method
		System.out.println("calling Membership addAccount");
		membershipDAO.addAccount();
		membershipDAO.goToSleep();
	}

}

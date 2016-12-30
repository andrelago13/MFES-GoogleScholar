package googlescholar.cli;

import java.util.Scanner;

import googlescholar.model.Scholar;

public class GoogleScholar {
	private Scholar scholar;

	public GoogleScholar() {
		this.scholar = new Scholar();
	}

	public void start() {
		if (scholar.isLoggedIn()) {
			userMenu();
		} else {
			mainMenu();
		}
	}
	
	public void mainMenu() {
		System.out.println("1 - Register");
		System.out.println("2 - Login");
		System.out.println();
		System.out.println("0 - Exit");
		Scanner s = new Scanner(System.in);
		int choice = s.nextInt();
		switch (choice) {
		case 1: register(); break;
		case 2: login(); break;
		case 0: break;
		default: mainMenu();
		}
	}
	
	public void register() {
		Scanner s = new Scanner(System.in);
		System.out.println("Email: ");
		String email = s.nextLine();
		System.out.println("Password: ");
		String password = s.nextLine();
		
		scholar.register(email, password);
		
		mainMenu();
	}
	
	public void login() {
		Scanner s = new Scanner(System.in);
		System.out.println("Email: ");
		String email = s.nextLine();
		System.out.println("Password: ");
		String password = s.nextLine();

		scholar.login(email, password);
		
		if (!scholar.isLoggedIn()) {
			System.out.println("Invalid login.");
			mainMenu();
		} else {
			userMenu();
		}
	}
	
	public void userMenu() {
		// TODO
	}
}

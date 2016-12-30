package googlescholar.cli;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import googlescholar.model.Paper;
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
	
	private void mainMenu() {
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
	
	private void register() {
		Scanner s = new Scanner(System.in);
		System.out.println("Email: ");
		String email = s.nextLine();
		System.out.println("Password: ");
		String password = s.nextLine();
		
		scholar.register(email, password);
		
		mainMenu();
	}
	
	private void login() {
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
	
	private void userMenu() {
		System.out.println("Hello, " + scholar.getCurrentUser().getEmail());
		System.out.println();
		System.out.println("1 - Change password");
		System.out.println("2 - Search paper by author");
		System.out.println("3 - Add paper");
		System.out.println();
		System.out.println("0 - Logout");
		
		Scanner s = new Scanner(System.in);
		int choice = s.nextInt();
		switch (choice) {
		case 1: changePassword(); break;
		case 2: searchPaperByAuthor(); break;
		case 3: addPaper(); break;
		case 0: logout(); break;
		default: userMenu();
		}
	}
	
	private void logout() {
		scholar.logout();
		mainMenu();
	}
	
	private void changePassword() {
		System.out.print("New password: ");
		Scanner s = new Scanner(System.in);
		String password = s.nextLine();
		
		scholar.getCurrentUser().changePassword(password);
		
		System.out.println("Password successfully changed.");
		
		userMenu();
	}
	
	private void searchPaperByAuthor() {
		System.out.println("Insert the author name:");
		Scanner s = new Scanner(System.in);
		String author = s.nextLine();
		
		Set result = scholar.getPapersFromAuthorName(author);
		
		List<Paper> list = new ArrayList<Paper>(result);
		
		int choice = -1;
		
		do {
		for (int i = 0; i < list.size(); i++) {
			System.out.println((i + 1) + " - " + list.get(i));
		}
		System.out.println();
		System.out.println("0 - Back");
		
		choice = s.nextInt();
		if (choice > 0 && choice <= list.size())
			viewPaper(list.get(choice));
		} while (choice != 0);
		userMenu();
	}
	
	private void viewPaper(Paper paper) {
		System.out.println("Abstract: " + paper.abstract_);
		System.out.println("Publication date: " + paper.publicationDate);
		System.out.println("DOI: " + paper.DOI);
		System.out.println("Authors: " + paper.getAuthors());
		System.out.println("Citations: " + paper.getCitations());
		System.out.println("Related to: " + paper.getRelatedPapers());
		System.out.println();
		System.out.println("0 - Back");
		
		Scanner s = new Scanner(System.in);
		int choice = s.nextInt();
		if (choice == 0)
			return;
		else
			viewPaper(paper);
	}
	
	private void addPaper() {
		// TODO
	}
}

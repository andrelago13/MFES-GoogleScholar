package googlescholar.cli;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.overture.codegen.runtime.VDMSet;

import googlescholar.model.Paper;
import googlescholar.model.Scholar;
import googlescholar.model.User;

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
		System.out.println("3 - Search paper by author");
		System.out.println("4 - Search user by email");
		System.out.println();
		System.out.println("0 - Exit");
		Scanner s = new Scanner(System.in);
		int choice = s.nextInt();
		switch (choice) {
		case 1: register(); break;
		case 2: login(); break;
		case 3: searchPaperByAuthor(); mainMenu(); break;
		case 4: searchUserByEmail(); mainMenu(); break;
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
		User user = scholar.getCurrentUser();
		System.out.println("Hello, " + user.getEmail());
		System.out.println("h-index: " + user.getHIndex(user.getPapers()));
		System.out.println("i10-index: " + user.getI10Index(user.getPapers()));
		System.out.println();
		System.out.println("1 - Change password");
		System.out.println("2 - My papers");
		System.out.println("3 - Search paper by author");
		System.out.println("4 - Search user by email");
		System.out.println("5 - Add paper");
		System.out.println();
		System.out.println("0 - Logout");

		Scanner s = new Scanner(System.in);
		int choice = s.nextInt();
		switch (choice) {
		case 1: changePassword(); break;
		case 2: papersByUser(user); userMenu(); break;
		case 3: searchPaperByAuthor(); userMenu(); break;
		case 4: searchUserByEmail(); userMenu(); break;
		case 5: addPaper(); break;
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

	private void papersByUser(User user) {
		VDMSet papers = user.getPapers();

		List<Paper> list = new ArrayList<Paper>(papers);

		Scanner s = new Scanner(System.in);
		int choice = -1;

		do {
			for (int i = 0; i < list.size(); i++) {
				System.out.println((i + 1) + " - " + list.get(i).getTitle());
			}
			System.out.println();
			System.out.println("0 - Back");

			choice = s.nextInt();
			if (choice > 0 && choice <= list.size())
				viewPaper(list.get(choice - 1));
		} while (choice != 0);
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
				System.out.println((i + 1) + " - " + list.get(i).getTitle());
			}
			System.out.println();
			System.out.println("0 - Back");

			choice = s.nextInt();
			if (choice > 0 && choice <= list.size())
				viewPaper(list.get(choice - 1));
		} while (choice != 0);
	}

	private void viewPaper(Paper paper) {
		System.out.println("Title: " + paper.getTitle());
		System.out.println("Abstract: " + paper.abstract_);
		System.out.println("Publication date: " + paper.publicationDate);
		System.out.println("DOI: " + paper.DOI);
		System.out.println("Authors: " + paper.getAuthors());
		System.out.println("Citations: " + paper.getCitations());
		System.out.println("Related to: " + paper.getRelatedPapers());
		System.out.println();

		if (scholar.getCurrentUser().getPapers().contains(paper)) { // Can edit paper?
			System.out.println("1 - Edit title");
			System.out.println("2 - Edit abstract");
			System.out.println("3 - Edit publication date");
			System.out.println("4 - Edit DOI");
		} else {
			System.out.println("1 - Add paper to my list");
		}
		System.out.println();
		System.out.println("0 - Back");

		Scanner s = new Scanner(System.in);
		int choice = s.nextInt();
		s.nextLine(); // Delete endline
		if (scholar.getCurrentUser().getPapers().contains(paper)) { // Can edit paper?
			switch (choice) {
			case 1: System.out.print("Title: "); paper.changeTitle(s.nextLine()); viewPaper(paper); break;
			case 2: System.out.print("Abstract: "); paper.changeAbstract(s.nextLine()); viewPaper(paper); break;
			case 3: System.out.print("Publication date: "); paper.changePublicationDate(s.nextInt()); viewPaper(paper); break;
			case 4: System.out.print("DOI : "); paper.changeDOI(s.nextLine()); viewPaper(paper); break;
			case 0: return;
			default: System.out.println("Invalid option."); viewPaper(paper);
			}
		} else {
			switch (choice) {
			case 1: scholar.getCurrentUser().addPaper(paper.cg_clone()); System.out.println("Paper successfully added."); viewPaper(paper); break;
			case 0: return;
			default: System.out.println("Invalid option."); viewPaper(paper);
			}
		}
	}

	private void addPaper() {
		Scanner s = new Scanner(System.in);
		System.out.print("Title: ");
		String title = s.nextLine();
		System.out.print("Abstract: ");
		String abstract_ = s.next();
		System.out.print("Publication date: ");
		int publicationDate = s.nextInt();
		s.nextLine();
		System.out.print("DOI: ");
		String doi = s.nextLine();

		Paper paper = new Paper(abstract_, publicationDate, title, doi, new VDMSet());
		scholar.addPaper(paper);
		System.out.println("Paper successfully created.");
		viewPaper(paper);
		userMenu();
	}
	
	private void searchUserByEmail() {
		Scanner s = new Scanner(System.in);
		System.out.print("Email: ");
		String email = s.nextLine();
		User user = scholar.getUserByEmail(email);
		if (user == null) {
			System.out.println("User not found.");
		} else {
			showUser(user);
		}
	}
	
	private void showUser(User user) {
		System.out.println("Email: " + user.getEmail());
		System.out.println("h-index: " + user.getHIndex(user.getPapers()));
		System.out.println("i10-index: " + user.getI10Index(user.getPapers()));
		System.out.println();
		System.out.println("1 - List papers");
		System.out.println();
		System.out.println("0 - Back");
		
		Scanner s = new Scanner(System.in);
		int choice = s.nextInt();
		switch (choice) {
		case 1: papersByUser(user); showUser(user); break;
		case 0: return;
		default: System.out.println("Invalid option."); showUser(user);
		}
	}
}

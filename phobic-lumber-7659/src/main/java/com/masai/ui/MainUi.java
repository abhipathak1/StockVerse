package com.masai.ui;

import java.util.List;
import java.util.Scanner;

import com.masai.Dao.UserDAO;
import com.masai.Service.UserService;
import com.masai.dto.Stocks;
import com.masai.dto.User;

public class MainUi {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		UserDAO userDAO = new UserDAO();
		UserService userService = new UserService(userDAO);

//		StocksDao stocksDao = new StocksDao();
//		StockService stockService = new StockService(stocksDao);

		int choice = 0;

		do {
			System.out.println("--------------------Main-Menu-------------------");
			System.out.println("1 --> Admin Login");
			System.out.println("2 --> Customer Login");
			System.out.println("3 --> Customer Register");
			System.out.println("0 --> Exit");
			System.out.print("Please enter your preference: ");
			choice = Integer.parseInt(scanner.nextLine());

			switch (choice) {
			case 1:
				adminLogin(userService); 
				break;
			case 2:
				userLogin(userService);
				break;
			case 3:
				userSignup(userService);
				break;
			case 0:
				System.out.println("Exiting the application. Goodbye!");
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		} while (choice != 0);
	}

	public static void adminLogin(UserService userService) {
		String adminUsername = "admin";
		String adminPassword = "admin";

		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter admin username: ");
		String username = scanner.nextLine();

		System.out.print("Enter admin password: ");
		String password = scanner.nextLine();

		if (username.equals(adminUsername) && password.equals(adminPassword)) {
			showAdminOptions(userService); 
		} else {
			System.out.println("Invalid credentials. Access denied.");
		}
	}

	private static void showAdminOptions(UserService userService) {
		Scanner scanner = new Scanner(System.in);
		int choice;
		do {
			System.out.println("\n---- Admin Options ----");
			System.out.println("1. Add Stocks");
			System.out.println("2. Edit Stocks");
			System.out.println("3. View Stocks");
			System.out.println("4. Delete Stocks");
			System.out.println("5. Delete Users From Server");
			System.out.println("6. Logout");
			System.out.print("Enter your choice: ");
			choice = scanner.nextInt();

			switch (choice) {
			case 1:
				addStock(userService);
				break;
			case 2:
				editStock(userService);
				break;
			case 3:
				viewStocks(userService);
				break;
			case 4:
				deleteStock(userService);
				break;
			case 5:
				deleteUsers(userService);
				break;
			case 6:
				System.out.println("Admin logged out.");
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		} while (choice != 4);
	}

	private static void addStock(UserService userService) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("\n---- Add Stocks ----");
		System.out.print("Enter stock name: ");
		String stockName = scanner.nextLine();

		System.out.print("Enter stock price: ");
		double stockPrice = Double.parseDouble(scanner.nextLine());

		Stocks newStock = new Stocks();
		newStock.setStockName(stockName);
		newStock.setStockPrice(stockPrice);

		userService.addStock(newStock);

		System.out.println("Stock added successfully.");
	}

	private static void editStock(UserService userService) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("\n---- Edit Stock ----");
		System.out.print("Enter stock name to edit: ");
		String stockName = scanner.nextLine();

		Stocks stockToEdit = userService.getStockByStockName(stockName);

		if (stockToEdit != null) {
			System.out.println("\nCurrent Stock Details:");
			System.out.println("Stock Name: " + stockToEdit.getStockName());
			System.out.println("Stock Price: " + stockToEdit.getStockPrice());

			System.out.print("\nEnter new stock price (press Enter to keep current price): ");
			String newStockPriceStr = scanner.nextLine();
			if (!newStockPriceStr.isEmpty()) {
				double newStockPrice = Double.parseDouble(newStockPriceStr);
				stockToEdit.setStockPrice(newStockPrice);
			}

			userService.editStock(stockToEdit);
			System.out.println("Stock details updated successfully.");
		} else {
			System.out.println("Stock with name " + stockName + " not found.");
		}
	}

	private static void viewStocks(UserService userService) {
		System.out.println("\n---- View Stocks ----");

		List<Stocks> stocksList = userService.getAllStocks();

		if (stocksList.isEmpty()) {
			System.out.println("No stocks available.");
		} else {
			System.out.println("Available Stocks:");

			for (Stocks stock : stocksList) {
				System.out.println("Stock Name: " + stock.getStockName());
				System.out.println("Stock Price: " + stock.getStockPrice());
				System.out.println("----------------------");
			}
		}
	}

	private static void deleteStock(UserService userService) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("\n---- Delete Stock ----");
		System.out.print("Enter stock name to delete: ");
		String stockName = scanner.nextLine();

		Stocks stockToDelete = userService.getStockByStockName(stockName);

		if (stockToDelete != null) {
			System.out.println("\nCurrent Stock Details:");
			System.out.println("Stock Name: " + stockToDelete.getStockName());
			System.out.println("Stock Price: " + stockToDelete.getStockPrice());

			System.out.print("Are you sure you want to delete this stock? (yes/no): ");
			String confirmation = scanner.nextLine().toLowerCase();

			if (confirmation.equals("yes")) {
				userService.deleteStock(stockToDelete);
				System.out.println("Stock deleted successfully.");
			} else {
				System.out.println("Stock deletion canceled.");
			}
		} else {
			System.out.println("Stock with name " + stockName + " not found.");
		}
	}

	private static void deleteUsers(UserService userService) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("\n---- Delete Users From Server ----");
		System.out.print("Enter username of the user to delete: ");
		String username = scanner.nextLine();

		User userToDelete = userService.getUserByUsername(username);

		if (userToDelete != null) {
			System.out.println("\nCurrent User Details:");
			System.out.println("Name: " + userToDelete.getName());
			System.out.println("Username: " + userToDelete.getUsername());
			System.out.println("Wallet Balance: " + userToDelete.getWalletBalance());

			System.out.print("Are you sure you want to delete this user? (yes/no): ");
			String confirmation = scanner.nextLine().toLowerCase();

			if (confirmation.equals("yes")) {
				userService.deleteUser(userToDelete);
				System.out.println("User deleted successfully.");
			} else {
				System.out.println("User deletion canceled.");
			}
		} else {
			System.out.println("User with username " + username + " not found.");
		}
	}

//	---------------------Users--------------------------------
//	---------------------Users--------------------------------

	private static void userSignup(UserService userService) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter user name: ");
		String name = scanner.nextLine();

		System.out.print("Enter username: ");
		String username = scanner.nextLine();

		System.out.print("Enter password: ");
		String password = scanner.nextLine();

		System.out.print("Enter initial wallet balance: ");
		double walletBalance = scanner.nextDouble();

		User user = new User();
		user.setName(name);
		user.setUsername(username);
		user.setPassword(password);
		user.setWalletBalance(walletBalance);

		userService.addUser(user);
		System.out.println("User signup successful. You can now log in.");
	}

	private static void userLogin(UserService userService) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter username: ");
		String username = scanner.nextLine();

		System.out.print("Enter password: ");
		String password = scanner.nextLine();

		User user = userService.getUserByUsername(username);

		if (user != null && user.getPassword().equals(password)) {
			showUserOptions(user);
		} else {
			System.out.println("Invalid credentials. Access denied.");
		}
	}

	private static void showUserOptions(User user) {
		Scanner scanner = new Scanner(System.in);
		int choice;
		do {
			System.out.println("\n---- User Options ----");
			System.out.println("1. View Portfolio");
			System.out.println("2. Buy Stocks");
			System.out.println("3. Logout");
			System.out.print("Enter your choice: ");
			choice = scanner.nextInt();

			switch (choice) {
			case 1:
				viewPortfolio(user);
				break;
			case 2:
				buyStocks(user);
				break;
			case 3:
				System.out.println("User logged out.");
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		} while (choice != 3);
	}

	private static void viewPortfolio(User user) {
		// Implement logic to view user portfolio
	}

	private static void buyStocks(User user) {
		// Implement logic for user to buy stocks
	}
}

package com.masai.ui;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.masai.Dao.UserDAO;
import com.masai.Dao.UserDAOImpl;
import com.masai.Dao.UserPortfolioDAO;
import com.masai.Dao.UserPortfolioDAOImpl;
import com.masai.Service.UserService;
import com.masai.dto.Stocks;
import com.masai.dto.User;
import com.masai.dto.UserPortfolio;

public class MainUi {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		UserDAO userDAO = new UserDAOImpl();
		UserPortfolioDAO userPortfolioDAO = new UserPortfolioDAOImpl();
		UserService userService = new UserService(userDAO, userPortfolioDAO);
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
			System.out.println();
			System.out.println("Admin successfully Logged in.");
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
			System.out.println("5. View All Users");
			System.out.println("6. Delete Users From Server");
			System.out.println("7. Logout");
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
				viewAllUsers(userService);
				break;
			case 6:
				deleteUsers(userService);
				break;
			case 7:
				System.out.println("Admin logged out.");
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		} while (choice != 7);
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

	private static void viewAllUsers(UserService userService) {
		List<User> users = userService.viewAllUsers();
		if (users.isEmpty()) {
			System.out.println("No users found on the server.");
		} else {
			System.out.println("\n---- All Users ----");
			for (User user : users) {
				System.out.println("Name: " + user.getName());
				System.out.println("Username: " + user.getUsername());
				System.out.println("Wallet Balance: " + user.getWalletBalance());
				System.out.println("----------------------");
			}
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
			showUserOptions(userService, user);
		} else {
			System.out.println("Invalid credentials. Access denied.");
		}
	}

	private static void showUserOptions(UserService userService, User user) {
		Scanner scanner = new Scanner(System.in);
		int choice;
		do {
			System.out.println("\n---- User Options ----");
			System.out.println("1. View Portfolio");
			System.out.println("2. View Available Stocks");
			System.out.println("3. Buy Stocks");
			System.out.println("4. Add More Balance to Wallet");
			System.out.println("5. Change Password");
			System.out.println("6. Logout");
			System.out.print("Enter your choice: ");
			choice = scanner.nextInt();

			switch (choice) {
			case 1:
				viewPortfolio(user, userService);
				break;
			case 2:
				viewAvailableStocks(userService);
				break;
			case 3:
				buyStocks(user, userService);
				break;
			case 4:
				addBalanceToWallet(user, userService);
				break;
			case 5:
				changePassword(user, userService);
				break;
			case 6:
				System.out.println("User logged out.");
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		} while (choice != 6);
	}

	private static void viewPortfolio(User user, UserService userService) {
		List<UserPortfolio> userPortfolio = userService.getUserPortfolioByUser(user);
		if (userPortfolio.isEmpty()) {
			System.out.println("Your portfolio is empty. Buy stocks to add to your portfolio.");
		} else {
			System.out.println("\n---- User Portfolio ----");
			for (UserPortfolio portfolio : userPortfolio) {
				System.out.println("Stock Name: " + portfolio.getStock().getStockName());
				System.out.println("Stock Price: " + portfolio.getStock().getStockPrice());
				System.out.println("Quantity: " + portfolio.getQuantity());
				System.out.println("-------------------------------------");
			}
		}
	}

	private static void buyStocks(User user, UserService userService) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter stock name to buy: ");
		String stockName = scanner.nextLine();

		Stocks stockToBuy = userService.getStockByStockName(stockName);

		if (stockToBuy != null) {
			System.out.print("Enter quantity to buy: ");
			int quantityToBuy = scanner.nextInt();

			double totalPrice = stockToBuy.getStockPrice() * quantityToBuy;

			if (user.getWalletBalance() >= totalPrice) {
				user.setWalletBalance(user.getWalletBalance() - totalPrice);
				userService.editUser(user);

				UserPortfolio userPortfolio = new UserPortfolio();
				userPortfolio.setUser(user);
				userPortfolio.setStock(stockToBuy);
				userPortfolio.setQuantity(quantityToBuy);

				userService.addUserPortfolio(userPortfolio);
				System.out.println("Stocks purchased successfully.");
			} else {
				System.out.println("Insufficient balance. Please add funds to your wallet.");
			}
		} else {
			System.out.println("Stock with name " + stockName + " not found.");
		}
	}

	private static void viewAvailableStocks(UserService userService) {
		Scanner scanner = new Scanner(System.in);
		int choice;
		do {
			System.out.println("\n---- Available Stocks ----");
			System.out.println("1. View all available stocks");
			System.out.println("2. View stocks sorted by price (low to high)");
			System.out.println("3. View stocks sorted by price (high to low)");
			System.out.println("4. Back to main menu");
			System.out.print("Enter your choice: ");
			choice = scanner.nextInt();

			switch (choice) {
			case 1:
				viewStocks(userService);
				break;
			case 2:
				viewStocksSortedByPriceLowToHigh(userService);
				break;
			case 3:
				viewStocksSortedByPriceHighToLow(userService);
				break;
			case 4:
				System.out.println("Returning to main menu.");
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		} while (choice != 4);
	}

	private static void viewStocksSortedByPriceLowToHigh(UserService userService) {
		List<Stocks> stocksList = userService.getAllStocks();
		if (stocksList.isEmpty()) {
			System.out.println("No stocks available.");
		} else {
			List<Stocks> sortedStocks = stocksList.stream().sorted(Comparator.comparingDouble(Stocks::getStockPrice))
					.collect(Collectors.toList());

			System.out.println("\n---- Available Stocks (Low to High) ----");
			for (Stocks stock : sortedStocks) {
				System.out.println("Stock Name: " + stock.getStockName());
				System.out.println("Stock Price: " + stock.getStockPrice());
				System.out.println("----------------------");
			}
		}
	}

	private static void viewStocksSortedByPriceHighToLow(UserService userService) {
		List<Stocks> stocksList = userService.getAllStocks();
		if (stocksList.isEmpty()) {
			System.out.println("No stocks available.");
		} else {
			List<Stocks> sortedStocks = stocksList.stream()
					.sorted(Comparator.comparingDouble(Stocks::getStockPrice).reversed()).collect(Collectors.toList());

			System.out.println("\n---- Available Stocks (High to Low) ----");
			for (Stocks stock : sortedStocks) {
				System.out.println("Stock Name: " + stock.getStockName());
				System.out.println("Stock Price: " + stock.getStockPrice());
				System.out.println("----------------------");
			}
		}
	}

	private static void addBalanceToWallet(User user, UserService userService) {
	    Scanner scanner = new Scanner(System.in);
	    System.out.println("Current Wallet Balance: $" + user.getWalletBalance());
	    System.out.println();
	    System.out.print("Enter the amount to add to your wallet: ");
	    double amountToAdd = Double.parseDouble(scanner.nextLine());

	    userService.addBalanceToWallet(user, amountToAdd);

	    user = userService.getUserByUsername(user.getUsername());
	    System.out.println("Amount successfully added to your wallet.");
	    System.out.println("Updated Wallet Balance: $" + user.getWalletBalance());
	}
	
	private static void changePassword(User user, UserService userService) {
	    Scanner scanner = new Scanner(System.in);
	    System.out.print("Enter your current password: ");
	    String currentPassword = scanner.nextLine();


	    if (user.getPassword().equals(currentPassword)) {
	        System.out.print("Enter your new password: ");
	        String newPassword = scanner.nextLine();


	        userService.changePassword(user, newPassword);

	        System.out.println("Password changed successfully. Please log in again.");
	    } else {
	        System.out.println("Incorrect current password. Password change failed.");
	    }
	}


}

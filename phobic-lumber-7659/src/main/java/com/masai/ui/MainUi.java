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
import com.masai.Service.UserServiceImpl;
import com.masai.dto.Stocks;
import com.masai.dto.User;
import com.masai.dto.UserPortfolio;

public class MainUi {

	private static final String RESET = "\u001B[0m";
	private static final String GREEN_BOLD = "\u001B[32;1m";
	private static final String GREEN = "\u001B[32m";
	private static final String YELLOW = "\u001B[33m";
	private static final String CYAN_BOLD = "\u001B[36;1m";
	private static final String RED_BOLD = "\u001B[31;1m";
	private static final String MAGENTA_BOLD = "\u001B[35;1m";
	private static final String WHITE_BOLD = "\u001B[37;1m";
	private static final String WHITE_BACKGROUND = "\u001B[47m";
	private static final String PURPLE_BACKGROUND = "\u001B[45m";
	private static final String CYAN_BACKGROUND = "\u001B[46m";
	private static final String Black_BOLD = "\u001B[30;1m";

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		UserDAO userDAO = new UserDAOImpl();
		UserPortfolioDAO userPortfolioDAO = new UserPortfolioDAOImpl();
		UserService userService = new UserServiceImpl(userDAO, userPortfolioDAO);
		int choice = 0;

		{
			System.out.println();
			System.out.println(WHITE_BACKGROUND + Black_BOLD + "📈📊📈 Welcome to StockVerse - Your Personal Stock Market Hub 📈📊📈" + RESET);
			System.out.println();
		}

		do {
			System.out.println("\t" + GREEN_BOLD + "┌───────────── Main-Menu ───────────┐" + RESET);
			System.out.println("\t" + GREEN + "│ " + YELLOW + "(1) --> Admin Login               " + GREEN + "│" + RESET);
			System.out.println("\t" + GREEN + "│ " + YELLOW + "(2) --> Customer Login            " + GREEN + "│" + RESET);
			System.out.println("\t" + GREEN + "│ " + YELLOW + "(3) --> Customer Register         " + GREEN + "│" + RESET);
			System.out.println("\t" + GREEN + "│ " + YELLOW + "(0) --> Exit                      " + GREEN + "│" + RESET);
			System.out.println("\t" + GREEN + "└───────────────────────────────────┘" + RESET);
			System.out.print("\t" + CYAN_BACKGROUND + Black_BOLD + "\tPlease enter your preference:       " + RESET);

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
				System.out.println("\t" + GREEN_BOLD + "Exiting StockVerse. Goodbye!" + RESET);
				break;
			default:
				System.out.println("\t" + RED_BOLD +  "Invalid choice. Please try again."+ RESET);
			}
		} while (choice != 0);
	}

//	-----------------------Admin Functionality-----------------------
//	-----------------------Admin Functionality-----------------------

	public static void adminLogin(UserService userService) {
		String adminUsername = "admin";
		String adminPassword = "admin";

		Scanner scanner = new Scanner(System.in);
		System.out.println();
		System.out.print("\t" + PURPLE_BACKGROUND + WHITE_BOLD + "Enter admin Username: " + RESET);
		String username = scanner.nextLine();

		System.out.print("\t" + PURPLE_BACKGROUND + WHITE_BOLD + "Enter admin password: " + RESET);

		String password = scanner.nextLine();

		if (username.equals(adminUsername) && password.equals(adminPassword)) {
			System.out.println();
			System.out.println("\t" + GREEN_BOLD + "Admin successfully Logged in." + RESET);
			showAdminOptions(userService);
		} else {
			System.out.println("\t" + RED_BOLD + "Invalid credentials. Access denied." + RESET);
		}

	}

	private static void printAdminOptionsMenu() {
		System.out.println("\t" + GREEN_BOLD + "┌───────────Admin Options────────────┐" + RESET);
		System.out.println("\t" + GREEN + "│" + YELLOW + " (1). Add Stocks                    " + GREEN + "│" + RESET);
		System.out.println("\t" + GREEN + "│" + YELLOW + " (2). Edit Stocks                   " + GREEN + "│" + RESET);
		System.out.println("\t" + GREEN + "│" + YELLOW + " (3). View Stocks                   " + GREEN + "│" + RESET);
		System.out.println("\t" + GREEN + "│" + YELLOW + " (4). Delete Stocks                 " + GREEN + "│" + RESET);
		System.out.println("\t" + GREEN + "│" + YELLOW + " (5). View All Users                " + GREEN + "│" + RESET);
		System.out.println("\t" + GREEN + "│" + YELLOW + " (6). Delete Users From Server      " + GREEN + "│" + RESET);
		System.out.println("\t" + GREEN + "│" + YELLOW + " (7). Logout                        " + GREEN + "│" + RESET);
		System.out.println("\t" + GREEN_BOLD + "└────────────────────────────────────┘" + RESET);
		System.out.print("\t" + CYAN_BACKGROUND + Black_BOLD + "\tPlease enter your preference:       " + RESET);
	}

	private static void showAdminOptions(UserService userService) {
		Scanner scanner = new Scanner(System.in);
		int choice;
		do {

			printAdminOptionsMenu();

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
				System.out.println("\t" + GREEN_BOLD + "Admin logged out." + RESET);
				break;
			default:
				System.out.println("\t" + RED_BOLD + "Invalid choice. Please try again." + RESET);
			}
		} while (choice != 7);
	}

	private static void addStock(UserService userService) {
		Scanner scanner = new Scanner(System.in);

		System.out.println();
		System.out.println("\t--- Add Stocks ---");
		System.out.print("\t" + PURPLE_BACKGROUND + WHITE_BOLD + "Enter stock name: " + RESET);
		String stockName = scanner.nextLine();

		System.out.print("\t" + PURPLE_BACKGROUND + WHITE_BOLD + "Enter stock price: " + RESET);
		double stockPrice = Double.parseDouble(scanner.nextLine());

		Stocks newStock = new Stocks();
		newStock.setStockName(stockName);
		newStock.setStockPrice(stockPrice);

		userService.addStock(newStock);

		System.out.println("\t" + GREEN_BOLD + "Stock added successfully." + RESET);
	}

	private static void editStock(UserService userService) {
		Scanner scanner = new Scanner(System.in);

		System.out.println();
		System.out.println("\t---- Edit Stock ----");
		System.out.print("\t" + PURPLE_BACKGROUND + WHITE_BOLD + "Enter stock name to edit: " + RESET);
		String stockName = scanner.nextLine();

		Stocks stockToEdit = userService.getStockByStockName(stockName);

		if (stockToEdit != null) {
			System.out.println("\n\tCurrent Stock Details:");
			System.out.println("\t" + GREEN + "┌───────────────" + RESET);
			System.out.println("\t" + GREEN + "│ " + YELLOW + "Stock Name: " + stockToEdit.getStockName() + RESET);
			System.out.println("\t" + GREEN + "│ " + YELLOW + "Stock Price: " + stockToEdit.getStockPrice() + RESET);
			System.out.println("\t" + GREEN + "└───────────────" + RESET);

			System.out.println();
			System.out.print("\t" + PURPLE_BACKGROUND + WHITE_BOLD
					+ "Enter new stock price (press Enter to keep current price): " + RESET);
			String newStockPriceStr = scanner.nextLine();
			if (!newStockPriceStr.isEmpty()) {
				double newStockPrice = Double.parseDouble(newStockPriceStr);
				stockToEdit.setStockPrice(newStockPrice);
			}

			userService.editStock(stockToEdit);
			System.out.println("\t" + GREEN_BOLD + "Stock details updated successfully." + RESET);
		} else {
			System.out.println("\t" + RED_BOLD + "Stock with name " + stockName + " not found." + RESET);
		}
	}

	private static void viewStocks(UserService userService) {
		System.out.println("\n\t---- View Stocks ----");

		List<Stocks> stocksList = userService.getAllStocks();

		if (stocksList.isEmpty()) {
			System.out.println("\t" + RED_BOLD + "No stocks available." + RESET);
		} else {
			System.out.println("\tAvailable Stocks:");

			for (Stocks stock : stocksList) {
				System.out.println("\t" + GREEN + "┌───────────────" + RESET);
				System.out.println("\t" + GREEN + "│ " + YELLOW + "Stock Name: " + stock.getStockName() + RESET);
				System.out.println("\t" + GREEN + "│ " + YELLOW + "Stock Price: " + stock.getStockPrice() + RESET);
				System.out.println("\t" + GREEN + "└───────────────" + RESET);
			}
		}
	}

	private static void deleteStock(UserService userService) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("\n\t---- Delete Stock ----");
		System.out.print("\t" + PURPLE_BACKGROUND + WHITE_BOLD + "Enter stock name to delete: " + RESET);
		String stockName = scanner.nextLine();

		Stocks stockToDelete = userService.getStockByStockName(stockName);

		if (stockToDelete != null) {
			System.out.println("\n\tCurrent Stock Details:");

			System.out.println("\t" + GREEN + "┌───────────────" + RESET);
			System.out.println("\t" + GREEN + "│ " + YELLOW + "Stock Name: " + stockToDelete.getStockName() + RESET);
			System.out.println("\t" + GREEN + "│ " + YELLOW + "Stock Price: " + stockToDelete.getStockName() + RESET);
			System.out.println("\t" + GREEN + "└───────────────" + RESET);

			System.out.print("\t" + PURPLE_BACKGROUND + WHITE_BOLD
					+ "Are you sure you want to delete this stock? (yes/no): " + RESET);
			String confirmation = scanner.nextLine().toLowerCase();

			if (confirmation.equals("yes")) {
				userService.deleteStock(stockToDelete);
				System.out.println("\t" + GREEN_BOLD + "Stock deleted successfully." + RESET);
			} else {
				System.out.println("\t" + RED_BOLD + "Stock deletion canceled." + RESET);
			}
		} else {
			System.out.println("\t" + RED_BOLD + "Stock with name " + stockName + " not found." + RESET);
		}
	}

	private static void viewAllUsers(UserService userService) {
		List<User> users = userService.viewAllUsers();
		if (users.isEmpty()) {
			System.out.println("\t" + RED_BOLD + "No users found on the server." + RESET);
		} else {
			System.out.println("\n\t---- All Users ----");
			for (User user : users) {
				System.out.println("\t" + GREEN + "┌───────────────" + RESET);
				System.out.println("\t" + GREEN + "│ " + YELLOW + "Name: " + user.getName() + RESET);
				System.out.println("\t" + GREEN + "│ " + YELLOW + "Username: " + user.getUsername() + RESET);
				System.out.println("\t" + GREEN + "│ " + YELLOW + "Wallet Balance: " + user.getWalletBalance() + RESET);
				System.out.println("\t" + GREEN + "└───────────────" + RESET);

			}
		}
	}

	private static void deleteUsers(UserService userService) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("\n\t---- Delete Users From Server ----");
		System.out.print("\t" + PURPLE_BACKGROUND + WHITE_BOLD + "Enter username of the user to delete: " + RESET);
		String username = scanner.nextLine();

		User userToDelete = userService.getUserByUsername(username);

		if (userToDelete != null) {
			System.out.println("\n\tCurrent User Details:");
			
			
			System.out.println("\t" + GREEN + "┌───────────────" + RESET);
			System.out.println("\t" + GREEN + "│ " + YELLOW + "Name: " + userToDelete.getName() + RESET);
			System.out.println("\t" + GREEN + "│ " + YELLOW + "Username: " + userToDelete.getUsername() + RESET);
			System.out.println("\t" + GREEN + "│ " + YELLOW + "Wallet Balance: " + userToDelete.getWalletBalance() + RESET);
			System.out.println("\t" + GREEN + "└───────────────" + RESET);

			System.out.print("\t" + PURPLE_BACKGROUND + WHITE_BOLD
					+ "Are you sure you want to delete this user? (yes/no): " + RESET);
			String confirmation = scanner.nextLine().toLowerCase();

			if (confirmation.equals("yes")) {
				userService.deleteUser(userToDelete);
				System.out.println("\t" + GREEN_BOLD + "User deleted successfully." + RESET);
			} else {
				System.out.println("\t" + RED_BOLD + "User deletion canceled." + RESET);
			}
		} else {
			System.out.println("\t" + RED_BOLD + "User with username " + username + " not found." + RESET);
		}
	}

//	-----------------------User SignUp Functionality-----------------------
//	-----------------------User SignUp Functionality-----------------------

	private static void userSignup(UserService userService) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("\t" + PURPLE_BACKGROUND + WHITE_BOLD + "Enter user name: " + RESET);
		String name = scanner.nextLine();

		System.out.print("\t" + PURPLE_BACKGROUND + WHITE_BOLD + "Enter username: " + RESET);
		String username = scanner.nextLine();

		System.out.print("\t" + PURPLE_BACKGROUND + WHITE_BOLD + "Enter password: " + RESET);
		String password = scanner.nextLine();

		System.out.print("\t" + PURPLE_BACKGROUND + WHITE_BOLD + "Enter initial wallet balance: " + RESET);
		double walletBalance = scanner.nextDouble();

		User user = new User();
		user.setName(name);
		user.setUsername(username);
		user.setPassword(password);
		user.setWalletBalance(walletBalance);

		userService.addUser(user);
		System.out.println("\t" + GREEN_BOLD + "User signup successful. You can now log in." + RESET);
	}

//	-----------------------User Login Functionality-----------------------
//	-----------------------User Login Functionality-----------------------

	private static void userLogin(UserService userService) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("\t" + PURPLE_BACKGROUND + WHITE_BOLD + "Enter username: " + RESET);
		String username = scanner.nextLine();

		System.out.print("\t" + PURPLE_BACKGROUND + WHITE_BOLD + "Enter password: " + RESET);
		String password = scanner.nextLine();

		User user = userService.getUserByUsername(username);

		if (user != null && user.getPassword().equals(password)) {
			showUserOptions(userService, user);
		} else {
			System.out.println("\t" + RED_BOLD + "Invalid credentials. Access denied." + RESET);
		}
	}

	private static void printUserOptionsMenu() {
		System.out.println("\n\t" + GREEN_BOLD + "┌──────────────User Options─────────┐" + RESET);
		System.out.println("\t" + GREEN + "│" + YELLOW + " (1). View Portfolio               " + GREEN + "│" + RESET);
		System.out.println("\t" + GREEN + "│" + YELLOW + " (2). View Available Stocks        " + GREEN + "│" + RESET);
		System.out.println("\t" + GREEN + "│" + YELLOW + " (3). Buy Stocks                   " + GREEN + "│" + RESET);
		System.out.println("\t" + GREEN + "│" + YELLOW + " (4). Add More Balance to Wallet   " + GREEN + "│" + RESET);
		System.out.println("\t" + GREEN + "│" + YELLOW + " (5). Change Password              " + GREEN + "│" + RESET);
		System.out.println("\t" + GREEN + "│" + YELLOW + " (6). Logout                       " + GREEN + "│" + RESET);
		System.out.println("\t" + GREEN_BOLD + "└───────────────────────────────────┘" + RESET);
		System.out.print("\t" + CYAN_BACKGROUND + Black_BOLD + "\tPlease enter your preference:       " + RESET);
	}

	private static void showUserOptions(UserService userService, User user) {
		Scanner scanner = new Scanner(System.in);
		int choice;
		do {

			printUserOptionsMenu();

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
				System.out.println("\t" + GREEN_BOLD + "User logged out." + RESET);
				break;
			default:
				System.out.println("\t" + RED_BOLD + "Invalid choice. Please try again." + RESET);
			}
		} while (choice != 6);
	}

	private static void viewPortfolio(User user, UserService userService) {
		List<UserPortfolio> userPortfolio = userService.getUserPortfolioByUser(user);
		if (userPortfolio.isEmpty()) {
			System.out
					.println("\t" + RED_BOLD + "Your portfolio is empty. Buy stocks to add to your portfolio." + RESET);
		} else {
			System.out.println("\n\t---- User Portfolio ----");
			for (UserPortfolio portfolio : userPortfolio) {
				System.out.println("\t" + GREEN + "┌───────────────" + RESET);
				System.out.println("\t" + GREEN + "│ " + YELLOW + "Stock Name: " + portfolio.getStock().getStockName() + RESET);
				System.out.println("\t" + GREEN + "│ " + YELLOW + "Stock Price: " + portfolio.getStock().getStockPrice() + RESET);
				System.out.println("\t" + GREEN + "│ " + YELLOW + "Quantity: " + portfolio.getQuantity() + RESET);
				System.out.println("\t" + GREEN + "└───────────────" + RESET);
			}
		}
	}

	private static void buyStocks(User user, UserService userService) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("\t" + PURPLE_BACKGROUND + WHITE_BOLD + "Enter stock name to buy: " + RESET);
		String stockName = scanner.nextLine();

		Stocks stockToBuy = userService.getStockByStockName(stockName);

		if (stockToBuy != null) {
			System.out.print("\t" + PURPLE_BACKGROUND + WHITE_BOLD + "Enter quantity to buy: " + RESET);
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
				System.out.println("\t" + GREEN_BOLD + "Stocks purchased successfully." + RESET);
			} else {
				System.out.println("\t" + RED_BOLD + "Insufficient balance. Please add funds to your wallet." + RESET);
			}
		} else {
			System.out.println("\t" + RED_BOLD + "Stock with name " + stockName + " not found." + RESET);
		}
	}

	private static void printAvailableStocksMenu() {
		System.out.println("\n\t" + GREEN_BOLD + "┌──────────────Available Stocks─────────────────┐" + RESET);
		System.out.println("\t" + GREEN + "│" + YELLOW + " (1). View all available stocks                 " + GREEN + "│" + RESET);
		System.out.println("\t" + GREEN + "│" + YELLOW + " (2). View stocks sorted by price (low to high) " + GREEN + "│" + RESET);
		System.out.println("\t" + GREEN + "│" + YELLOW + " (3). View stocks sorted by price (high to low) " + GREEN + "│" + RESET);
		System.out.println("\t" + GREEN + "│" + YELLOW + " (4). Back to main menu                         " + GREEN + "│" + RESET);
		System.out.println("\t" + GREEN_BOLD + "└───────────────────────────────────────────────┘" + RESET);
		System.out.print("\t" + CYAN_BACKGROUND + Black_BOLD + "\tPlease enter your choice:       " + RESET);
	}

	private static void viewAvailableStocks(UserService userService) {
		Scanner scanner = new Scanner(System.in);
		int choice;
		do {

			printAvailableStocksMenu();
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
				System.out.println("\t" + GREEN_BOLD + "Returning to main menu." + RESET);
				break;
			default:
				System.out.println("\t" + RED_BOLD + "Invalid choice. Please try again." + RESET);
			}
		} while (choice != 4);
	}

	private static void viewStocksSortedByPriceLowToHigh(UserService userService) {
		List<Stocks> stocksList = userService.getAllStocks();
		if (stocksList.isEmpty()) {
			System.out.println("\t" + RED_BOLD + "No stocks available." + RESET);
		} else {
			List<Stocks> sortedStocks = stocksList.stream().sorted(Comparator.comparingDouble(Stocks::getStockPrice))
					.collect(Collectors.toList());

			System.out.println("\n\t---- Available Stocks (Low to High) ----");
			for (Stocks stock : sortedStocks) {
				System.out.println("\t" + GREEN + "┌───────────────" + RESET);
				System.out.println("\t" + GREEN + "│ " + YELLOW + "Stock Name: " + stock.getStockName() + RESET);
				System.out.println("\t" + GREEN + "│ " + YELLOW + "Stock Price: " + stock.getStockPrice() + RESET);
				System.out.println("\t" + GREEN + "└───────────────" + RESET);
			}
		}
	}

	private static void viewStocksSortedByPriceHighToLow(UserService userService) {
		List<Stocks> stocksList = userService.getAllStocks();
		if (stocksList.isEmpty()) {
			System.out.println("\tNo stocks available.");
		} else {
			List<Stocks> sortedStocks = stocksList.stream()
					.sorted(Comparator.comparingDouble(Stocks::getStockPrice).reversed()).collect(Collectors.toList());

			System.out.println("\n\t---- Available Stocks (High to Low) ----");
			for (Stocks stock : sortedStocks) {
				System.out.println("\t" + GREEN + "┌───────────────" + RESET);
				System.out.println("\t" + GREEN + "│ " + YELLOW + "Stock Name: " + stock.getStockName() + RESET);
				System.out.println("\t" + GREEN + "│ " + YELLOW + "Stock Price: " + stock.getStockPrice() + RESET);
				System.out.println("\t" + GREEN + "└───────────────" + RESET);
			}
		}
	}

	private static void addBalanceToWallet(User user, UserService userService) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("\t" + CYAN_BOLD + "Current Wallet Balance: $" + user.getWalletBalance() + RESET);
		System.out.println();
		System.out.print("\t" + PURPLE_BACKGROUND + WHITE_BOLD + "Enter the amount to add to your wallet: " + RESET);
		double amountToAdd = Double.parseDouble(scanner.nextLine());

		userService.addBalanceToWallet(user, amountToAdd);

		user = userService.getUserByUsername(user.getUsername());
		System.out.println("\t" + GREEN_BOLD + "Amount successfully added to your wallet." + RESET);
		System.out.println("\t" + CYAN_BOLD + "Updated Wallet Balance: $" + user.getWalletBalance() + RESET);
	}

	private static void changePassword(User user, UserService userService) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("\t" + PURPLE_BACKGROUND + WHITE_BOLD + "Enter your current password: " + RESET);
		String currentPassword = scanner.nextLine();

		if (user.getPassword().equals(currentPassword)) {
			System.out.print("\t" + PURPLE_BACKGROUND + WHITE_BOLD + "Enter your new password: " + RESET);
			String newPassword = scanner.nextLine();

			userService.changePassword(user, newPassword);

			System.out.println("\t" + GREEN_BOLD + "Password changed successfully. Please log in again." + RESET);
		} else {
			System.out.println("\t" + RED_BOLD + "Incorrect current password. Password change failed." + RESET);
		}
	}

}

package library;

import java.util.InputMismatchException;
import java.util.Scanner;
import library.items.*;
import library.members.Member;
import library.exceptions.ItemNotAvailableException;
import library.exceptions.BorrowLimitExceededException;
import library.utils.MemberFactory;
import library.utils.SearchResult;
import library.utils.StudentConfig;

public class LibraryApp {
    private final Library library;
    private final Scanner scanner;
    private final StudentConfig config;

    public LibraryApp(int studentNumber) {
        this.library = new Library();
        this.scanner = new Scanner(System.in);
        this.config = new StudentConfig(studentNumber);
    }

    public static void main(String[] args) {
        Scanner startupScanner = new Scanner(System.in);
        System.out.println("===== Library Management System Setup =====");
        int studentNumber = -1;
        while (studentNumber < 0) {
            System.out.print("Enter your student number (non-negative integer): ");
            try {
                studentNumber = Integer.parseInt(startupScanner.nextLine().trim());
                if (studentNumber < 0) {
                    System.out.println("Please enter a non-negative number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please try again.");
            }
        }
        LibraryApp app = new LibraryApp(studentNumber);
        app.printStartupInfo();
        app.run();
    }

    private void printStartupInfo() {
        System.out.println("Student configuration loaded: " + config.getVariantDescription());
        System.out.println("Fine per overdue day for this setup: " + config.getFinePerDay() + " AZN");
        System.out.println("===========================================\n");
    }

    private void run() {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Enter choice: ", 1, 8);
            switch (choice) {
                case 1 -> addNewItem();
                case 2 -> registerNewMember();
                case 3 -> borrowItem();
                case 4 -> returnItem();
                case 5 -> searchItems();
                case 6 -> viewMemberReport();
                case 7 -> viewAvailableItems();
                case 8 -> {
                    System.out.println("Exiting application. Goodbye!");
                    running = false;
                }
                default -> System.out.println("Unknown option. Please enter a number between 1 and 8.");
            }
            System.out.println();
        }
    }

    private void printMenu() {
        System.out.println("===== Library Management System =====");
        System.out.println("1. Add new item        (Book / Magazine / Thesis)");
        System.out.println("2. Register new member");
        System.out.println("3. Borrow item");
        System.out.println("4. Return item");
        System.out.println("5. Search items        (by title or author)");
        System.out.println("6. View member report");
        System.out.println("7. View all available items");
        System.out.println("8. Exit");
        System.out.println("======================================");
    }

    private void addNewItem() {
        System.out.println("Choose an item type:");
        System.out.println("1. Book");
        System.out.println("2. Magazine");
        System.out.println("3. Thesis");
        int type = readInt("Type: ", 1, 3);
        System.out.print("Item ID: ");
        String itemId = scanner.nextLine().trim();
        System.out.print("Title: ");
        String title = scanner.nextLine().trim();
        System.out.print("Author: ");
        String author = scanner.nextLine().trim();

        try {
            LibraryItem item;
            switch (type) {
                case 1 -> item = new Book(itemId, title, author);
                case 2 -> item = new Magazine(itemId, title, author);
                case 3 -> item = new Thesis(itemId, title, author);
                default -> throw new IllegalStateException("Unexpected value: " + type);
            }
            library.addItem(item);
            System.out.println("Item added successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Failed to add item: " + e.getMessage());
        }
    }

    private void registerNewMember() {
        String[] tierNames = config.getTierNames();
        System.out.println("Choose a member tier:");
        for (int i = 0; i < tierNames.length; i++) {
            System.out.println((i + 1) + ". " + tierNames[i]);
        }
        int tier = readInt("Tier: ", 1, 3);
        System.out.print("Member ID: ");
        String memberId = scanner.nextLine().trim();
        System.out.print("Member name: ");
        String name = scanner.nextLine().trim();

        try {
            Member member = MemberFactory.createMember(config.getVariant(), tier, memberId, name);
            library.registerMember(member);
            System.out.println("Member registered successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Failed to register member: " + e.getMessage());
        }
    }

    private void borrowItem() {
        System.out.print("Member ID: ");
        String memberId = scanner.nextLine().trim();
        System.out.print("Item ID: ");
        String itemId = scanner.nextLine().trim();
        try {
            library.borrowItem(memberId, itemId);
            System.out.println("Borrow completed successfully.");
        } catch (ItemNotAvailableException | BorrowLimitExceededException e) {
            System.out.println("Cannot borrow item: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void returnItem() {
        System.out.print("Member ID: ");
        String memberId = scanner.nextLine().trim();
        System.out.print("Item ID: ");
        String itemId = scanner.nextLine().trim();
        int overdueDays = readInt("Overdue days (0 if returned on time): ", 0, Integer.MAX_VALUE);
        try {
            double fine = library.returnItem(memberId, itemId, overdueDays);
            System.out.println("Return completed successfully.");
            if (fine > 0) {
                System.out.printf("Total fine: %.2f AZN%n", fine);
            } else {
                System.out.println("No fine was charged.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void searchItems() {
        System.out.println("Search by:");
        System.out.println("1. Title");
        System.out.println("2. Author");
        int choice = readInt("Choice: ", 1, 2);
        System.out.print("Enter keyword: ");
        String keyword = scanner.nextLine().trim();
        SearchResult<LibraryItem> result = choice == 1
                ? library.searchByTitle(keyword)
                : library.searchByAuthor(keyword);
        System.out.println("Search results: " + result.getCount());
        result.display();
    }

    private void viewMemberReport() {
        System.out.print("Member ID: ");
        String memberId = scanner.nextLine().trim();
        try {
            library.getMemberReport(memberId);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void viewAvailableItems() {
        System.out.println("Available items:");
        library.listAllAvailable();
    }

    private int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(line);
                if (value < min || value > max) {
                    System.out.println("Please enter a number between " + min + " and " + max + ".");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
    }
}

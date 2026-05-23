import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    static class Book {
        int id;
        String title;
        String author;
        boolean available;

        Book(int id, String title, String author) {
            this.id = id;
            this.title = title;
            this.author = author;
            this.available = true;
        }
    }

    static ArrayList<Book> books = new ArrayList<>();
    static int idCounter = 1;
    static Scanner sc = new Scanner(System.in);

    // Add a new book
    static void addBook() {
        System.out.print("Enter book title: ");
        String title = sc.nextLine();
        System.out.print("Enter author name: ");
        String author = sc.nextLine();
        books.add(new Book(idCounter++, title, author));
        System.out.println("Book added successfully.");
    }

    // View all books
    static void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }
        System.out.println("\n------------------------------------------");
        System.out.printf("%-5s %-25s %-20s %-10s\n", "ID", "Title", "Author", "Status");
        System.out.println("------------------------------------------");
        for (Book b : books) {
            String status = b.available ? "Available" : "Issued";
            System.out.printf("%-5d %-25s %-20s %-10s\n", b.id, b.title, b.author, status);
        }
        System.out.println("------------------------------------------");
    }

    // Search book by title
    static void searchBook() {
        System.out.print("Enter title to search: ");
        String keyword = sc.nextLine().toLowerCase();
        boolean found = false;
        for (Book b : books) {
            if (b.title.toLowerCase().contains(keyword)) {
                System.out.println("\nBook Found:");
                System.out.println("ID     : " + b.id);
                System.out.println("Title  : " + b.title);
                System.out.println("Author : " + b.author);
                System.out.println("Status : " + (b.available ? "Available" : "Issued"));
                found = true;
            }
        }
        if (!found) System.out.println("No book found with that title.");
    }

    // Issue a book
    static void issueBook() {
        System.out.print("Enter book ID to issue: ");
        int id = sc.nextInt();
        sc.nextLine();
        for (Book b : books) {
            if (b.id == id) {
                if (b.available) {
                    b.available = false;
                    System.out.println("Book issued successfully.");
                } else {
                    System.out.println("Book is already issued.");
                }
                return;
            }
        }
        System.out.println("Book not found.");
    }

    // Return a book
    static void returnBook() {
        System.out.print("Enter book ID to return: ");
        int id = sc.nextInt();
        sc.nextLine();
        for (Book b : books) {
            if (b.id == id) {
                if (!b.available) {
                    b.available = true;
                    System.out.println("Book returned successfully.");
                } else {
                    System.out.println("Book was not issued.");
                }
                return;
            }
        }
        System.out.println("Book not found.");
    }

    // Delete a book
    static void deleteBook() {
        System.out.print("Enter book ID to delete: ");
        int id = sc.nextInt();
        sc.nextLine();
        Book toRemove = null;
        for (Book b : books) {
            if (b.id == id) {
                toRemove = b;
                break;
            }
        }
        if (toRemove != null) {
            books.remove(toRemove);
            System.out.println("Book deleted successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }

    // Display menu
    static void showMenu() {
        System.out.println("\n== Library Management System ==");
        System.out.println("1. Add Book");
        System.out.println("2. View All Books");
        System.out.println("3. Search Book");
        System.out.println("4. Issue Book");
        System.out.println("5. Return Book");
        System.out.println("6. Delete Book");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");
    }

    public static void main(String[] args) {
        int choice;
        do {
            showMenu();
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1: addBook();    break;
                case 2: viewBooks();  break;
                case 3: searchBook(); break;
                case 4: issueBook();  break;
                case 5: returnBook(); break;
                case 6: deleteBook(); break;
                case 7: System.out.println("Goodbye!"); break;
                default: System.out.println("Invalid choice.");
            }
        } while (choice != 7);

        sc.close();
    }
}
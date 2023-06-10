import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class LibraryCatalogApplication {
    private final int MAX_BOOKS = 100;
    private final int BOOK_ATTRIBUTES = 5;
    private final int BOOK_ID_INDEX = 0;
    private final int BOOK_TITLE_INDEX = 1;
    private final int BOOK_AUTHOR_INDEX = 2;
    private final int BOOK_AVAILABILITY_INDEX = 3;
    private final int BOOK_ISSUE_DATE_INDEX = 4;

    String[][] catalog = new String[MAX_BOOKS][BOOK_ATTRIBUTES];
    int bookCount = 0;
    {
        // Add book data to the catalog
        catalog[bookCount++] = new String[]{"101", "HTML and CSS", "Jon Duckett", "Available", "Null"};
        catalog[bookCount++] = new String[]{"102", "JavaScript: The Good Parts", "Douglas C", "Available", "Null"};
        catalog[bookCount++] = new String[]{"103", "Learning Web Design", "Jennifer N", "CP2014", "23-May-2023"};
        catalog[bookCount++] = new String[]{"104", "Responsive Web Design", "Ben Frain", "EC3142", "17-May-2023"};
    }
    Scanner scanner = new Scanner(System.in);

    public void displayMainMenu() {
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("Welcome to the Unique Library");
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("1. View the complete list of Books");
        System.out.println("2. Issue a Book");
        System.out.println("3. Return a Book");
        System.out.println("4. Exit");
    }

    public void displayAllBooks() {
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("List of all Books");
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.printf("%-15s%-29s%-15s%-15s%s\n", "Book ID", "Book Title", "Author", "Availability", "Issue Date");
        System.out.println("-----------------------------------------------------------------------------------------");
        for (int i = 0; i < bookCount; i++) {
            for (int j = 0; j < BOOK_ATTRIBUTES; j++) {
                if(j == BOOK_TITLE_INDEX){
                    System.out.printf("%-29s", catalog[i][j]);
                    continue;
                }
                System.out.printf("%-15s", catalog[i][j]);
            }
            System.out.println();
        }
    }

    public void issueBook() {
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("Issue the Book");
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.print("Enter the Book ID: ");
        String bookID = scanner.nextLine();
        int bookIndex = findBookIndexByID(bookID);
        if (bookIndex == -1) {
            System.out.println("Book not found!");
            return;
        }
        if (!catalog[bookIndex][BOOK_AVAILABILITY_INDEX].equals("Available")) {
            System.out.println("Book is already issued.");
            return;
        }
        System.out.println(catalog[bookIndex][BOOK_ID_INDEX] + " " + catalog[bookIndex][BOOK_TITLE_INDEX] + " - " +
                catalog[bookIndex][BOOK_AUTHOR_INDEX] + ": Available");
        System.out.print("Enter StudentID: ");
        String studentID = scanner.nextLine();
        System.out.print("Enter \"C\" to confirm: ");
        String confirmation = scanner.nextLine();
        if (confirmation.equalsIgnoreCase("C")) {
            catalog[bookIndex][BOOK_AVAILABILITY_INDEX] = studentID;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
            catalog[bookIndex][BOOK_ISSUE_DATE_INDEX] = LocalDate.now().format(formatter);
            System.out.println("BookID: " + catalog[bookIndex][BOOK_ID_INDEX] + " is issued to " + studentID);
        } else {
            System.out.println("Issue operation canceled.");
        }
    }

    public void returnBook() {
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("Return the Book");
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.print("Enter the Book ID: ");
        String bookID = scanner.nextLine();
        int bookIndex = findBookIndexByID(bookID);
        if (bookIndex == -1) {
            System.out.println("Book not found!");
            return;
        }
        if (catalog[bookIndex][BOOK_AVAILABILITY_INDEX].equals("Available")) {
            System.out.println("Book is not issued.");
            return;
        }
        System.out.println(catalog[bookIndex][BOOK_ID_INDEX] + " - " + catalog[bookIndex][BOOK_TITLE_INDEX] + " by " +
                catalog[bookIndex][BOOK_AUTHOR_INDEX]);
        System.out.println("StudentID - " + catalog[bookIndex][BOOK_AVAILABILITY_INDEX]);
        System.out.println("Issue Date - " + catalog[bookIndex][BOOK_ISSUE_DATE_INDEX]);
        long daysDelayed = calculateDaysDelayed(catalog[bookIndex][BOOK_ISSUE_DATE_INDEX]);
        if (daysDelayed > 0) {
            double delayedCharges = daysDelayed * 10.0;
            System.out.println("Delayed by - " + daysDelayed + " days");
            System.out.printf("Delayed Charges - Rs. %.2f\n", delayedCharges);
        }
        System.out.print("Enter \"R\" to confirm the return: ");
        String confirmation = scanner.nextLine();
        if (confirmation.equalsIgnoreCase("R")) {
            catalog[bookIndex][BOOK_AVAILABILITY_INDEX] = "Available";
            catalog[bookIndex][BOOK_ISSUE_DATE_INDEX] = "Null";
            System.out.println("BookID: " + catalog[bookIndex][BOOK_ID_INDEX] + " is returned back");
        } else {
            System.out.println("Return operation canceled.");
        }
    }

    public int findBookIndexByID(String bookID) {
        for (int i = 0; i < bookCount; i++) {
            if (catalog[i][BOOK_ID_INDEX].equals(bookID)) {
                return i;
            }
        }
        return -1;
    }

    public long calculateDaysDelayed(String issueDate) {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        LocalDate parsedIssueDate = LocalDate.parse(issueDate, formatter);
        return ChronoUnit.DAYS.between(parsedIssueDate, currentDate) - 7;
    }

    public void run() {
        String choice;

        do {
            displayMainMenu();
            System.out.print("\nPlease choose an option from the above menu: ");
            String option = scanner.next();
            scanner.nextLine(); // Consume the newline character left by next()
            System.out.println();
                if(option.equals("1")) {
                    displayAllBooks();
                } else if(option.equals("2")) {
                    issueBook();
                } else if(option.equals("3")) {
                    returnBook();
                } else if(option.equals("4")) {
                    System.out.println("Thank you for visiting  SmartPoint!");
                    return;
                } else {
                    System.out.println("Invalid option!");
            }
            System.out.print("\nEnter \"Y\" to return to the main menu or \"N\" to Exit: ");
            choice = scanner.next();
            System.out.println();
        } while (choice.equalsIgnoreCase("Y"));
        System.out.println("Thank you for visiting  SmartPoint!");
    }

    public static void main(String[] args) {
        LibraryCatalogApplication libraryCatalogApplication = new LibraryCatalogApplication();
        libraryCatalogApplication.run();
    }
}

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Visitor {
    private String name;
    private long contactNumber;
    private String email;

    public Visitor(String name, long contactNumber, String email) {
        this.name = name;
        this.contactNumber = contactNumber;
        this.email = email;
    }

    // Getter and Setter methods
    public String getName() {
        return name;
    }

    public long getContactNumber() {
        return contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setContactNumber(long contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

class Appointment {
    private LocalDate date;
    private LocalTime time;
    private Visitor visitor;

    public Appointment(LocalDate date, LocalTime time, Visitor visitor) {
        this.date = date;
        this.time = time;
        this.visitor = visitor;
    }

    // Getter and Setter methods
    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public Visitor getVisitor() {
        return visitor;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}

class AppointmentManagementSystemWithOops {
    private HashMap<LocalDate, ArrayList<Appointment>> appointments;

    public AppointmentManagementSystemWithOops() {
        appointments = new HashMap<>();
    }

    public void addAppointment(LocalDate date, LocalTime time, Visitor visitor) {
        Appointment appointment = new Appointment(date, time, visitor);
        ArrayList<Appointment> appointmentList = appointments.getOrDefault(date, new ArrayList<>());
        appointmentList.add(appointment);
        appointments.put(date, appointmentList);
    }

    public void removeAppointment(LocalDate date, LocalTime time) {
        ArrayList<Appointment> appointmentList = appointments.get(date);
        if (appointmentList != null) {
            appointmentList.removeIf(appointment -> appointment.getTime().equals(time));
            if (appointmentList.isEmpty()) {
                appointments.remove(date);
            }
        }
    }

    public void displayVisitorList() {
        System.out.println("Visitor List:");
        for (ArrayList<Appointment> appointmentList : appointments.values()) {
            for (Appointment appointment : appointmentList) {
                Visitor visitor = appointment.getVisitor();
                System.out.println("- " + visitor.getName());
            }
        }
    }

    public void addNewVisitor(String name, long contactNumber, String email) {
        Visitor visitor = new Visitor(name, contactNumber, email);
        // Assume the appointment details are entered separately
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        addAppointment(date, time, visitor);
        System.out.println("Visitor added successfully.");
    }

    public void editVisitorDetails(String name, long newContactNumber, String newEmail) {
        for (ArrayList<Appointment> appointmentList : appointments.values()) {
            for (Appointment appointment : appointmentList) {
                Visitor visitor = appointment.getVisitor();
                if (visitor.getName().equalsIgnoreCase(name)) {
                    visitor.setContactNumber(newContactNumber);
                    visitor.setEmail(newEmail);
                    System.out.println("Visitor details updated successfully.");
                    return;
                }
            }
        }
        System.out.println("Visitor not found.");
    }

    public void viewAppointmentSchedule(LocalDate date) {
        ArrayList<Appointment> appointmentList = appointments.get(date);
        if (appointmentList != null) {
            System.out.println("Appointment Schedule for " + date + ":");
            for (Appointment appointment : appointmentList) {
                Visitor visitor = appointment.getVisitor();
                LocalTime time = appointment.getTime();
                System.out.println("- " + time + " - " + visitor.getName());
            }
        } else {
            System.out.println("No appointments scheduled for " + date + ".");
        }
    }

    public void bookAppointment(LocalDate date, LocalTime time, Visitor visitor) {
        ArrayList<Appointment> appointmentList = appointments.getOrDefault(date, new ArrayList<>());
        for (Appointment appointment : appointmentList) {
            if (appointment.getTime().equals(time)) {
                System.out.println("The selected time slot is already booked. Please choose another time.");
                return;
            }
        }
        addAppointment(date, time, visitor);
        System.out.println("Appointment booked successfully.");
    }

    public void editOrCancelAppointment(LocalDate date, LocalTime time, LocalTime newTime) {
        ArrayList<Appointment> appointmentList = appointments.get(date);
        if (appointmentList != null) {
            for (Appointment appointment : appointmentList) {
                if (appointment.getTime().equals(time)) {
                    if (newTime != null) {
                        appointment.setTime(newTime);
                        System.out.println("Appointment time updated successfully.");
                    } else {
                        appointmentList.remove(appointment);
                        if (appointmentList.isEmpty()) {
                            appointments.remove(date);
                        }
                        System.out.println("Appointment canceled successfully.");
                    }
                    return;
                }
            }
        }
        System.out.println("Appointment not found.");
    }
}

public class AppointmentManagementSystemMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AppointmentManagementSystemWithOops ams = new AppointmentManagementSystemWithOops();

        // Data sample
        ams.addAppointment(LocalDate.of(2023, 6, 22), LocalTime.of(10, 30), new Visitor("Raj", 123456789, "raj@example.com"));
        ams.addAppointment(LocalDate.of(2023, 6, 22), LocalTime.of(14, 45), new Visitor("Sathya", 987654321, "sathya@example.com"));
        ams.addAppointment(LocalDate.of(2023, 6, 23), LocalTime.of(9, 0), new Visitor("Arya", 555555555, "arya@example.com"));

        while (true) {
            System.out.println("\n----- Appointment Management System -----");
            System.out.println("1. View Visitor List");
            System.out.println("2. Add New Visitor");
            System.out.println("3. Edit Visitor Details");
            System.out.println("4. View Appointment Schedule for a Day");
            System.out.println("5. Book an Appointment");
            System.out.println("6. Edit/Cancel Appointment");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            System.out.println("\n-----------------------------------------");

            switch (choice) {
                case 1:
                    ams.displayVisitorList();
                    break;
                case 2:
                    System.out.print("Enter Visitor Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Contact Number: ");
                    long contactNumber = scanner.nextLong();
                    scanner.nextLine(); // Consume newline character
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();
                    ams.addNewVisitor(name, contactNumber, email);
                    break;
                case 3:
                    System.out.print("Enter Visitor Name: ");
                    name = scanner.nextLine();
                    System.out.print("Enter New Contact Number: ");
                    contactNumber = scanner.nextLong();
                    scanner.nextLine(); // Consume newline character
                    System.out.print("Enter New Email: ");
                    email = scanner.nextLine();
                    ams.editVisitorDetails(name, contactNumber, email);
                    break;
                case 4:
                    System.out.print("Enter Date (YYYY-MM-DD): ");
                    String dateString = scanner.nextLine();
                    LocalDate date = LocalDate.parse(dateString);
                    ams.viewAppointmentSchedule(date);
                    break;
                case 5:
                    System.out.print("Enter Date (YYYY-MM-DD): ");
                    dateString = scanner.nextLine();
                    date = LocalDate.parse(dateString);
                    System.out.print("Enter Time (HH:MM): ");
                    String timeString = scanner.nextLine();
                    LocalTime time = LocalTime.parse(timeString);
                    System.out.print("Enter Visitor Name: ");
                    name = scanner.nextLine();
                    System.out.print("Enter Contact Number: ");
                    contactNumber = scanner.nextLong();
                    scanner.nextLine(); // Consume newline character
                    System.out.print("Enter Email: ");
                    email = scanner.nextLine();
                    Visitor visitor = new Visitor(name, contactNumber, email);
                    ams.bookAppointment(date, time, visitor);
                    break;
                case 6:
                    System.out.print("Enter Date (YYYY-MM-DD): ");
                    dateString = scanner.nextLine();
                    date = LocalDate.parse(dateString);
                    System.out.print("Enter Time (HH:MM): ");
                    timeString = scanner.nextLine();
                    time = LocalTime.parse(timeString);
                    System.out.println("Do you want to edit or cancel the appointment?");
                    System.out.println("1. Edit Appointment Time");
                    System.out.println("2. Cancel Appointment");
                    int editChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline character
                    if (editChoice == 1) {
                        System.out.print("Enter New Time (HH:MM): ");
                        String newTimeString = scanner.nextLine();
                        LocalTime newTime = LocalTime.parse(newTimeString);
                        ams.editOrCancelAppointment(date, time, newTime);
                    } else if (editChoice == 2) {
                        ams.editOrCancelAppointment(date, time, null);
                    } else {
                        System.out.println("Invalid choice. Returning to the main menu.");
                    }
                    break;
                case 7:
                    System.out.println("Exiting the program...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}

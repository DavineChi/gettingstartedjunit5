package patientintake;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ClinicMain {

	private static ClinicCalendar calendar;
	
	public static void main(String[] args) throws Throwable {
		
		calendar = new ClinicCalendar();
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Welcome to the Patient Intake System!");
		System.out.println();
		System.out.println();
		
		String lastOption = "";
		
		while (!lastOption.equalsIgnoreCase("x")) {
			
			lastOption = displayMenu(scanner);
		}
		
		System.out.println();
		System.out.println("Exiting system...");
		System.out.println();
	}
	
	private static String displayMenu(Scanner scanner) throws Throwable {
		
		System.out.println("Please select an option:");
		System.out.println("1. Enter a Patient Appointment");
		System.out.println("2. View All Appointments");
		System.out.println("X. Exit System");
		System.out.println("Option: ");
		
		String option = scanner.next();
		
		switch (option) {
		
			case "1": performPatientEntry(scanner);
				return option;
				
			case "2": performAllAppointments();
				return option;
				
			case "x":
				return option;
				
			default: System.out.println("Invalid option, please re-enter.");
				return option;
		}
	}
	
	private static void performPatientEntry(Scanner scanner) {
		
		String lastName = "";
		String firstName = "";
		String when = "";
		String doc = "";
		
		scanner.nextLine();
		System.out.println();
		System.out.println();
		System.out.println("Please enter appointment info:");
		System.out.println("  Patient Last Name: ");
		
		lastName = scanner.nextLine();
		
		System.out.println("  Patient First Name: ");
		
		firstName = scanner.nextLine();
		
		System.out.println("  Appointment Date (M/d/yyyy h:m a): ");
		
		when = scanner.nextLine();
		
		System.out.println("  Doctor Last Name: ");
		
		doc = scanner.nextLine();
		
		try {
			
			calendar.addAppointment(firstName, lastName, doc, when);
		}
		
		catch (Throwable th) {
			
			System.out.println("Error: " + th.getMessage());
			return;
		}
		
		System.out.println("Patient entered successfully.");
		System.out.println();
		System.out.println();
	}
	
	private static void performAllAppointments() throws Throwable {
		
		System.out.println();
		System.out.println();
		System.out.println("All appointments in system:");
		
		for (PatientAppointment appointment : calendar.getAppointments()) {
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a");
			String apptTime = formatter.format(appointment.getAppointmentDateTime());
			
			System.out.println(String.format("%s:  %s, %s\t\tDoctor: %s", apptTime, appointment.getPatientLastName(),
					appointment.getPatientFirstName(), appointment.getDoctor().getName()));
		}
		
		System.out.println();
		System.out.println("Press any key to continue...");
		System.in.read();
		System.out.println();
		System.out.println();
	}
}

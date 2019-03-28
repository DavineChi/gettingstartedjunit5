package patientintake;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ClinicCalendar {
	
	private List<PatientAppointment> appointments;

	public ClinicCalendar() {
		
		this.appointments = new ArrayList<PatientAppointment>();
	}
	
	public void addAppointment(String patientFirstName, String patientLastName, String doctorKey, String dateTime) {
		
		Doctor doctor = Doctor.valueOf(doctorKey.toUpperCase());
		LocalDateTime localDateTime;
		
		try {
			
			localDateTime = LocalDateTime.parse(dateTime.toUpperCase(),
					DateTimeFormatter.ofPattern("M/d/yyyy h:mm a", Locale.US));
		}
		
		catch (Throwable th) {
			
			throw new RuntimeException("Unable to create datetime from: [" +
					dateTime.toUpperCase() + "]. Please enter with format [M/d/yyyy h:mm a].");
		}
		
		PatientAppointment appointment = new PatientAppointment(patientFirstName, patientLastName, localDateTime, doctor);
		
		appointments.add(appointment);
	}
	
	public List<PatientAppointment> getAppointments() {
		
		return appointments;
	}
}

package test;

import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import patientintake.ClinicCalendar;
import patientintake.Doctor;
import patientintake.PatientAppointment;

class ClinicCalendarTest {

	@Test
	public void allowEntryOfAnAppointment() {
		
		ClinicCalendar calendar = new ClinicCalendar();
		
		calendar.addAppointment("Shannon", "Fisher", "avery", "3/26/2019 06:00 PM");
		
		List<PatientAppointment> appointments = calendar.getAppointments();
		
		Assertions.assertNotNull(appointments);
		Assertions.assertEquals(1, appointments.size());
		
		PatientAppointment enteredAppt = appointments.get(0);
		
		Assertions.assertEquals("Shannon", enteredAppt.getPatientFirstName());
		Assertions.assertEquals("Fisher", enteredAppt.getPatientLastName());
		Assertions.assertEquals(Doctor.AVERY, enteredAppt.getDoctor());
		Assertions.assertEquals("3/26/2019 06:00 PM", enteredAppt.getAppointmentDateTime().format(DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a")));
		
		
	}
}

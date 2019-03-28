package test;

import java.time.LocalDate;
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
		
		ClinicCalendar calendar = new ClinicCalendar(LocalDate.now());
		
		calendar.addAppointment("Shannon", "Fisher", "AVERY", "3/26/2019 06:00 PM");
		
		List<PatientAppointment> appointments = calendar.getAppointments();
		
		Assertions.assertNotNull(appointments);
		Assertions.assertEquals(1, appointments.size());
		
		PatientAppointment enteredAppt = appointments.get(0);
		
		Assertions.assertEquals("Shannon", enteredAppt.getPatientFirstName());
		Assertions.assertEquals("Fisher", enteredAppt.getPatientLastName());
		Assertions.assertSame(Doctor.AVERY, enteredAppt.getDoctor());
		Assertions.assertEquals("3/26/2019 06:00 PM", enteredAppt.getAppointmentDateTime().format(DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a")));
	}
	
	@Test
	public void returnTrueForHasAppointmentsIfThereAreAppointments() {
		
		ClinicCalendar calendar = new ClinicCalendar(LocalDate.now());
		
		calendar.addAppointment("Shannon", "Fisher", "AVERY", "09/01/2018 2:00 PM");
		Assertions.assertTrue(calendar.hasAppointment(LocalDate.of(2018, 9, 1)));
	}
	
	@Test
	public void returnFalseForHasAppointmentsIfThereAreNoAppointments() {
		
		ClinicCalendar calendar = new ClinicCalendar(LocalDate.now());
		
		Assertions.assertFalse(calendar.hasAppointment(LocalDate.of(2018, 9, 1)));
	}
	
	@Test
	public void returnCurrentDaysAppointments() {
		
		ClinicCalendar calendar = new ClinicCalendar(LocalDate.now());
		
		calendar.addAppointment("Shannon", "Fisher", "johnson", "03/28/2019 4:00 pm");
		calendar.addAppointment("Derek", "Steward", "murphy", "03/28/2019 5:00 pm");
		calendar.addAppointment("Paul", "Purcell", "avery", "09/01/2019 2:00 pm");
		
		Assertions.assertEquals(2, calendar.getTodayAppointments().size());
	}
}

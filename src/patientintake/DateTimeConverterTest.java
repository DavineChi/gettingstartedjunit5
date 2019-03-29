package patientintake;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("dateTime")
@DisplayName("DateTimeConverter test")
class DateTimeConverterTest {

	@Nested
	@DisplayName("for correct conversion of string keyword 'today'")
	class TodayTests {
		
		@Test
		@DisplayName("as normal")
		public void convertTodayStringCorrectly() {
			
			LocalDate today = LocalDate.of(2019, 3, 28);
			LocalDateTime result = DateTimeConverter.convertStringToDateTime("today 1:00 pm", today);
			
			Assertions.assertEquals(result, LocalDateTime.of(2019, 3, 28, 13, 0),
					() -> "Failed to convert 'today' string to expected date time, today passed was: " + today);
		}
		
		@Test
		@DisplayName("regardless of case")
		public void convertTodayStringCorrectlyCaseInsensitive() {
			
			LocalDate today = LocalDate.of(2019, 3, 28);
			LocalDateTime result = DateTimeConverter.convertStringToDateTime("ToDay 1:00 pm", today);
			
			Assertions.assertEquals(result, LocalDateTime.of(2019, 3, 28, 13, 0),
					() -> "Failed to convert 'today' string to expected date time, today passed was: " + today);
		}
	}
	
	@Test
	@DisplayName("for correct conversion of expected datetime pattern")
	public void convertCorrectPatterToDateTime() {
		
		LocalDateTime result = DateTimeConverter.convertStringToDateTime("9/2/2019 1:00 pm", LocalDate.of(2019, 3, 28));
		
		Assertions.assertEquals(result, LocalDateTime.of(2019, 9, 2, 13, 0));
	}
	
	@Test
	@DisplayName("for exception thrown if incorrect string pattern encountered")
	public void throwExceptionIfIncorrectPatternProvided() {
		
		Throwable error =  Assertions.assertThrows(RuntimeException.class, () ->
				DateTimeConverter.convertStringToDateTime("9/2/2019 100 pm",
				LocalDate.of(2019, 9, 2)));
		
		Assertions.assertEquals("Unable to create date time from: [9/2/2019 100 pm], " +
				"please enter with format [M/d/yyyy h:mm a], Text '9/2/2019 100 PM' " +
				"could not be parsed at index 12", error.getMessage());
	}
}

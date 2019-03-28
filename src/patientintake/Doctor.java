package patientintake;

public enum Doctor {

	AVERY("Ralph Avery"),
	JOHNSON("Beth Johnson"),
	MURPHY("Pat Murphy");
	
	private String name;
	
	Doctor(String name) {
		
		this.name = name;
	}
	
	public String getName() {
		
		return name;
	}
}

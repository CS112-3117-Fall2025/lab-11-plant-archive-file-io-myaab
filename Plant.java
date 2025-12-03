public class Plant {
	// CONSTANTS
	public static final String DEFAULT_NAME = "Mario Mushroom";
	public static final double DEFAULT_TEMP_FAHRENHEIT = 451.0;
	public static final String DEFAULT_USES = "Makes you Super!";

	// INSTANCE VARIABLES
	private String name;
	private double tempFahrenheit;
	private String uses;

	// CONSTRUCTORS
	public Plant() {
		this(DEFAULT_NAME, DEFAULT_TEMP_FAHRENHEIT, DEFAULT_USES);
	}

	public Plant(String name, double tempFahrenheit, String uses) throws IllegalArgumentException {
		if(!this.setName(name)) {
			throw new IllegalArgumentException("Invalid name value passed: " + name);
		}
		if(!this.setTempFahrenheit(tempFahrenheit)) {
			throw new IllegalArgumentException("Invalid temperature (F) value passed: " + tempFahrenheit);
		}
		if(!this.setUses(uses)) {
			throw new IllegalArgumentException("Invalid uses value passed: " + uses);
		}
	}

	public Plant(Plant original) throws IllegalArgumentException {
		if(original == null) {
			throw new IllegalArgumentException("Invalid Plant object to copy passed (null)");
		}
		this.setAll(original.name, original.tempFahrenheit, original.uses);
	}

	//Complete: Step 1 = CSV string constructor
	public Plant(String csvLine) throws IllegalArgumentException {
		// Error check the whole string
		if(csvLine == null || csvLine.isEmpty()) {
			throw new IllegalArgumentException("CSV line is null or empty.");
		}

		// Use String's split() method
		String[] parts = csvLine.split(",");

		// Error check the parts (must be 3)
		if(parts.length != 3) {
			throw new IllegalArgumentException("Invalid CSV: Expected 3 values, found " + parts.length + " in line: " + csvLine);
		}

		try {
			// Parse and trim data
			String name = parts[0].trim();
			double temp = Double.parseDouble(parts[1].trim());
			String uses = parts[2].trim();

			// Use the validation logic from the setters
			if (!this.setName(name)) {
				throw new IllegalArgumentException("Invalid name value passed: " + name);
			}
			if (!this.setTempFahrenheit(temp)) {
				throw new IllegalArgumentException("Invalid temperature (F) value passed: " + temp);
			}
			if (!this.setUses(uses)) {
				throw new IllegalArgumentException("Invalid uses value passed: " + uses);
			}

		} catch (NumberFormatException e) {
			// This catches errors from Double.parseDouble()
			throw new IllegalArgumentException("Invalid temperature format: '" + parts[1].trim() + "'", e);
		}
	}

	// MUTATORS/SETTERS
	public boolean setName(String name) {
		if(name == null || name.length() == 0) {
			return false;
		} else {
			this.name = name;
			return true;
		}
	}

	public boolean setTempFahrenheit(double tempFahrenheit) {
		if(tempFahrenheit < -459.67 || tempFahrenheit > 451.0 ) {
			return false;
		} else {
			this.tempFahrenheit = tempFahrenheit;
			return true;
		}
	}

	public boolean setUses(String uses) {
		if(uses == null || uses.length() == 0) {
			return false;
		} else {
			this.uses = uses;
			return true;
		}
	}

	public boolean setAll(String name, double tempFahrenheit, String uses) {
		String nameBackup = this.name, usesBackup = this.uses;
		double tempBackup = this.tempFahrenheit;

		if(!this.setName(name)) {
			this.name = nameBackup;
			return false;
		}

		if(!this.setTempFahrenheit(tempFahrenheit)) {
			this.tempFahrenheit = tempBackup;
			return false;
		}

		if(!this.setUses(uses)) {
			this.uses = usesBackup;
			return false;
		}

		return true;//only happens if all 3 setters return true and do their jobs
	}

	// ACCESSORS/GETTERS
	public String getName() {
		return this.name;
	}

	public double getTempFahrenheit() {
		return this.tempFahrenheit;
	}

	public String getUses() {
		return this.uses;
	}

	//OTHER REQUIRED METHODS
	@Override
	public boolean equals(Object other) {
		if(other == null || other.getClass() != this.getClass()) {
			return false;
		} else {
			Plant otherPlant = (Plant) other;

			return this.name.equals(otherPlant.name) &&
				Double.compare(this.tempFahrenheit, otherPlant.tempFahrenheit) == 0 &&
				this.uses.equals(otherPlant.uses);
		}
	}

	@Override
	public String toString() {
		return "name: " + this.name + "\n" +
			"temp: " + this.tempFahrenheit + "°F\n" +
			"uses: " + this.uses;
	}
}
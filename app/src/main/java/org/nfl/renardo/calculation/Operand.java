package org.nfl.renardo.calculation;

//record Operand (int value, String display) {}

final class Operand {
	private final int value;
	private final String display;

	Operand(int value) {
		this.value = value;
		this.display = "";
	}

	Operand(int value, String display) {
		this.value = value;
		this.display = display;
	}

	int getValue() {
		return value;
	}

	String getDisplay() {
		return display;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		if (!(o instanceof Operand))
			return false;
		if (this.getClass() != o.getClass())
			return false;
		Operand operand = (Operand) o;
		return value == operand.value && display.equals(operand.display);
	}

	@Override
	public int hashCode() {
		return 7 * value + 9 * display.hashCode();
	}

	@Override
	public String toString() {
		return display;
	}

}

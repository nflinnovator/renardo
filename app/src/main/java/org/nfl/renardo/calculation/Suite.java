package org.nfl.renardo.calculation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Suite {
	private final int[] value;
	private Calculation calculation;

	private Suite(int... value) {
		this.value = value;
	}

	static Suite from(int... value) {
		if (containsInvalidElements(value)) {
			throw new IllegalArgumentException("A suite must contain only positive numbers starting from 1");
		}
		return new Suite(value);
	}

	void calculate() {
		calculation = Calculation.calculate(this);
	}

	private static boolean containsInvalidElements(int[] value) {
		for (int each : value) {
			if (each <= 0)
				return true;
		}
		return false;
	}

	void showResults() {
		calculation.getResult().forEach(System.out::println);
	}

	void showAllValues() {
		for (Operand each : calculation.getResult()) {
			System.out.println(each.getValue());
		}
	}

	void showAllDisplays() {
		for (Operand each : calculation.getResult()) {
			System.out.println(each.getDisplay());
		}
	}

	boolean containsValue(int value) {
		for (Operand operand : calculation.getResult()) {
			if (operand.getValue() == value) {
				return true;
			}
		}
		return false;
	}

	void showNumberOfDisplaysForValue(int value) {
		List<String> displays = new ArrayList<>();
		for (Operand operand : calculation.getResult()) {
			if (operand.getValue() == value) {
				displays.add(operand.getDisplay());
			}
		}
		System.out.println(displays.size());
	}

	boolean containsDisplay(String display) {
		for (Operand operand : calculation.getResult()) {
			if (operand.getDisplay().equals(display)) {
				return true;
			}
		}
		return false;
	}

	void ShowAllDisplaysForValue(int value) {
		List<String> displays = new ArrayList<>();
		for (Operand operand : calculation.getResult()) {
			if (operand.getValue() == value) {
				displays.add(operand.getDisplay());
			}
		}
		displays.forEach(System.out::println);
	}

	int[] getValue() {
		return value;
	}

	Calculation getCalculation() {
		return calculation;
	}

	int getNumberOfResults() {
		return calculation.getResult().size();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		if (!(o instanceof Suite))
			return false;
		if (this.getClass() != o.getClass())
			return false;
		Suite suite = (Suite) o;
		Arrays.sort(value);
		Arrays.sort(suite.value);
		return Arrays.equals(value, suite.value);
	}

	@Override
	public int hashCode() {
		return Arrays.hashCode(value);
	}

	@Override
	public String toString() {
		return Arrays.toString(value);
	}

}

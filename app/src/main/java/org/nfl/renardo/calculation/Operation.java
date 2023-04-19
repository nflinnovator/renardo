package org.nfl.renardo.calculation;

import java.util.HashSet;
import java.util.Set;

class Operation {
	private final Operand premier;
	private final Operand deuxieme;
	private char sign;
	private int result;
	private String display;
	private Set<Operand> results;

	public Operation(Operand premier) {
		this.premier = premier;
		this.deuxieme = null;
		this.results = new HashSet<>();
	}

	Operation(Operand premier, Operand deuxieme) {
		this.premier = premier;
		this.deuxieme = deuxieme;
		this.results = new HashSet<>();
	}

	Set<Operand> calculate() {
		if (hasOnlyOneOperand()) {
			makeOneOperandCalculation();
		} else {
			makeTwoOperandsCalculation();
		}
		return results;
	}

	private void makeOneOperandCalculation() {
		results.add(premier);
	}

	private void makeTwoOperandsCalculation() {
		makeAddition();
		makeSubtraction();
		makeMultpiplication();
		makeDivision();
	}

	private void makeAddition() {
		results.add(addition());
	}

	private void makeSubtraction() {
		if (isValid(subtraction()))
			results.add(subtraction());
	}

	private void makeMultpiplication() {
		results.add(multiplication());
	}

	private void makeDivision() {
		if (isValid(division()))
			results.add(division());
	}

	private Operand addition() {
		result = add(premier, deuxieme);
		sign = '+';
		display();
		return new Operand(result, display);
	}

	private Operand subtraction() {
		result = subtract(premier, deuxieme);
		sign = '-';
		display();
		return new Operand(result, display);
	}

	private Operand multiplication() {
		result = multiply(premier, deuxieme);
		sign = 'x';
		display();
		return new Operand(result, display);
	}

	private Operand division() {
		result = divide(premier, deuxieme);
		sign = ':';
		display();
		return new Operand(result, display);
	}

	public int add(Operand augend, Operand addend) {
		return augend.getValue() + addend.getValue();
	}

	private int subtract(Operand subtrahend, Operand minuend) {
		return Math.abs(subtrahend.getValue() - minuend.getValue());
	}

	private int multiply(Operand multiplicand, Operand multiplier) {
		return multiplicand.getValue() * multiplier.getValue();
	}

	private int divide(Operand dividend, Operand divider) {
		final var max = max(dividend, divider);
		final var min = min(dividend, divider);
		int division;
		try {
			division = max % min == 0 ? max / min : 0;
		} catch (ArithmeticException exception) {
			division = 0;
		}
		return division;
	}

	private void display() {
		final var max = max(premier, deuxieme);
		final var min = min(premier, deuxieme);
		display = max + " " + sign + " " + min;
		display = result + " = " + display;
		if (hasDisplay(premier)) {
			display += "," + premier.getDisplay();
		}
		if (hasDisplay(deuxieme)) {
			display += "," + deuxieme.getDisplay();
		}
	}

	private boolean hasDisplay(Operand operand) {
		return !operand.getDisplay().isBlank();
	}

	private int max(Operand premier, Operand deuxieme) {
		final var premierValue = premier.getValue();
		final var deuxiemeValue = deuxieme.getValue();
		return Math.max(premierValue, deuxiemeValue) == premierValue ? premierValue : deuxiemeValue;
	}

	private int min(Operand premier, Operand deuxieme) {
		final var premierValue = premier.getValue();
		final var deuxiemeValue = deuxieme.getValue();
		return Math.min(premierValue, deuxiemeValue) == premierValue ? premierValue : deuxiemeValue;
	}

	private boolean isValid(Operand operand) {
		return operand.getValue() > 0 ? true : false;
	}

	private boolean hasOnlyOneOperand() {
		return deuxieme == null;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		if (!(o instanceof Operation))
			return false;
		if (this.getClass() != o.getClass())
			return false;
		Operation operation = (Operation) o;
		return (premier.equals(operation.premier) && deuxieme.equals(operation.deuxieme)
				|| premier.equals(operation.deuxieme) && deuxieme.equals(operation.premier));
	}

	@Override
	public int hashCode() {
		return 7 * premier.hashCode() + 9 * deuxieme.hashCode();
	}

	@Override
	public String toString() {
		return premier.getValue() + " " + sign + " " + deuxieme.getValue();
	}

}
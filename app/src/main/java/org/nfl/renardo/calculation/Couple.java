package org.nfl.renardo.calculation;

import java.util.HashSet;
import java.util.Set;

class Couple {

	private final Operand premier;
	private final Operand deuxieme;
	private Set<Operand> result;

	Couple(Operand premier, Operand deuxieme) {
		this.premier = premier;
		this.deuxieme = deuxieme;
		this.result = new HashSet<>();
	}

	void calculate() {
		result.add(make(new Addition()));
		var operation = make(new Subtraction());
		if (operation.getValue() > 0)
			result.add(operation);
		result.add(make(new Multiplication()));
		operation = make(new Division());
		if (operation.getValue() > 0)
			result.add(operation);
	}

	private Operand make(Operation operation) {
		final int result = operation.make(premier, deuxieme);
		final String format = operation.format(premier, deuxieme);
		final String value = buildOperation(result, format);
		return new Operand(result, value);
	}

	private String buildOperation(int result, String format) {
		String value = result + " = " + format;
		if (!premier.getOperation().isBlank()) {
			value += "," + premier.getOperation();
		}
		if (!deuxieme.getOperation().isBlank()) {
			value += "," + deuxieme.getOperation();
		}
		return value;
	}

	Set<Operand> getResult() {
		return result;
	}

}

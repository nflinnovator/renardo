package org.nfl.renardo.calculation;

import java.util.HashSet;
import java.util.Set;

abstract class Calculation {

	protected final Suite suite;
	protected final int length;
	protected Set<Operand> result;

	protected static final int BASE = 3;

	abstract void perform();

	protected Calculation(Suite suite) {
		this.suite = suite;
		this.length = suite.getValue().length;
		this.result = new HashSet<>();
	}

	static Calculation calculate(Suite suite) {
		return suite.getValue().length < BASE ? basic(suite) : generic(suite);
	}

	private static Calculation basic(Suite suite) {
		final Calculation calculation = new BasicCalculation(suite);
		calculation.perform();
		return calculation;
	}

	private static Calculation generic(Suite suite) {
		final Calculation calculation = new GenericCalculation(suite);
		calculation.perform();
		return calculation;
	}

	Set<Operand> getResult() {
		return Set.copyOf(result);
	}

}

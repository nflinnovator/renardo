package org.nfl.renardo.calculation;

class BasicCalculation extends Calculation {

	BasicCalculation(Suite suite) {
		super(suite);
	}

	@Override
	public void perform() {
		makeOperation();
		collectResult();
	}

	private void makeOperation() {
		final var value = suite.getValue();
		if (length == 1) {
			operation = new Operation(new Operand(value[0]));
		} else {
			operation = new Operation(new Operand(value[0]), new Operand(value[1]));
		}
	}

	private void collectResult() {
		result.addAll(operation.calculate());
	}

}

package org.nfl.renardo.calculation;

import java.util.ArrayList;
import java.util.List;

class BasicCalculation extends Calculation {

	private List<Operand> transformation;

	BasicCalculation(Suite suite) {
		super(suite);
	}

	@Override
	public void perform() {
		if (length == 1) {
			result.add(new Operand(suite.getValue()[0]));
		} else {
			transform();
			calculate();
		}
	}

	private void transform() {
		transformation = new ArrayList<>(length);
		for (int i = 0; i < length; i++) {
			transformation.add(new Operand(suite.getValue()[i]));
		}
	}

	private void calculate() {
		final Couple couple = new Couple(transformation.get(0), transformation.get(1));
		couple.calculate();
		result.addAll(couple.getResult());
	}

}

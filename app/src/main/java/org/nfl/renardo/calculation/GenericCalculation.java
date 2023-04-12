package org.nfl.renardo.calculation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class GenericCalculation extends Calculation {

	private final int numberOfShifts;
	private Set<Shift> shifts;
	private int[] shift;

	private static final int BASIC_NUMBER_OF_SHIFTS = 1;

	GenericCalculation(Suite suite) {
		super(suite);
		this.numberOfShifts = length < BASE ? BASIC_NUMBER_OF_SHIFTS : length;
		shifts = new HashSet<>(numberOfShifts);
		shift = new int[length];
	}

	@Override
	void perform() {
		shift();
		collect();
	}

	private void shift() {
		for (int counter = 0; counter < numberOfShifts; counter++) {
			shift(counter);
			shifts.add(new Shift(Arrays.copyOf(shift, shift.length)));
		}
	}

	private void collect() {
		for (Shift shift : shifts) {
			shift.calculate();
			result.addAll(shift.getResult());
		}
	}

	private void shift(int round) {
		if (round >= 0 && round < numberOfShifts) {
			if (round == 0)
				shift = suite.getValue();
			else {
				final int firstElement = shift[0];
				for (int i = 0; i < shift.length - 1; i++) {
					shift[i] = shift[i + 1];
				}
				shift[shift.length - 1] = firstElement;
			}
		} else {
			throw new IllegalArgumentException("shift invocation has an invalid argument");
		}
	}

	int getNumberOfShifts() {
		return shifts.size();
	}

	boolean hasShift(Shift element) {
		return shifts.contains(element);
	}

}

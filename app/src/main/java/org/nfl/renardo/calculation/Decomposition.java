package org.nfl.renardo.calculation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Decomposition {

	private final int[] reducer;
	private final int[] factor;
	private Map<int[], Calculation> calculations;
	private Set<Couple> composition;
	private Set<Operand> result;

	Decomposition(int[] reducer, int... factor) {
		this.reducer = reducer;
		this.factor = factor;
		this.calculations = new HashMap<>();
		this.composition = new HashSet<>();
		this.result = new HashSet<>();
	}

	void calculate() {
		reduce();
		factorize();
		compose();
		collect();
	}

	private void reduce() {
		final var reduction = Suite.from(reducer);
		reduction.calculate();
		final var calculation = reduction.getCalculation();
		calculations.put(reducer, calculation);
		result.addAll(calculation.getResult());
	}

	private void factorize() {
		final var factorization = Suite.from(factor);
		factorization.calculate();
		final var calculation = factorization.getCalculation();
		calculations.put(factor, calculation);
		if (factor.length > 1)
			result.addAll(calculation.getResult());
	}

	private void compose() {
		for (Operand reductionOperand : calculations.get(reducer).getResult()) {
			for (Operand factorizationOperand : calculations.get(factor).getResult()) {
				composition.add(new Couple(reductionOperand, factorizationOperand));
			}
		}
	}

	private void collect() {
		for (Couple couple : composition) {
			couple.calculate();
			result.addAll(couple.getResult());
		}
	}

	Set<Operand> getResult() {
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		if (!(o instanceof Decomposition))
			return false;
		if (this.getClass() != o.getClass())
			return false;
		Decomposition decomposition = (Decomposition) o;
		Arrays.sort(reducer);
		Arrays.sort(decomposition.reducer);
		Arrays.sort(factor);
		Arrays.sort(decomposition.factor);
		return Arrays.equals(reducer, decomposition.reducer) && Arrays.equals(factor, decomposition.factor)
				|| Arrays.equals(reducer, decomposition.factor) && Arrays.equals(factor, decomposition.reducer);
	}

	@Override
	public int hashCode() {
		return 7 * Arrays.hashCode(reducer) + 9 * Arrays.hashCode(factor);
	}

	@Override
	public String toString() {
		return Arrays.toString(reducer) + " # " + Arrays.toString(factor);
	}

}

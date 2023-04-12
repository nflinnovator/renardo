package org.nfl.renardo.calculation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Shift {

	private final int[] value;
	private final int length;
	private final int numberOfDecompositions;
	private int reducerSize;
	private int factorSize;
	private int[] reducer;
	private int[] factor;
	private Set<Decomposition> decompositions;
	private Set<Operand> result;

	Shift(int... value) {
		this.value = value;
		this.length = value.length;
		this.numberOfDecompositions = length % 2 == 0 ? length / 2 : (length - 1) / 2;
		this.decompositions = new HashSet<>();
		this.result = new HashSet<>();
	}

	void calculate() {
		decompose();
		collect();
	}

	private void decompose() {
		for (int counter = 0; counter < numberOfDecompositions; counter++) {
			reduce(counter);
			factorize(counter);
			Decomposition decomposition = new Decomposition(reducer, factor);
			decompositions.add(decomposition);
		}
	}
	
	private void collect() {
		for (Decomposition decomposition : decompositions) {
			decomposition.calculate();
			result.addAll(decomposition.getResult());
		}
	}

	private void calculateReducerSize(int counter) {
		reducerSize = length % 2 == 0 ? (length / 2 + counter) : ((length + 1) / 2) + counter;
	}

	private void calculateFactorSize(int counter) {
		factorSize = length % 2 == 0 ? (length / 2 - counter) : ((length - 1) / 2) - counter;
	}

	private void reduce(int counter) {
		calculateReducerSize(counter);
		reducer = new int[reducerSize];
		System.arraycopy(value, 0, reducer, 0, reducerSize);
	}

	private void factorize(int counter) {
		calculateFactorSize(counter);
		factor = new int[factorSize];
		System.arraycopy(value, reducerSize, factor, 0, factorSize);
	}

	int getNumberOfDecompositions() {
		return decompositions.size();
	}

	boolean hasDecomposition(Decomposition element) {
		return decompositions.contains(element);
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
		if (!(o instanceof Shift))
			return false;
		if (this.getClass() != o.getClass())
			return false;
		Shift shift = (Shift) o;
		return Arrays.equals(value, shift.value);
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

package org.nfl.renardo.calculation;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@DisplayName("Renardo Calculation Test Case")
class CalculationTest {

	// Suite{a,b,c} = Suite{a,c,b} = Suite{b,c,a} = ...
	// Calculation{a,b,c} != Calculation{a,c,b} != Calculation{b,c,a} = ...
	// Suite calculations = Map<int [shiftedSuite],List<Calculation>>
	// Calculation(int [shiftedSuite])
	// Suite has a list of calculation if value == 2 list has BasicCalculation
	// if value == 3 list contains one LinearCalculation
	// if value > 3 list contains two GenericCalculations (linear and crossed)
	// Suite is a composite
	// Implement CrossCalculation Algorithm
	// Test for a suite of 4 numbers
	// Calculation Iterator

	private Suite suite;

	@Nested
	@DisplayName("Calculation on a suite of one element")
	@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class suiteOfOneElement {

		@BeforeEach
		void createASuiteOfOneElement() {
			suite = Suite.from(1);
		}

		@Test
		@DisplayName("Verify Suite value is initialized with invalid data")
		@Order(1)
		void suiteInitializationWithInvalidData() {
			Exception exception = assertThrows(IllegalArgumentException.class, () -> Suite.from(-1));
			assertEquals("A suite must contain only positive numbers starting from 1", exception.getMessage());
			exception = assertThrows(IllegalArgumentException.class, () -> Suite.from(0));
			assertEquals("A suite must contain only positive numbers starting from 1", exception.getMessage());
		}

		@Test
		@DisplayName("Verify Suite value is initialized with valid data")
		@Order(2)
		void suiteInitializationWithValidData() {
			assertArrayEquals(new int[] { 1 }, suite.getValue());
		}

		@Test
		@DisplayName("Verify Suite equality")
		@Order(3)
		void suiteEquality() {
			var anotherSuite = Suite.from(1);
			assertTrue(suite.equals(anotherSuite));
			anotherSuite = Suite.from(1, 2);
			assertFalse(suite.equals(anotherSuite));
		}

		@Test
		@DisplayName("Verify Calculation mode")
		@Order(4)
		void calculationMode() {
			suite.calculate();
			assertTrue(suite.getCalculation() instanceof BasicCalculation);
		}

		@Test
		@DisplayName("Verify Suite calculation")
		@Order(5)
		void calculation() {
			suite.calculate();
			assertEquals(1, suite.getNumberOfResults());
			assertTrue(suite.calculationContains(new Operand(1)));
		}

	}

	@Nested
	@DisplayName("Calculation on a suite of two elements")
	@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class suiteOfTwoElement {

		@BeforeEach
		void createASuiteOfTwoElement() {
			suite = Suite.from(1, 2);
		}

		@Test
		@DisplayName("Verify Suite value is initialized with invalid data")
		@Order(1)
		void suiteInitializationWithInvalidData() {
			Exception exception = assertThrows(IllegalArgumentException.class, () -> Suite.from(-1, 2));
			assertEquals("A suite must contain only positive numbers starting from 1", exception.getMessage());
			exception = assertThrows(IllegalArgumentException.class, () -> Suite.from(1, 0));
			assertEquals("A suite must contain only positive numbers starting from 1", exception.getMessage());
		}

		@Test
		@DisplayName("Verify Suite value is initialized")
		@Order(2)
		void suiteValueInitialization() {
			assertArrayEquals(new int[] { 1, 2 }, suite.getValue());
		}

		@Test
		@DisplayName("Verify Suite equality")
		@Order(3)
		void suiteEquality() {
			var anotherSuite = Suite.from(1, 2);
			assertTrue(suite.equals(anotherSuite));
			anotherSuite = Suite.from(2, 1);
			assertTrue(suite.equals(anotherSuite));
			anotherSuite = Suite.from(1, 3);
			assertFalse(suite.equals(anotherSuite));
		}

		@Test
		@DisplayName("Verify Calculation mode")
		@Order(4)
		void calculationMode() {
			suite.calculate();
			assertTrue(suite.getCalculation() instanceof BasicCalculation);
		}

		@Test
		@DisplayName("Verify Suite calculation")
		@Order(5)
		void calculation() {
			suite.calculate();
			assertEquals(4, suite.getNumberOfResults());
			assertTrue(suite.calculationContains(new Operand(3, "3 = 2 + 1")));
			assertTrue(suite.calculationContains(new Operand(1, "1 = 2 - 1")));
			assertTrue(suite.calculationContains(new Operand(2, "2 = 2 x 1")));
			assertTrue(suite.calculationContains(new Operand(2, "2 = 2 : 1")));
		}

		@Test
		@DisplayName("Verify Suite calculation does not contain 0s")
		@Order(6)
		void noZeros() {
			suite = Suite.from(2, 2);
			suite.calculate();
			assertEquals(3, suite.getNumberOfResults());
			assertTrue(suite.calculationContains(new Operand(4, "4 = 2 + 2")));
			assertTrue(suite.calculationContains(new Operand(4, "4 = 2 x 2")));
			assertTrue(suite.calculationContains(new Operand(1, "1 = 2 : 2")));
		}

		@Test
		@DisplayName("Verify Suite calculation does not decimals")
		@Order(7)
		void noDecimals() {
			suite = Suite.from(5, 2);
			suite.calculate();
			assertEquals(3, suite.getNumberOfResults());
			assertTrue(suite.calculationContains(new Operand(7, "7 = 5 + 2")));
			assertTrue(suite.calculationContains(new Operand(3, "3 = 5 - 2")));
			assertTrue(suite.calculationContains(new Operand(10, "10 = 5 x 2")));
		}

		@Test
		@DisplayName("Verify Operand Equality")
		@Order(8)
		void operandEquality() {
			final var o1 = new Operand(3, "3 = 2 + 1");
			var o2 = new Operand(3, "3 = 2 + 1");
			assertTrue(o1.equals(o2));
			o2 = new Operand(3, "3 = 4 - 1");
			assertFalse(o1.equals(o2));
		}

	}

	@Nested
	@DisplayName("Calculation on a suite of three elements")
	@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
	class suiteOfThreeElement {

		@BeforeEach
		void createASuiteOfTwoElement() {
			suite = Suite.from(1, 2, 3);
		}

		@Test
		@DisplayName("Verify Suite value is initialized with invalid data")
		@Order(1)
		void suiteInitializationWithInvalidData() {
			Exception exception = assertThrows(IllegalArgumentException.class, () -> Suite.from(-1, 2, 3));
			assertEquals("A suite must contain only positive numbers starting from 1", exception.getMessage());
			exception = assertThrows(IllegalArgumentException.class, () -> Suite.from(1, 2, 0));
			assertEquals("A suite must contain only positive numbers starting from 1", exception.getMessage());
		}

		@Test
		@DisplayName("Verify Suite value is initialized")
		@Order(2)
		void suiteValueInitialization() {
			assertArrayEquals(new int[] { 1, 2, 3 }, suite.getValue());
		}

		@Test
		@DisplayName("Verify Suite equality")
		@Order(3)
		void suiteEquality() {
			var anotherSuite = Suite.from(1, 2, 3);
			assertTrue(suite.equals(anotherSuite));
			anotherSuite = Suite.from(1, 3, 2);
			assertTrue(suite.equals(anotherSuite));
			anotherSuite = Suite.from(2, 1, 3);
			assertTrue(suite.equals(anotherSuite));
			anotherSuite = Suite.from(2, 3, 1);
			assertTrue(suite.equals(anotherSuite));
			anotherSuite = Suite.from(3, 1, 2);
			assertTrue(suite.equals(anotherSuite));
			anotherSuite = Suite.from(3, 2, 1);
			assertTrue(suite.equals(anotherSuite));
			anotherSuite = Suite.from(1, 2, 4);
			assertFalse(suite.equals(anotherSuite));
		}

		@Test
		@DisplayName("Verify Calculation mode")
		@Order(4)
		void calculationMode() {
			suite.calculate();
			assertTrue(suite.getCalculation() instanceof GenericCalculation);
		}

		@Test
		@DisplayName("Verify Suite shifts")
		@Order(5)
		void shift() {
			var calculation = new GenericCalculation(suite);
			calculation.perform();
			assertEquals(3, calculation.getNumberOfShifts());
			assertTrue(calculation.hasShift(new Shift(1, 2, 3)));
			assertTrue(calculation.hasShift(new Shift(2, 3, 1)));
			assertTrue(calculation.hasShift(new Shift(1, 2, 3)));
			calculation = new GenericCalculation(Suite.from(2,2,2));
			calculation.perform();
			assertEquals(1, calculation.getNumberOfShifts());
			assertTrue(calculation.hasShift(new Shift(2, 2, 2)));
		}
		
		@Test
		@DisplayName("Verify Shift equality")
		@Order(6)
		void shiftEquality() {
			var shift = new Shift(1,2,3);
			var anotherShift = new Shift(1,2,3);
			assertTrue(shift.equals(anotherShift));
			anotherShift = new Shift(2,3,1);
			assertFalse(shift.equals(anotherShift));
		}
		
		@Test
		@DisplayName("Verify Shift Decomposition")
		@Order(7)
		void shiftDecomposition() {
			var shift = new Shift(1,2,3);
			shift.calculate();
			assertEquals(1, shift.getNumberOfDecompositions());
			assertTrue(shift.hasDecomposition(new Decomposition(new int [] {1,2}, 3)));
		}
		
		@Test
		@DisplayName("Verify Suite calculation")
		@Order(8)
		void calculation() {
			suite.calculate();
			assertEquals(48, suite.getNumberOfResults());
			assertTrue(suite.calculationContains(new Operand(3, "3 = 2 + 1")));
			assertTrue(suite.calculationContains(new Operand(6, "6 = 3 x 2,2 = 2 x 1")));
			assertTrue(suite.calculationContains(new Operand(9, "9 = 3 x 3,3 = 2 + 1")));
			suite = Suite.from(2,2,2);
			suite.calculate();
			assertEquals(15, suite.getNumberOfResults());
			assertTrue(suite.calculationContains(new Operand(4, "4 = 2 + 2")));
			assertTrue(suite.calculationContains(new Operand(6, "6 = 4 + 2,4 = 2 + 2")));
			assertTrue(suite.calculationContains(new Operand(8, "8 = 4 x 2,4 = 2 x 2")));
		}
		
		/*
		 * @Test
		 * 
		 * @DisplayName("Verify Suite calculation")
		 * 
		 * @Order(5) void calculation() { suite.calculate(); assertEquals(4,
		 * suite.getNumberOfResults()); assertTrue(suite.calculationContains(new
		 * Operand(3, "3 = 2 + 1"))); assertTrue(suite.calculationContains(new
		 * Operand(1, "1 = 2 - 1"))); assertTrue(suite.calculationContains(new
		 * Operand(2, "2 = 2 x 1"))); assertTrue(suite.calculationContains(new
		 * Operand(2, "2 = 2 : 1"))); }
		 * 
		 * @Test
		 * 
		 * @DisplayName("Verify Suite calculation does not contain 0s")
		 * 
		 * @Order(6) void noZeros() { suite = Suite.from(2, 2); suite.calculate();
		 * assertEquals(3, suite.getNumberOfResults());
		 * assertTrue(suite.calculationContains(new Operand(4, "4 = 2 + 2")));
		 * assertTrue(suite.calculationContains(new Operand(4, "4 = 2 x 2")));
		 * assertTrue(suite.calculationContains(new Operand(1, "1 = 2 : 2"))); }
		 * 
		 * @Test
		 * 
		 * @DisplayName("Verify Suite calculation does not decimals")
		 * 
		 * @Order(7) void noDecimals() { suite = Suite.from(5, 2); suite.calculate();
		 * assertEquals(3, suite.getNumberOfResults());
		 * assertTrue(suite.calculationContains(new Operand(7, "7 = 5 + 2")));
		 * assertTrue(suite.calculationContains(new Operand(3, "3 = 5 - 2")));
		 * assertTrue(suite.calculationContains(new Operand(10, "10 = 5 x 2"))); }
		 * 
		 * @Test
		 * 
		 * @DisplayName("Verify Operand Equality")
		 * 
		 * @Order(8) void operandEquality() { final var o1 = new Operand(3,
		 * "3 = 2 + 1"); var o2 = new Operand(3, "3 = 2 + 1");
		 * assertTrue(o1.equals(o2)); o2 = new Operand(3, "3 = 4 - 1");
		 * assertFalse(o1.equals(o2)); }
		 */
	}

	/*
	 * @Test
	 * 
	 * @DisplayName("Check Suite equality") void suiteEquality() { Suite s1 = new
	 * Suite(new int [] {1}); Suite s2 = new Suite(new int [] {1});
	 * assertTrue(s1.equals(s2)); s1 = new Suite(new int [] {1,2}); s2 = new
	 * Suite(new int [] {1,2}); assertTrue(s1.equals(s2)); s2 = new Suite(new int []
	 * {2,1}); assertTrue(s1.equals(s2)); s2 = new Suite(new int [] {1,3});
	 * assertFalse(s1.equals(s2)); s1 = new Suite(new int [] {1,2,3}); s2 = new
	 * Suite(new int [] {1,2,3}); assertTrue(s1.equals(s2)); s2 = new Suite(new int
	 * [] {1,3,2}); assertTrue(s1.equals(s2)); s2 = new Suite(new int [] {2,1,3});
	 * assertTrue(s1.equals(s2)); s2 = new Suite(new int [] {2,3,1});
	 * assertTrue(s1.equals(s2)); s2 = new Suite(new int [] {3,2,1});
	 * assertTrue(s1.equals(s2)); s2 = new Suite(new int [] {3,1,2});
	 * assertTrue(s1.equals(s2)); s2 = new Suite(new int [] {1,2,4});
	 * assertFalse(s1.equals(s2)); }
	 * 
	 * @Test
	 * 
	 * @DisplayName("Verify calculation mode") void verifyCalculationMode() { value
	 * = new int[] { 1 }; suite = new Suite(value); suite.calculate(); calculation =
	 * suite.getCalculation(); assertTrue(calculation instanceof BasicCalculation);
	 * value = new int[] { 1,2 }; suite = new Suite(value); suite.calculate();
	 * calculation = suite.getCalculation(); assertTrue(calculation instanceof
	 * BasicCalculation); value = new int[] { 1,2 }; suite = new Suite(value);
	 * suite.calculate(); calculation = suite.getCalculation();
	 * assertTrue(calculation instanceof BasicCalculation); }
	 * 
	 * @Test
	 * 
	 * @DisplayName("Shift equality") void shiftEquality() { Shift s1 = new
	 * Shift(new int[] { 1, 2 }); Shift s2 = new Shift(new int[] { 1, 2 });
	 * assertTrue(s1.equals(s2)); s2 = new Shift(new int[] { 2, 1 });
	 * assertFalse(s1.equals(s2)); }
	 * 
	 * @Test
	 * 
	 * @DisplayName("Collect all shifts") void shift() { value = new int[] { 1 };
	 * suite = new Suite(value); suite.calculate(); calculation =
	 * suite.getCalculation(); assertEquals(1, calculation);
	 * assertTrue(suite.shiftsContains(new Shift(new int[] { 1 }))); value = new
	 * int[] { 1, 2 }; suite = new Suite(value); suite.shift(); assertEquals(1,
	 * suite.getNumberOfShifts()); assertTrue(suite.shiftsContains(new Shift(new
	 * int[] { 1, 2 }))); value = new int[] { 1, 2, 3 }; suite = new Suite(value);
	 * suite.shift(); assertEquals(3, suite.getNumberOfShifts());
	 * assertTrue(suite.shiftsContains(new Shift(new int[] { 1, 2, 3 })));
	 * assertTrue(suite.shiftsContains(new Shift(new int[] { 2, 3, 1 })));
	 * assertTrue(suite.shiftsContains(new Shift(new int[] { 3, 1, 2 }))); value =
	 * new int[] { 1, 2, 3, 4 }; suite = new Suite(value); suite.shift();
	 * assertEquals(4, suite.getNumberOfShifts());
	 * assertTrue(suite.shiftsContains(new Shift(new int[] { 1, 2, 3, 4 })));
	 * assertTrue(suite.shiftsContains(new Shift(new int[] { 2, 3, 4, 1 })));
	 * assertTrue(suite.shiftsContains(new Shift(new int[] { 3, 4, 1, 2 })));
	 * assertTrue(suite.shiftsContains(new Shift(new int[] { 4, 1, 2, 3 }))); }
	 * 
	 * @Test
	 * 
	 * @DisplayName("Shift decomposition") void shiftDecomposition() { Shift shift =
	 * new Shift(new int[] { 1, 2 }); shift.decompose(); assertEquals(1,
	 * shift.getNumberOfDecompositions());
	 * assertTrue(shift.decompositionsContains(new Decomposition(new int[] { 1 },
	 * new int[] { 2 }))); shift = new Shift(new int[] { 1, 2, 3 });
	 * shift.decompose(); assertEquals(1, shift.getNumberOfDecompositions());
	 * assertTrue(shift.decompositionsContains(new Decomposition(new int[] { 1, 2 },
	 * new int[] { 3 }))); shift = new Shift(new int[] { 1, 2, 3, 4 });
	 * shift.decompose(); assertEquals(2, shift.getNumberOfDecompositions());
	 * assertTrue(shift.decompositionsContains(new Decomposition(new int[] { 1, 2 },
	 * new int[] { 3, 4 }))); assertTrue(shift.decompositionsContains(new
	 * Decomposition(new int[] { 1, 2, 3 }, new int[] { 4 }))); shift = new
	 * Shift(new int[] { 1, 2, 3, 4, 5 }); shift.decompose(); assertEquals(2,
	 * shift.getNumberOfDecompositions());
	 * assertTrue(shift.decompositionsContains(new Decomposition(new int[] { 1, 2, 3
	 * }, new int[] { 4, 5 }))); assertTrue(shift.decompositionsContains(new
	 * Decomposition(new int[] { 1, 2, 3, 4 }, new int[] { 5 }))); shift = new
	 * Shift(new int[] { 1, 2, 3, 4, 5, 6 }); shift.decompose(); assertEquals(3,
	 * shift.getNumberOfDecompositions());
	 * assertTrue(shift.decompositionsContains(new Decomposition(new int[] { 1, 2, 3
	 * }, new int[] { 4, 5, 6 }))); assertTrue(shift.decompositionsContains(new
	 * Decomposition(new int[] { 1, 2, 3, 4 }, new int[] { 5, 6 })));
	 * assertTrue(shift.decompositionsContains(new Decomposition(new int[] { 1, 2,
	 * 3, 4, 5 }, new int[] { 6 }))); shift = new Shift(new int[] { 1, 2, 3, 4, 5,
	 * 6, 7 }); shift.decompose(); assertEquals(3,
	 * shift.getNumberOfDecompositions());
	 * assertTrue(shift.decompositionsContains(new Decomposition(new int[] { 1, 2,
	 * 3, 4 }, new int[] { 5, 6, 7 }))); assertTrue(shift.decompositionsContains(new
	 * Decomposition(new int[] { 1, 2, 3, 4, 5 }, new int[] { 6, 7 })));
	 * assertTrue(shift.decompositionsContains(new Decomposition(new int[] { 1, 2,
	 * 3, 4, 5, 6 }, new int[] { 7 }))); }
	 * 
	 * @Test
	 * 
	 * @DisplayName("Decomposition equality") void decompositionEquality() {
	 * Decomposition d1 = new Decomposition(new int[] { 1, 2 }, new int[] { 3, 4 });
	 * Decomposition d2 = new Decomposition(new int[] { 1, 2 }, new int[] { 3, 4 });
	 * assertTrue(d1.equals(d2)); d2 = new Decomposition(new int[] { 4, 3 }, new
	 * int[] { 2, 1 }); assertTrue(d1.equals(d2)); d2 = new Decomposition(new int[]
	 * { 4, 1 }, new int[] { 2, 3 }); assertFalse(d1.equals(d2)); }
	 * 
	 * @Test
	 * 
	 * @DisplayName("Decomposition reduction and factorization") void
	 * reductionAndFactorization() { Decomposition d = new Decomposition(new int []
	 * {1}, new int [] {2}); d.reduce(); assertEquals(1,d.getNumberOfReductions());
	 * assertTrue(d.reductionsContains(new Operand(1))); d.factorize();
	 * assertEquals(1,d.getNumberOfFactorizations());
	 * assertTrue(d.factorizationsContains(new Operand(2))); }
	 * 
	 * @Test
	 * 
	 * @DisplayName("Calculation on a suite of one number") void
	 * calculationOnASuiteOfOneNumber() { value = new int[] { 10 }; suite = new
	 * Suite(value); suite.calculate(); assertAll("{10}", () ->
	 * assertArrayEquals(new int[] { 10 },
	 * RenardoUtil.toIntegerArray(suite.getAllValues())), () ->
	 * assertArrayEquals(new String[] { "" },
	 * RenardoUtil.toStringArray(suite.getAllOperations()))); }
	 * 
	 * @Test
	 * 
	 * @DisplayName("Calculation on a suite of two numbers") void
	 * calculationOnASuiteOfTwoNumbers() { value = new int[] { 10, 1 }; suite = new
	 * Suite(value); suite.calculate(); assertAll("{10,1}", () ->
	 * assertArrayEquals(new int[] { 11, 9, 10, 10 },
	 * RenardoUtil.toIntegerArray(suite.getAllValues())), () ->
	 * assertArrayEquals(new String[] { "11 = 10 + 1", "9 = 10 - 1", "10 = 10 x 1",
	 * "10 = 10 : 1" }, RenardoUtil.toStringArray(suite.getAllOperations()))); value
	 * = new int[] { 10, 25 }; suite = new Suite(value); suite.calculate();
	 * assertAll("{10,25}", () -> assertArrayEquals(new int[] { 35, 15, 250 },
	 * RenardoUtil.toIntegerArray(suite.getAllValues())), () ->
	 * assertArrayEquals(new String[] { "35 = 25 + 10", "15 = 25 - 10",
	 * "250 = 25 x 10" }, RenardoUtil.toStringArray(suite.getAllOperations())));
	 * value = new int[] { 5, 100 }; suite = new Suite(value); suite.calculate();
	 * assertAll("{5,100}", () -> assertArrayEquals(new int[] { 105, 95, 500, 20 },
	 * RenardoUtil.toIntegerArray(suite.getAllValues())), () -> assertArrayEquals(
	 * new String[] { "105 = 100 + 5", "95 = 100 - 5", "500 = 100 x 5",
	 * "20 = 100 : 5" }, RenardoUtil.toStringArray(suite.getAllOperations())));
	 * value = new int[] { 100, 25 }; suite = new Suite(value); suite.calculate();
	 * assertAll("{100,25}", () -> assertArrayEquals(new int[] { 125, 75, 2500, 4 },
	 * RenardoUtil.toIntegerArray(suite.getAllValues())), () -> assertArrayEquals(
	 * new String[] { "125 = 100 + 25", "75 = 100 - 25", "2500 = 100 x 25",
	 * "4 = 100 : 25" }, RenardoUtil.toStringArray(suite.getAllOperations())));
	 * value = new int[] { 25, 5 }; suite = new Suite(value); suite.calculate();
	 * assertAll("{25,5}", () -> assertArrayEquals(new int[] { 30, 20, 125, 5 },
	 * RenardoUtil.toIntegerArray(suite.getAllValues())), () ->
	 * assertArrayEquals(new String[] { "30 = 25 + 5", "20 = 25 - 5",
	 * "125 = 25 x 5", "5 = 25 : 5" },
	 * RenardoUtil.toStringArray(suite.getAllOperations()))); }
	 * 
	 * @Test
	 * 
	 * @DisplayName("Calculation results on a suite of three numbers") void
	 * calculationResultsOnASuiteOfThreeNumbers() { final int[] value = new int[] {
	 * 5, 100, 25 }; final Suite suite = new Suite(value); suite.calculate();
	 * assertAll("{5,100,25}", () -> assertArrayEquals( new int[] { 105, 95, 500,
	 * 20, 130, 80, 2625, 120, 70, 2375, 525, 475, 12500, 20, 45, 5, 500, 125, 75,
	 * 2500, 4, 130, 120, 625, 25, 80, 70, 375, 15, 2505, 2495, 12500, 500, 9, 1,
	 * 20, 30, 20, 125, 5, 130, 70, 3000, 120, 80, 2000, 5, 225, 25, 12500, 105, 95,
	 * 500, 20 }, RenardoUtil.toIntegerArray(suite.getAllValues())), () ->
	 * assertArrayEquals(new String[] { "105 = 100 + 5", "95 = 100 - 5",
	 * "500 = 100 x 5", "20 = 100 : 5", "130 = 105 + 25,105 = 100 + 5",
	 * "80 = 105 - 25,105 = 100 + 5", "2625 = 105 x 25,105 = 100 + 5",
	 * "120 = 95 + 25,95 = 100 - 5", "70 = 95 - 25,95 = 100 - 5",
	 * "2375 = 95 x 25,95 = 100 - 5", "525 = 500 + 25,500 = 100 x 5",
	 * "475 = 500 - 25,500 = 100 x 5", "12500 = 500 x 25,500 = 100 x 5",
	 * "20 = 500 : 25,500 = 100 x 5", "45 = 25 + 20,20 = 100 : 5",
	 * "5 = 25 - 20,20 = 100 : 5", "500 = 25 x 20,20 = 100 : 5", "125 = 100 + 25",
	 * "75 = 100 - 25", "2500 = 100 x 25", "4 = 100 : 25",
	 * "130 = 125 + 5,125 = 100 + 25", "120 = 125 - 5,125 = 100 + 25",
	 * "625 = 125 x 5,125 = 100 + 25", "25 = 125 : 5,125 = 100 + 25",
	 * "80 = 75 + 5,75 = 100 - 25", "70 = 75 - 5,75 = 100 - 25",
	 * "375 = 75 x 5,75 = 100 - 25", "15 = 75 : 5,75 = 100 - 25",
	 * "2505 = 2500 + 5,2500 = 100 x 25", "2495 = 2500 - 5,2500 = 100 x 25",
	 * "12500 = 2500 x 5,2500 = 100 x 25", "500 = 2500 : 5,2500 = 100 x 25",
	 * "9 = 5 + 4,4 = 100 : 25", "1 = 5 - 4,4 = 100 : 25",
	 * "20 = 5 x 4,4 = 100 : 25", "30 = 25 + 5", "20 = 25 - 5", "125 = 25 x 5",
	 * "5 = 25 : 5", "130 = 100 + 30,30 = 25 + 5", "70 = 100 - 30,30 = 25 + 5",
	 * "3000 = 100 x 30,30 = 25 + 5", "120 = 100 + 20,20 = 25 - 5",
	 * "80 = 100 - 20,20 = 25 - 5", "2000 = 100 x 20,20 = 25 - 5",
	 * "5 = 100 : 20,20 = 25 - 5", "225 = 125 + 100,125 = 25 x 5",
	 * "25 = 125 - 100,125 = 25 x 5", "12500 = 125 x 100,125 = 25 x 5",
	 * "105 = 100 + 5,5 = 25 : 5", "95 = 100 - 5,5 = 25 : 5",
	 * "500 = 100 x 5,5 = 25 : 5", "20 = 100 : 5,5 = 25 : 5" },
	 * RenardoUtil.toStringArray(suite.getAllOperations()))); }
	 */

}

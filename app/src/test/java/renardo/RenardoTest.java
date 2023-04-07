package renardo;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Renardo Calculation Test Case")
public class RenardoTest {

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

        @Test
        void calculationResultsOnTwoNumbers() {
                int[] value = new int[] { 10, 1 };
                Suite suite = Suite.of(value);
                suite.calculate();
                assertArrayEquals(new int[] { 11, 9, 10, 10 }, RenardoUtil.toIntegerArray(suite.getAllValues()));
                value = new int[] { 10, 25 };
                suite = Suite.of(value);
                suite.calculate();
                assertArrayEquals(new int[] { 35, 15, 250 }, RenardoUtil.toIntegerArray(suite.getAllValues()));
                value = new int[] { 5, 100 };
                suite = Suite.of(value);
                suite.calculate();
                assertArrayEquals(new int[] { 105, 95, 500, 20 }, RenardoUtil.toIntegerArray(suite.getAllValues()));
                value = new int[] { 100, 25 };
                suite = Suite.of(value);
                suite.calculate();
                assertArrayEquals(new int[] { 125, 75, 2500, 4 }, RenardoUtil.toIntegerArray(suite.getAllValues()));
                value = new int[] { 25, 5 };
                suite = Suite.of(value);
                suite.calculate();
                assertArrayEquals(new int[] { 30, 20, 125, 5 }, RenardoUtil.toIntegerArray(suite.getAllValues()));
        }

        @Test
        void calculationOperationsOnTwoNumbers() {
                int[] value = new int[] { 10, 1 };
                Suite suite = Suite.of(value);
                suite.calculate();
                assertArrayEquals(new String[] { "11 = 10 + 1", "9 = 10 - 1", "10 = 10 x 1", "10 = 10 : 1" },
                                RenardoUtil.toStringArray(suite.getAllOperations()));
                value = new int[] { 10, 25 };
                suite = Suite.of(value);
                suite.calculate();
                assertArrayEquals(new String[] { "35 = 10 + 25", "15 = 25 - 10", "250 = 10 x 25" },
                                RenardoUtil.toStringArray(suite.getAllOperations()));
                value = new int[] { 5, 100 };
                suite = Suite.of(value);
                suite.calculate();
                assertArrayEquals(new String[] { "105 = 5 + 100", "95 = 100 - 5", "500 = 5 x 100", "20 = 100 : 5" },
                                RenardoUtil.toStringArray(suite.getAllOperations()));
                value = new int[] { 100, 25 };
                suite = Suite.of(value);
                suite.calculate();
                assertArrayEquals(new String[] { "125 = 100 + 25", "75 = 100 - 25", "2500 = 100 x 25", "4 = 100 : 25" },
                                RenardoUtil.toStringArray(suite.getAllOperations()));
                value = new int[] { 25, 5 };
                suite = Suite.of(value);
                suite.calculate();
                assertArrayEquals(new String[] { "30 = 25 + 5", "20 = 25 - 5", "125 = 25 x 5", "5 = 25 : 5" },
                                RenardoUtil.toStringArray(suite.getAllOperations()));

        }

        @Test
        void calculationValidResultsOnTwoNumbers() {
                int[] value = new int[] { 10, 1 };
                Suite suite = Suite.of(value);
                suite.calculate();
                assertArrayEquals(new int[] {}, RenardoUtil.toIntegerArray(suite.getAllValidValues()));
                value = new int[] { 10, 25 };
                suite = Suite.of(value);
                suite.calculate();
                assertArrayEquals(new int[] { 250 }, RenardoUtil.toIntegerArray(suite.getAllValidValues()));
                value = new int[] { 5, 100 };
                suite = Suite.of(value);
                suite.calculate();
                assertArrayEquals(new int[] { 105, 500 }, RenardoUtil.toIntegerArray(suite.getAllValidValues()));
                value = new int[] { 100, 25 };
                suite = Suite.of(value);
                suite.calculate();
                assertArrayEquals(new int[] { 125 }, RenardoUtil.toIntegerArray(suite.getAllValidValues()));
                value = new int[] { 25, 5 };
                suite = Suite.of(value);
                suite.calculate();
                assertArrayEquals(new int[] { 125 }, RenardoUtil.toIntegerArray(suite.getAllValidValues()));
        }

        @Test
        void calculationValidOperationsOnTwoNumbers() {
                int[] value = new int[] { 10, 1 };
                Suite suite = Suite.of(value);
                suite.calculate();
                assertArrayEquals(new String[] {},
                                RenardoUtil.toStringArray(suite.getAllValidOperations()));
                value = new int[] { 10, 25 };
                suite = Suite.of(value);
                suite.calculate();
                assertArrayEquals(new String[] { "250 = 10 x 25" },
                                RenardoUtil.toStringArray(suite.getAllValidOperations()));
                value = new int[] { 5, 100 };
                suite = Suite.of(value);
                suite.calculate();
                assertArrayEquals(new String[] { "105 = 5 + 100", "500 = 5 x 100" },
                                RenardoUtil.toStringArray(suite.getAllValidOperations()));
                value = new int[] { 100, 25 };
                suite = Suite.of(value);
                suite.calculate();
                assertArrayEquals(new String[] { "125 = 100 + 25" },
                                RenardoUtil.toStringArray(suite.getAllValidOperations()));
                value = new int[] { 25, 5 };
                suite = Suite.of(value);
                suite.calculate();
                assertArrayEquals(new String[] { "125 = 25 x 5" },
                                RenardoUtil.toStringArray(suite.getAllValidOperations()));

        }

        @Test
        void calculationResultsOnThreeNumbers() {
                int[] value = new int[] { 5, 100, 25 };
                Suite suite = Suite.of(value);
                suite.calculate();
                assertArrayEquals(
                                new int[] { 105, 95, 500, 20, 130, 80, 2625, 120, 70, 2375, 525, 475, 12500, 20, 45, 5,
                                                500, 125, 75, 2500, 4, 130, 120, 625, 25, 80, 70, 375, 15, 2505, 2495,
                                                12500, 500, 9, 1, 20, 30, 20, 125, 5, 130, 70, 3000, 120, 80, 2000, 5,
                                                225, 25,
                                                12500, 105, 95, 500, 20 },
                                RenardoUtil.toIntegerArray(suite.getAllValues()));
        }

        @Test
        void calculationOperationsOnThreeNumbers() {
                int[] value = new int[] { 5, 100, 25 };
                Suite suite = Suite.of(value);
                suite.calculate();
                assertArrayEquals(
                                new String[] { "105 = 5 + 100", "95 = 100 - 5", "500 = 5 x 100", "20 = 100 : 5",
                                                "130 = 105 + 25,105 = 5 + 100", "80 = 105 - 25,105 = 5 + 100",
                                                "2625 = 105 x 25,105 = 5 + 100", "120 = 95 + 25,95 = 100 - 5",
                                                "70 = 95 - 25,95 = 100 - 5",
                                                "2375 = 95 x 25,95 = 100 - 5", "525 = 500 + 25,500 = 5 x 100",
                                                "475 = 500 - 25,500 = 5 x 100",
                                                "12500 = 500 x 25,500 = 5 x 100", "20 = 500 : 25,500 = 5 x 100",
                                                "45 = 20 + 25,20 = 100 : 5", "5 = 25 - 20,20 = 100 : 5",
                                                "500 = 20 x 25,20 = 100 : 5", "125 = 100 + 25", "75 = 100 - 25",
                                                "2500 = 100 x 25", "4 = 100 : 25",
                                                "130 = 125 + 5,125 = 100 + 25", "120 = 125 - 5,125 = 100 + 25",
                                                "625 = 125 x 5,125 = 100 + 25", "25 = 125 : 5,125 = 100 + 25",
                                                "80 = 75 + 5,75 = 100 - 25",
                                                "70 = 75 - 5,75 = 100 - 25", "375 = 75 x 5,75 = 100 - 25",
                                                "15 = 75 : 5,75 = 100 - 25",
                                                "2505 = 2500 + 5,2500 = 100 x 25", "2495 = 2500 - 5,2500 = 100 x 25",
                                                "12500 = 2500 x 5,2500 = 100 x 25", "500 = 2500 : 5,2500 = 100 x 25",
                                                "9 = 4 + 5,4 = 100 : 25", "1 = 5 - 4,4 = 100 : 25",
                                                "20 = 4 x 5,4 = 100 : 25", "30 = 25 + 5", "20 = 25 - 5", "125 = 25 x 5",
                                                "5 = 25 : 5",
                                                "130 = 30 + 100,30 = 25 + 5", "70 = 100 - 30,30 = 25 + 5",
                                                "3000 = 30 x 100,30 = 25 + 5",
                                                "120 = 20 + 100,20 = 25 - 5", "80 = 100 - 20,20 = 25 - 5",
                                                "2000 = 20 x 100,20 = 25 - 5",
                                                "5 = 100 : 20,20 = 25 - 5", "225 = 125 + 100,125 = 25 x 5",
                                                "25 = 125 - 100,125 = 25 x 5",
                                                "12500 = 125 x 100,125 = 25 x 5", "105 = 5 + 100,5 = 25 : 5",
                                                "95 = 100 - 5,5 = 25 : 5", "500 = 5 x 100,5 = 25 : 5",
                                                "20 = 100 : 5,5 = 25 : 5" },
                                RenardoUtil.toStringArray(suite.getAllOperations()));
        }

        @Test
        void calculationValidResultsOnThreeNumbers() {
                int[] value = new int[] { 5, 100, 25 };
                Suite suite = Suite.of(value);
                suite.calculate();
                assertArrayEquals(
                                new int[] { 105, 500, 130, 120, 525, 475, 500, 125, 130, 120, 625, 375, 500, 125, 130,
                                                120, 225, 105, 500 },
                                RenardoUtil.toIntegerArray(suite.getAllValidValues()));
        }

        @Test
        void singleRoundCalculationValidOperationsOnThreeNumbers() {
                int[] value = new int[] { 5, 100, 25 };
                Suite suite = Suite.of(value);
                suite.calculate();
                assertArrayEquals(
                                new String[] { "105 = 5 + 100", "500 = 5 x 100", "130 = 105 + 25,105 = 5 + 100",
                                                "120 = 95 + 25,95 = 100 - 5",
                                                "525 = 500 + 25,500 = 5 x 100", "475 = 500 - 25,500 = 5 x 100",
                                                "500 = 20 x 25,20 = 100 : 5", "125 = 100 + 25",
                                                "130 = 125 + 5,125 = 100 + 25",
                                                "120 = 125 - 5,125 = 100 + 25",
                                                "625 = 125 x 5,125 = 100 + 25", "375 = 75 x 5,75 = 100 - 25",
                                                "500 = 2500 : 5,2500 = 100 x 25", "125 = 25 x 5",
                                                "130 = 30 + 100,30 = 25 + 5",
                                                "120 = 20 + 100,20 = 25 - 5",
                                                "225 = 125 + 100,125 = 25 x 5", "105 = 5 + 100,5 = 25 : 5",
                                                "500 = 5 x 100,5 = 25 : 5" },
                                RenardoUtil.toStringArray(suite.getAllValidOperations()));
        }

}

package renardo;

import java.util.ArrayList;
import java.util.List;

class Suite {

    private int[] value;

    private Suite(int[] value) {
        this.value = value;
    }

    static Suite of(int[] value) {
        return new Suite(value);
    }

    int[] calculate() {
        return validate(Calculation.of(value).perform());
        // return value.length == 2 ? BasicCalculation.of(value).perform()
        // GenericCalculation.of(value).perform();
    }

    private int[] validate(int[] resultWithNoDuplicates) {
        List<Integer> validResultList = new ArrayList<>();
        for (int i = 0; i < resultWithNoDuplicates.length; i++) {
            if (resultWithNoDuplicates[i] > 99 && resultWithNoDuplicates[i] < 1000)
                validResultList.add(resultWithNoDuplicates[i]);
        }
        return RenardoUtil.fromList(validResultList);
    }

    /*
     * @Override
     * public boolean equals(Object o) {
     * if (this == o)
     * return true;
     * if (o == null)
     * return false;
     * if (!(o instanceof Suite))
     * return false;
     * if (this.getClass() != o.getClass())
     * return false;
     * Suite suite = (Suite) o;
     * sort(this.value);
     * sort(suite.value);
     * if (this.calculation != null)
     * sort(this.calculation);
     * if (suite.calculation != null)
     * sort(suite.calculation);
     * return Arrays.equals(this.value, suite.value)
     * && Arrays.equals(this.calculation, suite.calculation);
     * }
     * 
     * @Override
     * public int hashCode() {
     * return 7 * this.value.hashCode() + 9 * this.calculation.hashCode();
     * }
     * 
     * @Override
     * public String toString() {
     * return "Suite Value : " + this.value + "\n" + "Suite Calculation : " +
     * this.calculation;
     * }
     */

}

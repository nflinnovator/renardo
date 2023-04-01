package renardo;

import java.util.Arrays;

class Collector {
    private int[] value;

    Collector collect(int[] sortedResult) {
        if (this.value == null)
            this.value = new int[0];
        int[] newValue = new int[this.value.length + sortedResult.length];
        System.arraycopy(this.value, 0, newValue, 0, this.value.length);
        System.arraycopy(sortedResult, 0, newValue, this.value.length, sortedResult.length);
        this.value = newValue;
        sort(this.value);
        return this;
    }

    private void sort(int[] array) {
        Arrays.sort(array);
    }

    int[] getValue() {
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (!(o instanceof Collector))
            return false;
        if (this.getClass() != o.getClass())
            return false;
        Collector collector = (Collector) o;
        return this.value == collector.value;
    }

    @Override
    public int hashCode() {
        return 7 * this.value.hashCode();
    }

    @Override
    public String toString() {
        return "Collector Value : " + this.value.toString();
    }
}

package renardo;

import java.util.Arrays;
import java.util.List;

class RenardoUtil {

    static int[] fromList(List<Integer> list) {
        int[] array = new int[list.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    void sort(int[] array) {
        Arrays.sort(array);
    }

}

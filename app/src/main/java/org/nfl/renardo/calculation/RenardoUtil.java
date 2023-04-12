package org.nfl.renardo.calculation;

import java.util.Arrays;
import java.util.List;

class RenardoUtil {

    static int[] toIntegerArray(List<Integer> list) {
        int[] array = new int[list.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    static String[] toStringArray(List<String> list) {
        String[] array = new String[list.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    static void sort(int[] array) {
        Arrays.sort(array);
    }

}

package renardo;

import java.util.ArrayList;
import java.util.List;

public class Calculator {
    List<Result> results = new ArrayList<Result>();
    int [] suite;
    int target;

    Calculator(int [] suite,int target){
        this.suite = suite;
        this.target = target;
    }

    int numberOfResults(){
        return 1;
    }

}

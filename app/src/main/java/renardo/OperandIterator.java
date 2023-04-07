package renardo;

import java.util.Iterator;
import java.util.List;

interface OperandIterator extends Iterator<Operand>{

    List<Integer> getAllValues();

    List<String> getAllOperations();

    List<Integer> getAllValidValues();

    List<String> getAllValidOperations();
    
}

package renardo;

class Result {
    int value;
    Calculation calculation;

    Result(int value, Calculation calculation){
      this.value = value;
      this.calculation = calculation;
    }

    @Override
    public boolean equals(Object object) {
        Result result = (Result) object;
        return this.value == result.value
        && this.calculation.equals(result.calculation);
    }

    @Override
    public String toString(){
        return calculation+" = "+value; 
    }
}

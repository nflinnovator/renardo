package renardo;

class Calculation {
    int firstNumber;
    int secondNumber;
    char operand;

    Calculation(int firstNumber,int secondNumber){
     this.firstNumber = firstNumber;
     this.secondNumber = secondNumber;
    }
    
    Result add(){
        int result = this.firstNumber + this.secondNumber;
        this.operand = '+';
        return new Result(result, this);
    }
    
    Result substract(){
        int result = this.firstNumber - this.secondNumber;
        this.operand = '-';
        return new Result(result, this);
    }
    
    Result multiply(){
        int result = this.firstNumber * this.secondNumber;
        this.operand = 'x';
        return new Result(result, this);
    }

    Result divide(){
        int result = this.firstNumber / this.secondNumber;
        this.operand = ':';
        return new Result(result, this);
    }

    @Override
    public boolean equals(Object object) {
        Calculation calculation = (Calculation) object;
        return firstNumber == calculation.firstNumber
        && this.secondNumber == calculation.secondNumber
        && this.operand == calculation.operand;
    }

    @Override
    public String toString(){
        return firstNumber +" "+operand+" "+secondNumber; 
    }
}

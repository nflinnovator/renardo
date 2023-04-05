package renardo;

class Operand {
    private int value;
    private String operation;

    Operand(int value) {
        this.value = value;
        this.operation = "";
    }

    Operand(int value, String operation) {
        this.value = value;
        this.operation = operation;
    }

    public int getValue() {
        return value;
    }

    public String getOperation() {
        return operation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (!(o instanceof Operand))
            return false;
        if (this.getClass() != o.getClass())
            return false;
        Operand nombre = (Operand) o;
        return value == nombre.getValue()
                && operation.equals(nombre.getOperation());
    }

    @Override
    public int hashCode() {
        return 7 * value + 9 * operation.hashCode();
    }

    @Override
    public String toString() {
        return operation;
    }

}

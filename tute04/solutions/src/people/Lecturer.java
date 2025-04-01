package people;

import java.util.Map;

public class Lecturer extends Person {
    public static final Map<String, Integer> payRates = Map.of(
        "LVL0", 0,
        "LVL1", 1000,
        "LVL2", 2000,
        "LVL3", 3000
    );

    private int salary;
    
    public Lecturer(String name, int age, String payRate) {
        super(name, age);
        setSalary(payRate);
    }
    
    public int getSalary() {
        return salary;
    }

    /**
     * Sets the salary of a person given their pay rate
     * @param payRate New pay rate of the person
     * @precondition payRate is a valid pay rate label
     * @postcondition salary is set to the correct value
     */
    public void setSalary(String payRate) {
        Integer pay = payRates.get(payRate);
        salary = pay;
    }
}

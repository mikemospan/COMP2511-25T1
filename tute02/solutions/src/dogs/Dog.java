package dogs;

public abstract class Dog {
    private String colour;

    public Dog(String colour) {
        this.colour = colour;
    }

    public String getColour() {
        return colour;
    }

    public abstract void bark();
    
}
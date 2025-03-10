package dogs;

public class Greyhound implements Dog {
    private String colour;
    private int speed;

    public Greyhound(String colour) {
        this.colour = colour;
    }

    public String getColour() {
        return colour;
    }

    public int getSpeed() {
        return speed;
    }

    @Override
    public void bark() {
        System.out.println("woof woof!");
    }

    public void sleepOnCouch() {
        System.out.println("Having a nap on the couch!");
    }
   
}

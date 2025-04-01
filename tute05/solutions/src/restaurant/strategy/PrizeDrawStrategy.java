package restaurant.strategy;

import restaurant.Meal;

import java.util.List;

public class PrizeDrawStrategy implements ChargingStrategy {
    private int numCustomers = 0;

    @Override
    public double cost(List<Meal> order, boolean payeeIsMember) {
        numCustomers++;
        if (numCustomers % 100 == 0) {
            return 0;
        } else {
            return order.stream().mapToDouble(meal -> meal.getCost()).sum();
        }
    }

    @Override
    public double standardChargeModifier() {
        return 1;
    }
}

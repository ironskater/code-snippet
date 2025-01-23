package snippetlab.java.design_pattern.decorator;

public class SimpleCoffee implements Coffee {

    @Override
    public double getCost() {

        return 1.0;
    }

    @Override
    public String getDescription() {

        return "Simple Coffee";
    }
}
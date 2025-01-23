package snippetlab.java.design_pattern.decorator;

public abstract class CoffeeDecorator implements Coffee {

    protected Coffee coffee;

    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public double getCost() {

        return this.coffee.getCost();
    }

    @Override
    public String getDescription() {

        return this.coffee.getDescription();
    }
}

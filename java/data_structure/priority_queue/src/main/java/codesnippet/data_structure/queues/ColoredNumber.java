package codesnippet.data_structure.queues;

public class ColoredNumber {

    private int value;
    private String color;

    public ColoredNumber() {}

    public ColoredNumber(int value, String color) {
        this.value = value;
        this.color = color;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
 }
package codesnippet.data_structure.queues;

public class ColoredNumberWithComparable implements Comparable<ColoredNumberWithComparable> {

    private int value;
    private String color;

    public ColoredNumberWithComparable() {}

    public ColoredNumberWithComparable(int value, String color) {
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

    /*
     * This will grant that every item will be ordered considering the “red” color first and then
     * the value in a natural ordering, meaning that all red-colored objects will be returned first:
     */
    @Override
    public int compareTo(ColoredNumberWithComparable o) {
        if ((this.color.equals("red") && o.color.equals("red")) ||
            (!this.color.equals("red") && !o.color.equals("red"))) {
            return Integer.compare(this.value, o.value);
        }
        else if (this.color.equals("red")) {
            return -1;
        }
        else {
            return 1;
        }
    }
 }
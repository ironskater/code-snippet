package codesnippet.enumtype;

public class App
{
    public static void main(String[] args) {

        Color red = new Color("red", 0);
        Color green = new Color("green", 1);
        WeekDay monday = new WeekDay("monday", 2);

        System.out.println("compare red and green: " + red.compareTo(green));

        // Can't compare Color and WeekDay thanks to they extends the same base class but different generic
        // Color extends BaseEnum<Color>
        // WeekDay extends BaseEnum<WeekDay>
        System.out.println("compare red and green: " + red.compareTo(monday));
    }
}

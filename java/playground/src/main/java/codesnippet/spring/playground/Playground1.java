package codesnippet.spring.playground;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Playground1 {

    private static class Item() {

    }

    public static void main(String[] args) throws Exception {

        // Create a BigDecimal object
        BigDecimal a;

        // Create a String object
        String s;

        a = new BigDecimal(500000);

        DecimalFormat decimalFormat = new DecimalFormat("#,###.########");

        // apply toPlainString() method
        s = a.toPlainString();

        // print the result
        System.out.println(s);
        System.out.println(decimalFormat.format(a));
    }
}

package codesnippet.data_structure.queues;

import java.util.PriorityQueue;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public final class PriorityQueueTest {

    @Test
    public void byComparable() {

        PriorityQueue<ColoredNumberWithComparable> queue = new PriorityQueue<>();

        queue.add(new ColoredNumberWithComparable(2, "blue"));
        queue.add(new ColoredNumberWithComparable(1, "blue"));
        queue.add(new ColoredNumberWithComparable(20, "red"));
        queue.add(new ColoredNumberWithComparable(10, "red"));

        /*
         * In this example, red has high priority
         * then compare by nature property
         */
        ColoredNumberWithComparable first = queue.poll();
        Assertions.assertThat(first.getColor()).isEqualTo("red");
        Assertions.assertThat(first.getValue()).isEqualTo(10);

        queue.poll();

        ColoredNumberWithComparable third = queue.poll();
        Assertions.assertThat(third.getColor()).isEqualTo("blue");
        Assertions.assertThat(third.getValue()).isEqualTo(1);
    }

    @Test
    public void byLambda() {

        PriorityQueue<ColoredNumber> queue = new PriorityQueue<>(
            (current, incoming) -> {
                if ((current.getColor().equals("red") && incoming.getColor().equals("red")) ||
                    (!current.getColor().equals("red") && !incoming.getColor().equals("red"))) {
                    return Integer.compare(current.getValue(), incoming.getValue());
                }
                else if (current.getColor().equals("red")) {
                    return -1;
                }
                else {
                    return 1;
                }
            }
        );

        queue.add(new ColoredNumber(2, "blue"));
        queue.add(new ColoredNumber(1, "blue"));
        queue.add(new ColoredNumber(20, "red"));
        queue.add(new ColoredNumber(10, "red"));

        /*
         * In this example, red has high priority
         * then compare by nature property
         */
        ColoredNumber first = queue.poll();
        Assertions.assertThat(first.getColor()).isEqualTo("red");
        Assertions.assertThat(first.getValue()).isEqualTo(10);

        queue.poll();

        ColoredNumber third = queue.poll();
        Assertions.assertThat(third.getColor()).isEqualTo("blue");
        Assertions.assertThat(third.getValue()).isEqualTo(1);
    }
}

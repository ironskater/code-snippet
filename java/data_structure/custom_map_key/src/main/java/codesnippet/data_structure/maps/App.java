package codesnippet.data_structure.maps;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class App
{
    private static class Coordinate {
        private final int x;
        private final int y;
        private int hashCode;
        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
            this.hashCode = Objects.hash(x, y);
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public boolean equals(Object obj) {
            if(this == obj) {
                return true;
            }

            if(obj == null || getClass() != obj.getClass()) {
                return false;
            }

            Coordinate that = (Coordinate) obj;
            return this.x == that.x && this.y == that.y;
        }

        @Override
        public int hashCode() {
            return this.hashCode;
        }
    }

	public static void
		main(String[] args)
	{
        Map<Coordinate, String> pixels = new HashMap<>();

        Coordinate coord1 = new Coordinate(1, 2);
        pixels.put(coord1, "Pixel1");

        Coordinate coord2 = new Coordinate(4, 3);
        pixels.put(coord2, "Pixel2");

        System.out.println(pixels.get(coord1));
        System.out.println(pixels.get(coord2));
	}
}

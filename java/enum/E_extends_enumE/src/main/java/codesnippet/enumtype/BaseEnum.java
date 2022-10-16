package codesnippet.enumtype;

public abstract class BaseEnum<E extends BaseEnum<E>> implements Comparable<E> {

    private final String name;
    private final int ordinal;

    protected BaseEnum(String name, int ordinal) {
        this.name = name;
        this.ordinal = ordinal;
    }

    public final String name() {
        return name;
    }
    public final int ordinal() {
        return ordinal;
    }

    @Override
    public int compareTo(E o) {
        return ordinal - o.ordinal();
    }
}

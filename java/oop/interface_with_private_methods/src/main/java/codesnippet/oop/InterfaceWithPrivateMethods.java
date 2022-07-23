package codesnippet.oop;

public interface InterfaceWithPrivateMethods
{
    private static String staticPrivate()
    {
        return "static private";
    }

    private String instancePrivate()
    {
        return "instance private";
    }

    default void print(String str)
    {
        System.out.println(str);
    }

    default void check()
    {
        print(InterfaceWithPrivateMethods.staticPrivate());
        print(this.instancePrivate());
    }

    public void invoke();
}

package codesnippet.oop;

public class App
{
    public static void
        main( String[] args )
    {
        InterfaceWithPrivateMethods obj =
            new InterfaceWithPrivateMethods(){
                @Override
                public void invoke() {
                    System.out.println("instance public");
                };
            };

        obj.invoke();
        obj.check();
    }
}

package codesnippet.functional_interface.supplier;

public class FooCustomer {

    private long id;
    private String fooName;

    public FooCustomer(long id, String fooName) {
        this.id = id;
        this.fooName = fooName;
    }

    public String getIdAndName() {
        return "fooCustomer: " + id + fooName;
    }
}

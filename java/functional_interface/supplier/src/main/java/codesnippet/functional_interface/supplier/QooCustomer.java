package codesnippet.functional_interface.supplier;

public class QooCustomer {

    private long id;
    private String qooName;

    public QooCustomer(long id, String qooName) {
        this.id = id;
        this.qooName = qooName;
    }

    public String getInfo() {
        return "qooCustomer: " + id + qooName;
    }
}

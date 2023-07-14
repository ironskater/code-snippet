package codesnippet.functional_interface.supplier;

import java.util.function.Supplier;

public class App
{
	public static void main( String[] args )
	{
        Supplier<FooCustomer> source1 = get(1, FooCustomer.class);

        System.out.println(source1.get().getIdAndName());
        System.out.println(source1.get().getIdAndName());
        System.out.println(source1.get().getIdAndName());
        System.out.println(source1.get().getIdAndName());
        System.out.println(source1.get().getIdAndName());
        System.out.println(source1.get().getIdAndName());

        Supplier<QooCustomer> source2 = get(56, QooCustomer.class);
        System.out.println(source2.get().getInfo());
        System.out.println(source2.get().getInfo());
        System.out.println(source2.get().getInfo());
        System.out.println(source2.get().getInfo());
	}

    private static <T> Supplier<T> get(long id, Class<T> customerType) {

        if (FooCustomer.class.equals(customerType)) {
            return (Supplier)new FooCustomerSupplier(id);
        } else if (QooCustomer.class.equals(customerType)) {
            return (Supplier)new QooCustomerSupplier(id);
        }

        throw new IllegalArgumentException("Unknown customer type: " + customerType);
    }

    private static class FooCustomerSupplier implements Supplier<FooCustomer> {

        private long id;

        public FooCustomerSupplier(long id) {
            this.id = id;
        }

        public FooCustomer get() {
            return new FooCustomer(this.id++, "fooer");
        }
    }

    private static class QooCustomerSupplier implements Supplier<QooCustomer> {

        private long id;

        public QooCustomerSupplier(long id) {
            this.id = id;
        }

        public QooCustomer get() {
            return new QooCustomer(this.id++, "qooer");
        }
    }
}

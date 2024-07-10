package codesnippet.miscellaneous;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class TimingDynamicInvocationHandler implements InvocationHandler {

    private final Map<String, Method> methods = new HashMap<>();

    private Object target;

    public TimingDynamicInvocationHandler(Object target) {

        this.target = target;

        for (Method method : this.target.getClass().getDeclaredMethods()) {

            this.methods.put(method.getName(), method);
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        long start = System.nanoTime();

        Object result = this.methods.get(method.getName()).invoke(this.target, args);

        System.out.println(String.format("Time elapsed ns: %d",
            System.nanoTime() - start));

        return result;
    }
}

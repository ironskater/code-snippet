package codesnippet.miscellaneous;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class App {

    public static void main(String[] args) {

        Map proxyInstance = (Map) Proxy.newProxyInstance(
            // 被代理的物件類別
            App.class.getClassLoader(),
            // 被代理的物件其所實作的介面
            new Class[] {Map.class},
            new TimingDynamicInvocationHandler(new HashMap<>()));

        proxyInstance.put("hello", "world");
    }
}

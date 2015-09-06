package br.com.simple.tests.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {

    public static void main(String[] args) {
        ClassLoader classLoader = ProxyTest.class.getClassLoader();
        ProxyInterface proxyInterface = (ProxyInterface) Proxy.newProxyInstance(classLoader, new Class[]{ProxyInterface.class}, new ClassInvocationHandler(new ImplClass()));

        for (Method method : proxyInterface.getClass().getMethods()) {
            System.out.println(method.getName());
        }
    }

    public interface ProxyInterface {
        void run();
    }

    public static class ImplClass implements ProxyInterface {
        @Override
        public void run() {
        }

        public void run2() {
        }
    }


    public static class ClassInvocationHandler implements InvocationHandler {
        private Object obj;

        public ClassInvocationHandler(Object obj) {
            this.obj = obj;
        }

        @Override
        public Object invoke(Object o, Method method, Object[] args) throws Throwable {
            return method.invoke(obj, args);
        }
    }
}

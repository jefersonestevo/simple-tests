package br.com.simple.tests.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

public class CglibProxyTest {

    public static void main(String[] args) {
        ImplClass obj = new ImplClass();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ImplClass.class);
        enhancer.setCallback(new ClassMethodInterceptor(obj));
        ImplClass finalClass = (ImplClass) enhancer.create();

        for (Method method : finalClass.getClass().getSuperclass().getMethods()) {
            System.out.print(method.getName());
            System.out.print(" " + Arrays.toString(method.getAnnotations()));
            System.out.println();
        }
    }

    public interface ProxyInterface {
        void run();
    }

    public static class ImplClass implements ProxyInterface {
        @Override
        public void run() {
        }

        @CglibProxyMethodAnnotation
        public void run2() {
        }
    }


    public static class ClassMethodInterceptor implements MethodInterceptor {
        private Object obj;

        public ClassMethodInterceptor(Object obj) {
            this.obj = obj;
        }

        @Override
        public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
            System.out.println("Cglib proxy: " + methodProxy.getSignature());
            return methodProxy.invokeSuper(obj, args);
        }
    }

}

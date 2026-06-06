package work3;

class B {
//设计两个类A、B，在类A的主方法main里创建一个类B的匿名内部类对象，覆盖B中的方法f，并调用此方法。
    public void f() {
        System.out.println("这是类B的原始方法f()");
    }
}

public class A {
    public static void main(String[] args) {
        B b = new B()
        {
            @Override
            public void f() {
                System.out.println("这是匿名内部类中重写的方法f()");
            }
        };

        b.f();
    }
}


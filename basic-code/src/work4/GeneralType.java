package work4;

// 定义一个泛型类 GeneralType，其中 T 是类型参数
class GeneralType<T>
{
    // 声明一个类型为 T 的成员变量 obj，用于存储泛型对象
    T obj;

    // 构造方法，接收一个类型为 T 的参数并赋值给成员变量 obj
    GeneralType(T obj)
    {
        this.obj = obj;
    }

    // 获取存储的泛型对象
    public T getObject()
    {
        return obj;
    }

    // 打印当前存储对象的实际类名
    public void print(){
        System.out.println("GeneralType is:"+ obj.getClass().getName());
    }
}

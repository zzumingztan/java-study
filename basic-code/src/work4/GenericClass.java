package work4;

public class GenericClass{
    public static void main(String[] args){
        GeneralType<String> T1 = new GeneralType<String>("Type is String");
        System.out.println(T1.getObject());
        T1.print();
        GeneralType<Integer> T2 = new GeneralType<Integer>(123);
        System.out.println(T2.getObject());
        T2.print();
        GeneralType<Double> T3 = new GeneralType<Double>(123.456);
        System.out.println(T3.getObject());
        T3.print();
        GeneralType<Float> T4 = new GeneralType<Float>(123.456f);
        System.out.println(T4.getObject());
        T4.print();
    }
}

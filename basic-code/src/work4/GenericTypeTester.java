package work4;

public class GenericTypeTester {
    public static void main(String args[])
    {
        showType s = new showType();
        GeneralType<Integer> i= new GeneralType<Integer>(123);
        s.showType(i);
    }
}

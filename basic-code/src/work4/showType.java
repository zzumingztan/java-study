package work4;

class showType{
    public void showType(GeneralType<?> o){
        System.out.println("Type is:"+ o.getObject().getClass().getName());
    }
}
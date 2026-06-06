package work4;
//通配符泛型
class GeneralType1<Type>{
    Type object;
    public GeneralType1(Type object){
        this.object = object;
    }
    public Type getObject(){
        return object;
    }
}


//package work2;
//
//public class Patient {
//    private String name;
//    private String sex;
//    private int age;
//    private float weight;
//    private boolean allergies;
//
//    public Patient() {
//    }
//    public Patient(String name, String sex, int age, float weight, boolean allergies) {
//        this.name = name;
//        this.sex = sex;
//        this.age = age;
//        this.weight = weight;
//        this.allergies = allergies;
//    }
//    public String getName() {
//        return name;
//    }
//    public void setName(String name) {
//        this.name = name;
//    }
//    public String getSex() {
//        return sex;
//    }
//    public void setSex(String sex) {
//        this.sex = sex;
//    }
//    public int getAge() {
//        return age;
//    }
//    public void setAge(int age) {
//        this.age = age;
//    }
//    public float getWeight() {
//        return weight;
//    }
//    public void setWeight(float weight) {
//        this.weight = weight;
//    }
//    public boolean isAllergies() {
//        return allergies;
//    }
//    public void setAllergies(boolean allergies) {
//        this.allergies = allergies;
//    }
//    @Override
//    public String toString()
//    {
//        return "Patient{" +
//                "name='" + name + '\'' +
//                ", sex='" + sex + '\'' +
//                ", age=" + age +
//                ", weight=" + weight +
//                ", allergies=" + allergies +
//                '}';
//    }
//}
//class TestPatient {
//    public static void main(String[] args) {
//        Patient april = new Patient();
//        april.setName("ZhangLi");
//        april.setSex("f");
//        april.setAge(33);
//        april.setWeight(154.72f);
//        april.setAllergies(true);
//        System.out.println("Name:"+april.getName());
//        System.out.println("Sex:"+april.getSex());
//        System.out.println("Age:"+april.getAge());
//        System.out.println("Weight:"+april.getWeight());
//        System.out.println("Allergies:"+april.isAllergies());
//        System.out.println("-------------------");
//        System.out.println(april.toString());
//    }
//}

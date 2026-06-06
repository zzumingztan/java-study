package text2;

public class Car extends Vehicle
{
    private int wheel;      // 车轮数量
    private int door;       // 车门数量
    private String engine;  // 引擎类型

    public Car() {
        super();
    }

    public Car(String brand, double speed, int wheel, int door, String engine) {
        super(brand, speed);
        this.wheel = wheel;
        this.door = door;
        this.engine = engine;
    }

    public int getWheel() {
        return wheel;
    }

    public void setWheel(int wheel) {
        this.wheel = wheel;
    }

    public int getDoor() {
        return door;
    }

    public void setDoor(int door) {
        this.door = door;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    @Override
    public void move() {
        System.out.println(getBrand()+"的汽车正在以"+getSpeed()+"km/h的速度行驶。"
                + "\n  - 车轮数: " + wheel + "个"
                + "\n  - 车门数: " + door + "个"
                + "\n  - 引擎类型: " + engine);
    }

    public void honk()
    {
        System.out.println("正在鸣笛...");
    }
}


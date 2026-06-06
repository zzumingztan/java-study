package text2;

public class Bicycle extends Vehicle
{
    public Bicycle() {
        super();
    }

    public Bicycle(String brand, double speed) {
        super(brand, speed);
    }
    @Override
    public void move() {
        System.out.println(getBrand()+"的自行车正在以"+getSpeed()+"km/h的速度行驶。");
    }
    public void ringBell() {
        System.out.println("正在响铃...");
    }
}

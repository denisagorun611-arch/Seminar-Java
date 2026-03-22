import java.util.Objects;

public class Car extends Vehicle {
    private int doors;

    public Car(String id, String brand, double speed, int doors) {
        super(id, brand, speed);
        this.doors = doors;
    }

    public int getDoors() { return doors; }

    @Override
    public boolean needsService() {
        return getMileage() >= 10000;
    }

    @Override
    public double rentalPrice(int days) {
        double price = 50 * days;
        if (doors >= 4) price *= 1.10;
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return doors == car.doors && getBrand().equals(car.getBrand());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBrand(), doors);
    }
}
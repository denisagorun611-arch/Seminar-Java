public class Truck extends Vehicle {
    private double loadCapacity;

    public Truck(String id, String brand, double speed, double loadCapacity) {
        super(id, brand, speed);
        this.loadCapacity = loadCapacity;
    }

    @Override
    public boolean needsService() {
        return getMileage() >= 15000;
    }

    @Override
    public double rentalPrice(int days) {
        return (80 + 0.02 * loadCapacity) * days;
    }
}
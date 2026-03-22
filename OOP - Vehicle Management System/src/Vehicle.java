public abstract class Vehicle {
    private String brand;
    private double speed;
    private String id;
    private int mileage;
    private boolean rented;

    public Vehicle(String id, String brand, double speed) {
        this.id = id;
        this.brand = brand;
        this.speed = speed;
        this.mileage = 0;
        this.rented = false;
    }

    public String getBrand()        { return brand; }
    public void setBrand(String b)  { this.brand = b; }
    public double getSpeed()        { return speed; }
    public void setSpeed(double s)  { this.speed = s; }
    public String getId()           { return id; }
    public int getMileage()         { return mileage; }
    public boolean isRented()       { return rented; }

    public void move() {
        System.out.println(brand + " is moving at " + speed + " km/h.");
    }

    public void rent() {
        if (rented) {
            throw new IllegalStateException("Vehicle " + id + " is already rented.");
        }
        rented = true;
        System.out.println("Vehicle " + id + " (" + brand + ") rented successfully.");
    }

    public void returnVehicle(int drivenKm) {
        if (!rented) {
            throw new IllegalStateException("Vehicle " + id + " is not currently rented.");
        }
        if (drivenKm <= 0) {
            throw new IllegalArgumentException("Driven km must be greater than 0.");
        }
        mileage += drivenKm;
        rented = false;
        System.out.println("Vehicle " + id + " returned. Total mileage: " + mileage + " km.");
    }

    public abstract boolean needsService();
    public abstract double rentalPrice(int days);

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{id='" + id + "', brand='" + brand +
                "', speed=" + speed + ", mileage=" + mileage + ", rented=" + rented + "}";
    }
}
public static void main(String[] args) {

    // Polymorphism demo
    System.out.println("=== Polymorphism Demo ===");
    Vehicle[] vehicles = {
            new Car("C1", "Toyota", 120, 4),
            new Motorcycle("M1", "Honda", 100, false),
            new Truck("T1", "Volvo", 90, 5000)
    };
    for (Vehicle v : vehicles) v.move();

    // equals() demo
    System.out.println("\n=== equals() Demo ===");
    Car car1 = new Car("C1", "Toyota", 120, 4);
    Car car2 = new Car("C2", "Toyota", 110, 4);
    Car car3 = new Car("C3", "BMW", 130, 2);
    System.out.println("car1.equals(car2): " + car1.equals(car2)); // true
    System.out.println("car1.equals(car3): " + car1.equals(car3)); // false

    // Garage demo
    System.out.println("\n=== Garage Demo ===");
    Garage garage = new Garage(10);
    garage.add(new Car("C10", "Dacia", 110, 4));
    garage.add(new Car("C11", "Ford", 130, 2));
    garage.add(new Motorcycle("M10", "Kawasaki", 150, true));
    garage.add(new Truck("T10", "MAN", 80, 8000));
    garage.add(new Car("C12", "Renault", 115, 4));

    // Test duplicate id
    garage.add(new Car("C10", "BMW", 140, 4));

    garage.rentById("C10");
    garage.rentById("M10");

    // Test renting already rented vehicle
    try {
        garage.rentById("C10");
    } catch (IllegalStateException e) {
        System.out.println("Error: " + e.getMessage());
    }

    garage.returnById("C10", 350);

    garage.printAvailable();
    garage.printNeedsService();
    garage.printRentalEstimate("C10", 5);
    garage.printRentalEstimate("M10", 3);
    garage.printRentalEstimate("T10", 7);
}
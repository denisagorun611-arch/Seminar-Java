public class Garage {
    private Vehicle[] fleet;
    private int size;

    public Garage(int capacity) {
        fleet = new Vehicle[capacity];
        size = 0;
    }

    public void add(Vehicle v) {
        if (size >= fleet.length) {
            System.out.println("Garage is full. Cannot add " + v.getId());
            return;
        }
        if (findById(v.getId()) != null) {
            System.out.println("Duplicate id: " + v.getId() + ". Vehicle not added.");
            return;
        }
        fleet[size++] = v;
        System.out.println("Added: " + v.getId());
    }

    public Vehicle findById(String id) {
        for (int i = 0; i < size; i++) {
            if (fleet[i].getId().equals(id)) return fleet[i];
        }
        return null;
    }

    public void rentById(String id) {
        Vehicle v = findById(id);
        if (v == null) { System.out.println("Vehicle not found: " + id); return; }
        v.rent();
    }

    public void returnById(String id, int drivenKm) {
        Vehicle v = findById(id);
        if (v == null) { System.out.println("Vehicle not found: " + id); return; }
        v.returnVehicle(drivenKm);
    }

    public void printAvailable() {
        System.out.println("\n--- Available vehicles ---");
        for (int i = 0; i < size; i++) {
            if (!fleet[i].isRented()) System.out.println(fleet[i]);
        }
    }

    public void printNeedsService() {
        System.out.println("\n--- Vehicles needing service ---");
        for (int i = 0; i < size; i++) {
            if (fleet[i].needsService()) System.out.println(fleet[i]);
        }
    }

    public void printRentalEstimate(String id, int days) {
        Vehicle v = findById(id);
        if (v == null) { System.out.println("Vehicle not found: " + id); return; }
        System.out.println("Rental estimate for " + id + " (" + days + " days): " +
                v.rentalPrice(days) + " EUR");
    }
}
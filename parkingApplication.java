import java.util.*;


class ParkingLot {
    private int capacity;
    private TreeMap<Integer, Car> slots;
    private Map<String, Integer> registrationToSlot;
    private Map<String, String> colorToRegistration;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        this.slots = new TreeMap<>();
        this.registrationToSlot = new HashMap<>();
        this.colorToRegistration = new HashMap<>();
        initializeParkingLot();
    }

    private void initializeParkingLot() {   
        for (int i = 1; i <= capacity; i++) {
            slots.put(i, null);
        }
    }

    public void park(String registrationNumber, String color) {
        if (isParkingLotFull()) {
            System.out.println("Sorry, parking lot is full");
            return;
        }

        int availableSlot = getNearestAvailableSlot();
        Car car = new Car(registrationNumber, color);
        slots.put(availableSlot, car);
        registrationToSlot.put(registrationNumber, availableSlot);
        colorToRegistration.computeIfAbsent(color, k -> new String()).concat(registrationNumber + ", ");

        System.out.println("Allocated slot number: " + availableSlot);
    }

    public void leave(int slotNumber) {
        if (slots.containsKey(slotNumber) && slots.get(slotNumber) != null) {
            Car car = slots.get(slotNumber);
            slots.put(slotNumber, null);
            registrationToSlot.remove(car.getRegistrationNumber());
            colorToRegistration.computeIfPresent(car.getColor(), (k, v) -> v.replace(car.getRegistrationNumber() + ", ", ""));
            System.out.println("Slot number " + slotNumber + " is free");
        } else {
            System.out.println("Slot number " + slotNumber + " is already empty");
        }
    }

    public void status() {
        System.out.println("Slot No. Registration No Colour");
        for (Map.Entry<Integer, Car> entry : slots.entrySet()) {
            if (entry.getValue() != null) {
                System.out.println(entry.getKey() + " " + entry.getValue().getRegistrationNumber() + " " + entry.getValue().getColor());
            }
        }
    }

    public void getRegistrationNumbersForCarsWithColor(String color) {
        String registrationNumbers = colorToRegistration.getOrDefault(color, "");
        if (registrationNumbers.isEmpty()) {
            System.out.println("No cars found with color " + color);
        } else {
            System.out.println(registrationNumbers.substring(0, registrationNumbers.length() - 2));
        }
    }

    public void getSlotNumberForRegistrationNumber(String registrationNumber) {
        Integer slotNumber = registrationToSlot.get(registrationNumber);
        if (slotNumber != null) {
            System.out.println("Slot number for Registration No " + registrationNumber + " is " + slotNumber);
        } else {
            System.out.println("Car with Registration No " + registrationNumber + " not found");
        }
    }

    private boolean isParkingLotFull() {
        return registrationToSlot.size() >= capacity;
    }

    private int getNearestAvailableSlot() {
        for (Map.Entry<Integer, Car> entry : slots.entrySet()) {
            if (entry.getValue() == null) {
                return entry.getKey();
            }
        }
        return -1;
    }
}

class Car {
    private String registrationNumber;
    private String color;

    public Car(String registrationNumber, String color) {
        this.registrationNumber = registrationNumber;
        this.color = color;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getColor() {
        return color;
    }
}

 class ParkingLotApplication {
    public static void main(String[] args) {
        ParkingLot parkingLot = null;
        boolean exit = false;
        Scanner sc = new Scanner(System.in);
        while (!exit) {
            System.out.print("Enter command: ");
            String commandLine = sc.nextLine();
            String[] commandParts = commandLine.split(" ");

            switch (commandParts[0]) {
                case "create_parking_lot":
                    int capacity = Integer.parseInt(commandParts[1]);
                    parkingLot = new ParkingLot(capacity);
                    System.out.println("Created a parking lot with " + capacity + " slots");
                    break;

                case "park":
                    if (parkingLot == null) {
                        System.out.println("Please create a parking lot first");
                    } else {
                        parkingLot.park(commandParts[1], commandParts[2]);
                    }
                    break;

                case "leave":
                    if (parkingLot == null) {
                        System.out.println("Please create a parking lot first");
                    } else {
                        int slotNumber = Integer.parseInt(commandParts[1]);
                        parkingLot.leave(slotNumber);
                    }
                    break;

                case "status":
                    if (parkingLot == null) {
                        System.out.println("Please create a parking lot first");
                    } else {
                        parkingLot.status();
                    }
                    break;

                case "registration_numbers_for_cars_with_colour":
                    if (parkingLot == null) {
                        System.out.println("Please create a parking lot first");
                    } else {
                        parkingLot.getRegistrationNumbersForCarsWithColor(commandParts[1]);
                    }
                    break;

                case "slot_number_for_registration_number":
                    if (parkingLot == null) {
                        System.out.println("Please create a parking lot first");
                    } else {
                        parkingLot.getSlotNumberForRegistrationNumber(commandParts[1]);
                    }
                    break;

                case "exit":
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid command");
                    break;
            }

        }
    }
}

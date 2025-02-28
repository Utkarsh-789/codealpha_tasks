import java.util.*;

class Room {
    int roomNumber;
    String type;
    double price;
    boolean isAvailable;

    public Room(int roomNumber, String type, double price) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.price = price;
        this.isAvailable = true;
    }

    public void bookRoom() {
        if (isAvailable) {
            isAvailable = false;
            System.out.println("Room " + roomNumber + " booked successfully!");
        } else {
            System.out.println("Room " + roomNumber + " is already booked!");
        }
    }

    public void freeRoom() {
        isAvailable = true;
    }

    @Override
    public String toString() {
        return "Room " + roomNumber + " (" + type + ") - $" + price + " - " + (isAvailable ? "Available" : "Booked");
    }
}

class Hotel {
    List<Room> rooms = new ArrayList<>();
    Map<Integer, String> bookings = new HashMap<>();

    public Hotel() {
        rooms.add(new Room(101, "Single", 50));
        rooms.add(new Room(102, "Double", 80));
        rooms.add(new Room(103, "Suite", 150));
        rooms.add(new Room(104, "Single", 50));
    }

    public void displayAvailableRooms() {
        System.out.println("\nAvailable Rooms:");
        for (Room room : rooms) {
            if (room.isAvailable) {
                System.out.println(room);
            }
        }
    }

    public void bookRoom(int roomNumber, String guestName) {
        for (Room room : rooms) {
            if (room.roomNumber == roomNumber && room.isAvailable) {
                room.bookRoom();
                bookings.put(roomNumber, guestName);
                return;
            }
        }
        System.out.println("Room not available or does not exist!");
    }

    public void viewBookings() {
        System.out.println("\nBooking Details:");
        if (bookings.isEmpty()) {
            System.out.println("No rooms booked yet.");
        } else {
            for (Map.Entry<Integer, String> entry : bookings.entrySet()) {
                System.out.println("Room " + entry.getKey() + " booked by " + entry.getValue());
            }
        }
    }
}

public class HotelReservationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hotel hotel = new Hotel();

        while (true) {
            System.out.println("\n1. View Available Rooms  2. Book a Room  3. View Bookings  4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    hotel.displayAvailableRooms();
                    break;
                case 2:
                    System.out.print("Enter room number to book: ");
                    int roomNumber = scanner.nextInt();
                    System.out.print("Enter your name: ");
                    scanner.nextLine(); // Consume newline
                    String guestName = scanner.nextLine();
                    hotel.bookRoom(roomNumber, guestName);
                    break;
                case 3:
                    hotel.viewBookings();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
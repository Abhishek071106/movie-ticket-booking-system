import java.util.*;

public class MovieTicketBookingSystem {

    // Movies and seats data
    private static Map<String, List<String>> movies = new HashMap<>();
    private static Map<String, Set<Integer>> bookedSeats = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initializeMovies();

        System.out.println("🎬 Welcome to Movie Ticket Booking System 🎟️");

        while (true) {
            System.out.println("\nAvailable Movies:");
            for (String movie : movies.keySet()) {
                System.out.println("- " + movie);
            }

            System.out.print("\nEnter movie name to book (or 'exit' to quit): ");
            String movieName = scanner.nextLine();

            if (movieName.equalsIgnoreCase("exit")) {
                System.out.println("Thank you! Goodbye 👋");
                break;
            }

            if (!movies.containsKey(movieName)) {
                System.out.println("Movie not found. Try again.");
                continue;
            }

            showAvailableSeats(movieName);
            System.out.print("Choose seat number to book (1–5): ");
            int seatNumber = scanner.nextInt();
            scanner.nextLine(); // consume newline

            bookSeat(movieName, seatNumber);
        }

        scanner.close();
    }

    // Initialize movies with seats
    private static void initializeMovies() {
        movies.put("Avengers", Arrays.asList("Seat1", "Seat2", "Seat3", "Seat4", "Seat5"));
        movies.put("Inception", Arrays.asList("Seat1", "Seat2", "Seat3", "Seat4", "Seat5"));
        movies.put("Spider-Man", Arrays.asList("Seat1", "Seat2", "Seat3", "Seat4", "Seat5"));

        bookedSeats.put("Avengers", new HashSet<>());
        bookedSeats.put("Inception", new HashSet<>());
        bookedSeats.put("Spider-Man", new HashSet<>());
    }

    // Show available seats for a movie
    private static void showAvailableSeats(String movie) {
        List<String> seats = movies.get(movie);
        Set<Integer> booked = bookedSeats.get(movie);

        System.out.println("\nAvailable Seats for " + movie + ":");
        for (int i = 0; i < seats.size(); i++) {
            if (!booked.contains(i)) {
                System.out.print((i + 1) + " ");
            } else {
                System.out.print("[X] ");
            }
        }
        System.out.println();
    }

    // Book a seat for a movie
    private static void bookSeat(String movie, int seatNumber) {
        Set<Integer> booked = bookedSeats.get(movie);

        if (seatNumber < 1 || seatNumber > 5) {
            System.out.println("❌ Invalid seat number.");
            return;
        }

        if (booked.contains(seatNumber - 1)) {
            System.out.println("⚠️ Seat already booked! Try another.");
        } else {
            booked.add(seatNumber - 1);
            System.out.println("✅ Successfully booked Seat " + seatNumber + " for " + movie + "!");
        }
    }
}


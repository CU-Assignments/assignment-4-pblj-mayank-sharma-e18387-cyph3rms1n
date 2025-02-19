import java.util.concurrent.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class TicketBookingSystem {
    private static int TOTAL_SEATS;
    private static int TOTAL_CUSTOMERS;
    private static int VIP_CUSTOMERS;
    private static boolean[] seats;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the total number of seats: ");
        TOTAL_SEATS = scanner.nextInt();
        seats = new boolean[TOTAL_SEATS];

        System.out.print("Enter the total number of customers: ");
        TOTAL_CUSTOMERS = scanner.nextInt();

        System.out.print("Enter the number of VIP customers: ");
        VIP_CUSTOMERS = scanner.nextInt();

        scanner.close();

        List<Customer> vipCustomers = new ArrayList<>();
        List<Customer> regularCustomers = new ArrayList<>();

        for (int i = 0; i < VIP_CUSTOMERS; i++) {
            vipCustomers.add(new Customer(i, true));
        }

        for (int i = VIP_CUSTOMERS; i < TOTAL_CUSTOMERS; i++) {
            regularCustomers.add(new Customer(i, false));
        }

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (Customer vip : vipCustomers) {
            executorService.execute(vip);
        }

        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executorService = Executors.newFixedThreadPool(5);
        for (Customer regular : regularCustomers) {
            executorService.execute(regular);
        }

        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Booking process completed.");
        printBookingSummary();
    }

    static class Customer implements Runnable {
        private int id;
        private boolean isVIP;

        public Customer(int id, boolean isVIP) {
            this.id = id;
            this.isVIP = isVIP;
        }

        @Override
        public void run() {
            Thread.currentThread().setPriority(isVIP ? Thread.MAX_PRIORITY : Thread.NORM_PRIORITY);
            bookSeat();
        }

        private void bookSeat() {
            synchronized (seats) {
                for (int i = 0; i < TOTAL_SEATS; i++) {
                    if (!seats[i]) {
                        seats[i] = true;
                        System.out.println((isVIP ? "VIP " : "") + "Customer " + (id + 1) + " booked seat " + (i + 1));
                        return;
                    }
                }
                System.out.println((isVIP ? "VIP " : "") + "Customer " + (id + 1) + " couldn't book a seat");
            }
        }
    }

    private static void printBookingSummary() {
        int bookedSeats = 0;
        for (boolean seat : seats) {
            if (seat) bookedSeats++;
        }
        System.out.println("\nBooking Summary:");
        System.out.println("Total Seats: " + TOTAL_SEATS);
        System.out.println("Booked Seats: " + bookedSeats);
        System.out.println("Available Seats: " + (TOTAL_SEATS - bookedSeats));
    }
}

import java.util.Scanner;

public class AppRunner {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PupilManager manager = new PupilManager("pupils_data_mod.txt");
        boolean running = true;

        while (running) {
            System.out.println("\n===== Pupil Records Manager (Lab 3 Modified) =====");
            System.out.println("1. Add Pupil");
            System.out.println("2. View All");
            System.out.println("3. Search by Name");
            System.out.println("4. Delete by Name");
            System.out.println("5. Save & Exit");
            System.out.print("Enter choice: ");

            String choice = sc.nextLine().trim();
            try {
                switch (choice) {
                    case "1":
                        System.out.print("Enter Roll No (Integer): ");
                        Integer roll = Integer.valueOf(sc.nextLine().trim());
                        System.out.print("Enter Name: ");
                        String name = sc.nextLine().trim();
                        System.out.print("Enter Email: ");
                        String email = sc.nextLine().trim();
                        System.out.print("Enter Course: ");
                        String course = sc.nextLine().trim();
                        System.out.print("Enter Marks (Double): ");
                        Double marks = Double.valueOf(sc.nextLine().trim());

                        Thread t1 = new Thread(new Spinner("Adding"));
                        t1.start();
                        t1.join();

                        manager.addRecord(roll, name, email, course, marks);
                        System.out.println("Pupil added successfully.");
                        break;

                    case "2":
                        manager.viewAll();
                        break;

                    case "3":
                        System.out.print("Enter name to search: ");
                        String sName = sc.nextLine().trim();
                        Thread t2 = new Thread(new Spinner("Searching"));
                        t2.start();
                        t2.join();
                        Pupil found = manager.searchByName(sName);
                        System.out.println(found);
                        System.out.println("Grade: " + (found.getMarks() >= 90 ? "A" : found.getMarks() >=75 ? "B" : found.getMarks() >=50 ? "C" : "D"));
                        break;

                    case "4":
                        System.out.print("Enter name to delete: ");
                        String dName = sc.nextLine().trim();
                        Thread t3 = new Thread(new Spinner("Deleting"));
                        t3.start();
                        t3.join();
                        manager.deleteByName(dName);
                        System.out.println("Pupil deleted.");
                        break;

                    case "5":
                        Thread t4 = new Thread(new Spinner("Saving"));
                        t4.start();
                        t4.join();
                        manager.saveToFile();
                        System.out.println("Data saved. Exiting...");
                        running = false;
                        break;

                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Number format error: " + nfe.getMessage());
            } catch (IllegalArgumentException iae) {
                System.out.println("Validation error: " + iae.getMessage());
            } catch (PupilNotFoundException pnfe) {
                System.out.println("Not found: " + pnfe.getMessage());
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted.");
                Thread.currentThread().interrupt();
            } catch (Exception ex) {
                System.out.println("Unexpected error: " + ex.getMessage());
            }
        }

        try {
            sc.close();
        } finally {
            System.out.println("Scanner closed. Program terminated.");
        }
    }
}

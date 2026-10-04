package lw03.prelab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.* ;

public class Main {

    public static void main(String[] args) {
        problem1();
        problem2();
        problem3();
    }

    
    // PROBLEM 1
    
    public static void problem1() {
        List<String> playlist = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File("src/lw03/prelab/playlist.txt"));

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                if (line.isEmpty()) {
                    continue;
                }

                String[] parts = line.split(" ", 3);
                String operation = parts[0];

                if (operation.equals("ADD")) {
                    String song = parts[1];
                    playlist.add(song);

                } else if (operation.equals("INSERT")) {
                    int index = Integer.parseInt(parts[1]);
                    String song = parts[2];

                    playlist.add(index, song);

                } else if (operation.equals("REMOVE")) {
                    String song = parts[1];
                    playlist.remove(song);
                }
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("playlist.txt tidak ditemukan.");
            return;
        }

        System.out.println("===== Problem 1 =====");
        System.out.println("Total songs: " + playlist.size());

        for (int i = 0; i < playlist.size(); i++) {
            System.out.println((i + 1) + ": " + playlist.get(i));
        }
    }

    
    // PROBLEM 2 
    
    public static void problem2() {
        Set<String> participants = new LinkedHashSet<>();
        int duplicateRegistrations = 0;

        try {
            Scanner scanner = new Scanner(new File("src/lw03/prelab/participants.txt"));

            while (scanner.hasNextLine()) {
                String name = scanner.nextLine().trim();

                if (name.isEmpty()) {
                    continue;
                }

                if (!participants.add(name)) {
                    duplicateRegistrations++;
                }
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("participants.txt tidak ditemukan.");
            return;
        }

        System.out.println("===== Problem 2 =====");
        System.out.println("Unique participants: " + participants.size());

        int number = 1;

        for (String participant : participants) {
            System.out.println(number + ". " + participant);
            number++;
        }

        System.out.println("Duplicate registrations: "
                + duplicateRegistrations);
    }

    
    // PROBLEM 3 
    
    public static void problem3() {
        Map<String, Integer> inventory = new LinkedHashMap<>();
        int failedSales = 0;

        try {
            Scanner scanner = new Scanner(new File("src/lw03/prelab/inventory.txt"));

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                if (line.isEmpty()) {
                    continue;
                }

                String[] parts = line.split(" ");

                String type = parts[0];
                String product = parts[1];
                int quantity = Integer.parseInt(parts[2]);

                if (type.equals("ADD")) {

                    if (inventory.containsKey(product)) {
                        int currentStock = inventory.get(product);
                        inventory.put(product, currentStock + quantity);
                    } else {
                        inventory.put(product, quantity);
                    }

                } else if (type.equals("SELL")) {

                    if (inventory.containsKey(product)
                            && inventory.get(product) >= quantity) {

                        int currentStock = inventory.get(product);
                        inventory.put(product, currentStock - quantity);

                    } else {
                        failedSales++;
                    }
                }
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("inventory.txt tidak ditemukan.");
            return;
        }

        System.out.println("===== Problem 3 =====");

        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        System.out.println("Failed sales: " + failedSales);
    }
}
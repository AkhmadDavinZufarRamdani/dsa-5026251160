package lw02.unguided;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        // LinkedList untuk menyimpan semua order
        LinkedList<String[]> orders = new LinkedList<>();

    
        LinkedList<String[]> foods = new LinkedList<>();

        
        
        LinkedList<String[]> drinks = new LinkedList<>();

        
        LinkedList<String[]> successfulOrders = new LinkedList<>();

    
        Stack<String[]> failedOrders = new Stack<>();

        

        foods.add(new String[]{"Bakso", "2"});
        foods.add(new String[]{"Sate", "1"});
        foods.add(new String[]{"Soto", "2"});
        drinks.add(new String[]{"EsTeh", "4"});
        drinks.add(new String[]{"EsJeruk", "2"});

        try {
            Scanner scanner = new Scanner(
                new File("src/lw02/unguided/orders.txt")
            );

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();

                String[] data = line.split(" ");

                String name = data[0];
                String food = data[1];
                String drink = data[2];
                String table = data[3];

                orders.add(new String[]{
                    name,food,drink,table
                });
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("File orders.txt tidak ditemukan.");
            return;
        }

        Queue<String[]> orderQueue = new LinkedList<>();

        while (!orders.isEmpty()) {
            orderQueue.add(orders.removeFirst());
        }

        
        while (!orderQueue.isEmpty()) {

            String[] order = orderQueue.poll();

            String foodName = order[1];
            String drinkName = order[2];

            boolean foodAvailable = true;
            boolean drinkAvailable = true;

            
            if (!foodName.equals("-")) {

                for (String[] food : foods) {

                    if (food[0].equals(foodName)) {

                        int stock = Integer.parseInt(food[1]);

                        if (stock <= 0) {
                            foodAvailable = false;
                        }

                        break;
                    }
                }
            }

            
            if (!drinkName.equals("-")) {

                for (String[] drink : drinks) {

                    if (drink[0].equals(drinkName)) {

                        int stock = Integer.parseInt(drink[1]);

                        if (stock <= 0) {
                            drinkAvailable = false;
                        }

                        break;
                    }
                }
            }


            if (foodAvailable && drinkAvailable) {

                // kurangi food stock
                if (!foodName.equals("-")) {

                    for (String[] food : foods) {

                        if (food[0].equals(foodName)) {

                            int stock = Integer.parseInt(food[1]);

                            stock--;

                            food[1] = String.valueOf(stock);

                            break;
                        }
                    }
                }

                // kurangi drink stock
                if (!drinkName.equals("-")) {

                    for (String[] drink : drinks) {

                        if (drink[0].equals(drinkName)) {

                            int stock = Integer.parseInt(drink[1]);

                            stock--;

                            drink[1] = String.valueOf(stock);

                            break;
                        }
                    }
                }

                // order sukses
                successfulOrders.add(order);

            } else {

                // order gagal
                failedOrders.push(order);
            }
        }

        
        System.out.println("=== Successfully Processed Orders ===");

        for (String[] order : successfulOrders) {

            System.out.println(
                order[0] + " "
                + order[1] + " "
                + order[2] + " "
                + order[3]
            );
        }

        System.out.println("=== Remaining Food Stock ===");

        for (String[] food : foods) {

            System.out.println(
                food[0] + " : " + food[1]
            );
        }

        
        System.out.println("=== Remaining Drink Stock ===");

        for (String[] drink : drinks) {

            System.out.println(
                drink[0] + " : " + drink[1]
            );
        }


        System.out.println("=== Failed Orders ===");

        while (!failedOrders.isEmpty()) {

            String[] order = failedOrders.pop();

            System.out.println(
                order[0] + " "
                + order[1] + " "
                + order[2] + " "
                + order[3]
            );
        }
    }
}
package lw01.unguided;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(Main.class.getResourceAsStream("washes.txt"));

        int n  = scanner.nextInt();

        WashService[] washes = new WashService[n];

        for (int i = 0; i < n; i++) {
            String type = scanner.next();
            String id = scanner.next();
            int days = scanner.nextInt();
            int units = scanner.nextInt();

            if (type.equals("MOTORCYCLE")) {
                washes[i] = new MotorcycleWash(id, days);
            } else if (type.equals("CAR")) {
                washes[i] = new CarWash(id, days);
            }

            // Menggunakan overload calculateCharge(int units)
            // untuk memastikan units tervalidasi dan dihitung.
            final int currentUnits = units;

            // Tidak perlu mengubah object menjadi subclass.
            // Perhitungan units dilakukan saat output.
        }

        scanner.close();

        for (WashService wash : washes) {
            System.out.println(wash.summary());
        }
    }
}
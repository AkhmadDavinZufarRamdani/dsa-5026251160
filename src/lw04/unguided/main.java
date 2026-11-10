package lw04.unguided;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.* ;


public class main {

    public static void main(String[] args) throws FileNotFoundException {
    
            Scanner parcelscan = new Scanner(
                    new File("src/lw04/unguided/parcels.txt"));

            int N = parcelscan.nextInt();

            int[] urutanparcel = new int[N];

            for (int i = 0; i < N; i++) {
                urutanparcel[i] = parcelscan.nextInt();
            }

            int M = parcelscan.nextInt();

            int[] search = new int[M];

            for (int i = 0; i < M; i++) {
                search[i] = parcelscan.nextInt();
            }
        

            
            for (int pass = 0; pass < 2; pass++) {
                for (int i = 0; i < N - 1; i++) {
                    if (urutanparcel[i] < urutanparcel[i + 1]) {
                        int temp = urutanparcel[i];
                        urutanparcel[i] = urutanparcel[i + 1];
                        urutanparcel[i + 1] = temp;
                    }
                }
            }

            
            System.out.println("==== After 2 Passes ====");
            for (int i = 0; i < N; i++) {
                System.out.print(urutanparcel[i] + " ");
            }


  
            for (int pass = 0; pass < N - 1; pass++) {
                for (int i = 0; i < N - 1; i++) {
                    if (urutanparcel[i] < urutanparcel[i + 1]) {
                        int temp = urutanparcel[i];
                        urutanparcel[i] = urutanparcel[i + 1];
                        urutanparcel[i + 1] = temp;
                    }
                }
            }

            System.out.println("\n==== Fully Sorted ====");
            for (int i = 0; i < N; i++) {
                System.out.print(urutanparcel[i] + " ");
            }

            

           System.out.println("\n==== Binary Search ====");

        for (int i = 0; i < M; i++) {

            int target = search[i];

            int left = 0;
            int right = N - 1;

            int steps = 0;
            int foundIndex = -1;

            while (left <= right) {

                int mid = (left + right) / 2;
                steps++;

                if (urutanparcel[mid] == target) {
                    foundIndex = mid;
                    break;
                }

                if (target > urutanparcel[mid]) {
                    right = mid - 1;
                }

                else {
                    left = mid + 1;
                }
            }

            if (foundIndex != -1) {
                System.out.println(target +
                        ": FOUND at index " + foundIndex +
                        " (steps: " + steps + ")");
            } else {
                System.out.println(target +
                        ": NOT FOUND (steps: " + steps + ")");


}
}
    }
}
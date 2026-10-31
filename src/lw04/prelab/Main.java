package lw04.prelab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.* ;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        Scanner branchscan = new Scanner(
                new File("src/lw04/prelab/branches.txt"));

            int N = branchscan.nextInt();

            int[] urutanbranch = new int[N];

            for (int i = 0; i < N; i++) {
                urutanbranch[i] = branchscan.nextInt();
            }

            int M = branchscan.nextInt();

            int[] search = new int[M];

            for (int i = 0; i < M; i++) {
                search[i] = branchscan.nextInt();
            }
            

            for (int i = 1; i <= 3 && i < N; i++) {

            int key = urutanbranch[i];
            int j = i - 1;

            while (j >= 0 && urutanbranch[j] < key) {
                urutanbranch[j + 1] = urutanbranch[j];
                j--;
            }

            urutanbranch[j + 1] = key;
            if (i== 3) {
    
            for (int value : urutanbranch) {
                System.out.print(value + " ");
            }
            System.out.println();
                }
            }


            for (int i = 0; i < M; i++) {

    boolean found = false;

    for (int j = 0; j < N; j++) {

        if (search[i] == urutanbranch[j]) {
            System.out.println(
                search[i] + ": FOUND at index " + j
            );

            found = true;
            break;
        }
    }

    if (!found) {
        System.out.println(search[i] + ": NOT FOUND");
    }
}
}
}
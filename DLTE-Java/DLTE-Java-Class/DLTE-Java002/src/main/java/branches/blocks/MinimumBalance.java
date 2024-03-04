package branches.blocks;

import java.util.Scanner;

public class MinimumBalance {

    public static void main(String[] args) {
        Double[] availableBalance = {10000.0, 5000.0, 20000.0, 4000.0, 15000.0, 800.0, 150.0, 4500.0, 2000.0, 5000.0, 2500.0, 5500.0, 8000.0, 150000.0, 22000.0, 3400.0, 60000.0, 7000.0, 3300.0, 6500.0};
        //Scanner scanner = new Scanner(System.in);
        for (int index=0;index<20;index++) {
            //availableBalance[index] = scanner.nextDouble();
            if (availableBalance[index] <= 10000) {
                if (availableBalance[index] >= 5000 && availableBalance[index] <= 9999) {
                    availableBalance[index] = availableBalance[index] - 0.03*availableBalance[index];
                } else if (availableBalance[index] >= 1000 && availableBalance[index] <=4999) {
                    availableBalance[index] = availableBalance[index] - 0.05*availableBalance[index];
                }
            }
        }
        for (int index=0;index<20;index++) {
            System.out.println(availableBalance[index]);
        }
//        System.out.println();

    }

}

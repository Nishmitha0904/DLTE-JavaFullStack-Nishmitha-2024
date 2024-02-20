package branches.blocks;

import java.util.Scanner;

public class MinimumBalance {

    public static void main(String[] args) {
        double[] availableBalance = new double[20];
        Scanner scanner = new Scanner(System.in);
        for (int i=0;i<20;i++) {
            availableBalance[i] = scanner.nextDouble();
            if (availableBalance[i] <= 10000) {
                if (availableBalance[i] >= 5000 && availableBalance[i] <= 9999) {
                    availableBalance[i] = availableBalance[i] - 0.03*availableBalance[i];
                } else if (availableBalance[i] >= 1000 && availableBalance[i] <=4999) {
                    availableBalance[i] = availableBalance[i] - 0.05*availableBalance[i];
                }
            }
        }
        for (int i=0;i<20;i++) {
            System.out.print(availableBalance[i] + " ");
        }
        System.out.println();

    }

}

package com.company;

import java.util.Random;

public class Main {

    public static int[] health = {700, 250, 250, 250, 100};
    public static int[] damage = {50, 20, 20, 20, 10};
    public static String[] hitTypes = {"Physical", "Physical", "Magical", "Metal", "Medik"};

    public static void main(String[] args) {

        printStatistics();
        while (!isFinished()) {
            hitTypes[0] = generateBossDefenceType();
            round();
            health[4] = medicAtion();
        }
    }

    public static boolean isFinished() {
        if (health[0] <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        if (health[1] <= 0 && health[2] <= 0 && health[3] <= 0 && health[4] <= 0) {
            System.out.println("Boss won!!");
            return true;
        }
        return false;
    }

    public static int hitPlayer(int playerIndex) {

        return health[playerIndex] - damage[0];
    }

    public static int hitBoss(int playerIndex) {

        Random r = new Random();
        int randomNumber = r.nextInt(6) + 1; //Generate random number 1 to 5
        if (health[0] <= 0) {
            System.out.println("Boss is dead");
            return health[0] * 1;
        }
        if (hitTypes[0].equals(hitTypes[playerIndex])) {
            System.out.println(hitTypes[playerIndex] + " нанес критический удар "
                    + damage[playerIndex] * randomNumber);
            if (health[0] <= 0) {
                System.out.println("Boss is deaad!!");

            }
            return health[0] - damage[playerIndex];
        }

        return health[0] - damage[playerIndex] * randomNumber;
    }

    public static void printStatistics() {

        System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+\nBoss health - " + health[0] + "\n" +
                "Varior health - " + health[1] + "\nMagic health - " + health[2] + "\nKinetic health - "
                + health[3] + "\n");
        /*System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        System.out.println("Boss health " + health[0]);
        System.out.println("Varior health " + health[1]);
        System.out.println("Magic health " + health[2]);
        System.out.println("Kinetic health " + health[3]);
        System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");*/
    }

    public static void round() {

        for (int i = 1; i <= 4; i++) {

            health[i] = hitPlayer(i);
            health[0] = hitBoss(i);
        }


        printStatistics();
    }

    public static String generateBossDefenceType() {

        Random r = new Random();
        int randomDefence = r.nextInt(3) + 1;

        return hitTypes[randomDefence];
    }

    public static int medicAtion() {
        Random rnd = new Random();
        int randomPlayerNumber = rnd.nextInt(4);
        if (randomPlayerNumber == 0) {
            System.out.println("Боссу не досталось лекарства");
            return health[randomPlayerNumber];
        }
        System.out.println("Medic treat " + hitTypes[randomPlayerNumber]);
        return health[randomPlayerNumber] += damage[4];
    }
}

package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SessionController sessionController = new SessionController();

        Tower tower1 = new Tower("tower1", 100);
        Tower tower2 = new Tower("tower2", 40);
        Mage mage1 = new Mage("mage1", 10, tower1);
        Mage mage2 = new Mage("mage2", 8, tower1);
        Mage mage3 = new Mage("mage3", 7, tower2);

        System.out.print("enter towers number: ");
        Scanner scanner = new Scanner(System.in);
        int n1 = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < n1; i++) {
            System.out.println("adding tower " + (i + 1));
            System.out.print("enter tower name: ");
            String towerName = scanner.nextLine();
            System.out.print("enter tower height: ");
            int towerHeight = scanner.nextInt();
            scanner.nextLine();

            Tower tower = new Tower(towerName, towerHeight);
            sessionController.addTower(tower);
        }

        System.out.print("enter mages number: ");
        int n2 = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < n2; i++) {
            System.out.println("adding mage " + (i + 1));
            System.out.print("enter mage name: ");
            String mageName = scanner.nextLine();
            System.out.print("enter mage level: ");
            int mageLevel = scanner.nextInt();
            scanner.nextLine(); // consume newline
            System.out.print("enter mage's tower name: ");
            String towerName = scanner.nextLine();

            Tower assignedTower = sessionController.getTowerByName(towerName);
            if (assignedTower != null) {
                Mage mage = new Mage(mageName, mageLevel, assignedTower);
                sessionController.addMage(mage);
            } else {
                System.out.println("adding failed: tower does not exist");
            }
        }
        sessionController.addTower(tower1);
        sessionController.addTower(tower2);
        sessionController.addMage(mage1);
        sessionController.addMage(mage2);
        sessionController.addMage(mage3);

        sessionController.displayAllMages();
        sessionController.displayMagesWithLevelGreaterThan(8);
        sessionController.displayTowersWithHeightLowerThan(50);
        sessionController.displayMagesWithLevelGreaterThanFromTower("tower1", 7);

        sessionController.deleteTowerByName("tower1");
        sessionController.displayAllMages();


        sessionController.closeSessionFactory();
    }
}

package model;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PrintConsole {

    public static void printQuestion(Parking parking) {

        Scanner parkingSize = new Scanner(System.in);
        System.out.println("Enter parking size");
        try {
            parking.setParkingSize(parkingSize.nextInt());
        } catch (InputMismatchException e) {
            System.out.println("Invalid input, please try again");
            printQuestion(parking);
        }

        parking.responseAgain();

    }

}

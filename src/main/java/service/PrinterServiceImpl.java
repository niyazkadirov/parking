package service;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PrinterServiceImpl implements PrinterService {

    @Override
    public void printQuestionToConsole(ParkingServiceImpl parkingService) {

        Scanner parkingSize = new Scanner(System.in);
        System.out.println("Enter parking size");
        try {
            parkingService.setParkingSize(parkingSize.nextInt());


        } catch (InputMismatchException e) {
            System.out.println("Invalid input, please try again");
            printQuestionToConsole(parkingService);
        }

        parkingService.responseAgain();

    }

}

package service;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PrinterServiceImpl implements PrinterService {


    @Override
    public void printQuestionToConsole(ParkingServiceImpl parkingService) {

        Scanner parkingSize = new Scanner(System.in);
        System.out.println("Enter parking size:");
        try {
            int getParkingSize = parkingSize.nextInt();

            if (getParkingSize < 0) {
                System.out.println("Invalid input, please try again");
                printQuestionToConsole(parkingService);
            }

            parkingService.setParkingSize(getParkingSize);
            parkingService.addCarsToList();


        } catch (InputMismatchException | InterruptedException e) {
            System.out.println("Invalid input, please try again");
            printQuestionToConsole(parkingService);
        }
    }

}

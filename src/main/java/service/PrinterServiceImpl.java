package service;

import lombok.SneakyThrows;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PrinterServiceImpl implements PrinterService {

    MyThread thread = new MyThread();

    @Override
    public void printQuestionToConsole(ParkingServiceImpl parkingService) {

        Scanner parkingSize = new Scanner(System.in);
        System.out.println("Enter parking size");
        try {
            parkingService.setParkingSize(parkingSize.nextInt());
            thread.start();
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
            parkingService.checkEnter=(false);
            System.out.println(parkingService.isCheckEnter());
        } catch (InputMismatchException e) {
            System.out.println("Invalid input, please try again");
            printQuestionToConsole(parkingService);
        }

       // parkingService.responseAgain();

    }

}

class MyThread extends Thread {
    private ParkingServiceImpl parkingService = new ParkingServiceImpl();

    @SneakyThrows
    @Override
    public void run() {
        parkingService.addFewCarsToListAndStopByEnter();
    }
}

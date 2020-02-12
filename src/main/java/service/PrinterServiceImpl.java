package service;

import model.Car;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class PrinterServiceImpl implements PrinterService {


    @Override
    public void printQuestionToConsole(ParkingServiceImpl parkingService) {

        Scanner parkingSize = new Scanner(System.in);
        System.out.println("Enter parking size (min size 3)");
        try {
            int getParkingSize = parkingSize.nextInt();

            if (getParkingSize < 3) {
                System.out.println("Invalid input, please try again");
                printQuestionToConsole(parkingService);
            }

            parkingService.setParkingSize(getParkingSize);
            parkingService.addFewCarsToListAndStopByEnter();

            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
            parkingService.setCheckEnter();

        } catch (InputMismatchException | InterruptedException e) {
            System.out.println("Invalid input, please try again");
            printQuestionToConsole(parkingService);
        }
    }

    public void printParkingInfo(List<Car> cars) {
        System.out.println("In the parking lot of " + cars.size() + " cars");
        System.out.println("parking will be free in " + cars.stream().sorted().findFirst());
    }

}

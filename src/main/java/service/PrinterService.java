package service;

import model.Car;
import model.Parking;

import java.util.InputMismatchException;
import java.util.List;

public class PrinterService {


    public static void printQuestionToConsole(ParkingService parkingService) {

        System.out.println("Enter parking size:");
        int parkingSizeFromConsole = parkingService.getParkingSizeFromConsole();

        try {
            if (parkingSizeFromConsole <= 0) {
                System.out.println("Invalid input, please try again");
                printQuestionToConsole(parkingService);
            }
            parkingService.addCarsToList();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input, please try again");
            printQuestionToConsole(parkingService);
        }
    }


    static void printNumberParkingSpace(Parking parking, List<Car> carList) {
        System.out.println("Number of parking spaces: " + ((parking.getParkingSize() - carList.size())));
    }

    static void printNotPlaceInParking(int emptyPlace) {
        for (int i = 1; i <= emptyPlace; i++) {
            System.out.println("Sorry, we don't have any openings.");
        }
    }

    static void printParkingPlaceInfo(List<Car> carList) {
        int remainingIterate = Integer.MAX_VALUE;

        for (Car car : carList) {
            System.out.println("Iteration before leaving the parking lot : " + car.getRemainingIterate() + "  Parking place: " + carList.indexOf(car));
            if (remainingIterate > car.getRemainingIterate()) {
                remainingIterate = car.getRemainingIterate();
            }
        }
        System.out.println("Parking will be empty in " + remainingIterate + " later iteration");
    }


}

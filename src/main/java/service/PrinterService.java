package service;

import model.Car;
import model.Parking;

import java.util.InputMismatchException;
import java.util.List;

public class PrinterService {


    public static void printQuestionToConsole(ParkingServiceImpl parkingServiceImpl) {

        System.out.println("Enter parking size:");
        int parkingSizeFromConsole = parkingServiceImpl.getParkingSizeFromConsole();

        try {
            if (parkingSizeFromConsole <= 0) {
                System.out.println("Invalid input, please try again");
                printQuestionToConsole(parkingServiceImpl);
            }
            parkingServiceImpl.parkingHandler();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input, please try again");
            printQuestionToConsole(parkingServiceImpl);
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
        if (remainingIterate != Integer.MAX_VALUE) {
            System.out.println("Parking will be free in " + remainingIterate + " later iteration");
        }
    }


}

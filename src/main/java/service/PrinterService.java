package service;

import model.Car;
import model.Parking;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

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


    static int printAndGetNumberParkingSpace(Parking parking, List<Car> carList) {
        int numberParkingSpace = parking.getParkingSize() - carList.size();
        System.out.println("Number of parking spaces: " + numberParkingSpace);
        return numberParkingSpace;
    }

    static void printNotPlaceInParking(int emptyPlace) {
        for (int i = 1; i <= emptyPlace; i++) {
            System.out.println("Sorry, we don't have any openings.");
        }
    }

    //Collapse this method
    static void printParkingPlaceInfo(List<Car> carList) {

        for (Car car : carList) {
            System.out.println("Iteration before leaving the parking lot : " + car.getRemainingIterate() +
                    "  Parking place: " + carList.indexOf(car));
        }
    }

    static void printIterBeforeLeavingParking(List<Car> carList) {
        int remainingIterate = Integer.MAX_VALUE;
        for (Car car : carList) {

            if (remainingIterate > car.getRemainingIterate()) {
                remainingIterate = car.getRemainingIterate();
            }
        }

        if (remainingIterate != Integer.MAX_VALUE) {
            System.out.println("Parking will be free in " + remainingIterate + " later iteration");
        }
    }


}

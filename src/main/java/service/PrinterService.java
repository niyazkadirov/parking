package service;

import model.ParkingPlace;

import java.util.List;

public class PrinterService {

    public static void printQuestionToConsole(ParkingService parkingService) {

        System.out.println("Enter parking size:");

        if (parkingService.validateInputFromConsole()) {
            parkingService.parkingHandler();
        } else {
            System.out.println("Invalid command, please try again");
            printQuestionToConsole(parkingService);
        }
    }

    static void printNumberParkingSpace(int numberParkingSpace) {
        System.out.println("Amount of free parking slots " + numberParkingSpace);
    }

    static void printNotPlaceInParking(final int emptyPlace) {
        for (int i = 1; i <= emptyPlace; i++) {
            System.out.println("Sorry, we don't have any openings.");
        }
    }

    static void printParkingPlaceInfo(List<ParkingPlace> carList) {
        if (carList.size() <= 0) {
            System.out.println("parking is empty");
        }
        for (ParkingPlace parkingPlace : carList) {
            if (parkingPlace.getCar() == null) {
                continue;
            }
            System.out.println(
                    "Iteration before leaving the parking lot : "
                            + parkingPlace.getCar().getRemainingIterate()
                            + "  Parking place: "
                            + carList.indexOf(parkingPlace));
        }
    }

    static void printIterBeforeLeavingParking(int remainingIterate) {
        System.out.println("Parking will be free in " + remainingIterate + " later iteration");
    }
}

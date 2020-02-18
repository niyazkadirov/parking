package service;

import model.Car;
import model.Parking;
import model.ParkingPlace;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static service.PrinterService.*;

public class ParkingServiceImpl implements ParkingService {
    private static final int UPPER_RANGE_RANDOM = 50;
    private final Random random = new Random(System.currentTimeMillis());
    private final Parking parking = new Parking();
    private final Car car = new Car(0);
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public boolean isHasFreePlace() {
        return parking.getParkingSize() > parking.getParkingPlaceList().size();
    }

    @Override
    public void parkingHandler() {

        while (true) {

            String command = scanner.nextLine();
            switch (command) {
                case ("status"):
                    printParkingPlaceInfo(parking.getParkingPlaceList());
                    continue;

                case ("help"):
                    printHelpCommands();
                    continue;

                case (""):
                    emptyMoveHandler();
                    continue;

                case ("clear all"):
                    clearAll(parking.getParkingPlaceList());
                    continue;

                default:
                    if (!clearByIndex(command)) {
                        System.out.println("Invalid command");
                    }
            }
        }
    }

    @Override
    public int getEmptyParkingSpaces() {
        return (int)
                parking.getParkingPlaceList().stream()
                        .filter(parkingPlace -> parkingPlace.getCar() == null)
                        .count();
    }

    @Override
    public void emptyMoveHandler() {
        int randomNumber = getRandomNumber(parking);
        List<ParkingPlace> parkingPlaceList = parking.getParkingPlaceList();

        decrementAndRemoveParkingPlaceList(parkingPlaceList);
        generateCarsAndAddToParkingPlace(parkingPlaceList, randomNumber);
        parking.setEmptyPlace(
                parking.getParkingSize() - (parkingPlaceList.size() - getEmptyParkingSpaces()));

        printNumberParkingSpace(parking.getEmptyPlace());
        if (parking.getEmptyPlace() == 0) {
            printIterBeforeLeavingParking(car.getRemainingIterate());
            int emptyPlace = randomNumber - (parking.getParkingSize() - parkingPlaceList.size());
            printNotPlaceInParking(emptyPlace);
        }
    }

    @Override
    public boolean validateInputFromConsole() {
        try {
            int parkingSize = scanner.nextInt();
            parking.setParkingSize(parkingSize);

            if (parkingSize <= 0) {
                return false;
            }
        } catch (InputMismatchException ignored) {
            scanner.next();
            return false;
        }
        return true;
    }

    @Override
    public void decrementAndRemoveParkingPlaceList(List<ParkingPlace> carList) {

        int remainingIterate = Integer.MAX_VALUE;
        for (int i = 0; i <= carList.size() - 1; i++) {
            Car car = parking.getParkingPlaceList().get(i).getCar();

            if (car != null) {
                car.setRemainingIterate(car.getRemainingIterate() - 1);

                if (car.getRemainingIterate() <= 0) {
                    parking.getParkingPlaceList().get(i).setCar(null);
                }

                if (remainingIterate > car.getRemainingIterate() & car.getRemainingIterate() != 0) {
                    remainingIterate = car.getRemainingIterate();
                }
            }

        }
        car.setRemainingIterate(remainingIterate);
    }

    @Override
    public int getRandomNumber(Parking parking) {
        int numberAddedCars;
        if (parking.getParkingSize() <= 5) {
            numberAddedCars = 1;
        } else {
            int i = parking.getParkingSize() / 3;
            numberAddedCars = random.nextInt(i);
        }
        return numberAddedCars;
    }

    @Override
    public void generateCarsAndAddToParkingPlace(List<ParkingPlace> carList, final int randomNumber) {
        for (int i = 1; i <= randomNumber; i++) {
            int remainingIterate = random.nextInt(UPPER_RANGE_RANDOM);
            if (isHasFreePlace()) {
                parking.getParkingPlaceList().add(new ParkingPlace(new Car(remainingIterate <= 0 ? 1 : remainingIterate)));
            } else {
                parking.getParkingPlaceList().stream()
                        .filter(parkingPlace -> parkingPlace.getCar() == null)
                        .limit(randomNumber)
                        .forEach(parkingPlace -> parkingPlace.setCar(new Car(remainingIterate <= 0 ? 1 : remainingIterate)));
            }
        }
    }

    @Override
    public void clearAll(List<ParkingPlace> carList) {
        carList.clear();
        System.out.println("parking successfully cleared");
        printParkingPlaceInfo(carList);
    }

    @Override
    public boolean clearByIndex(String index) {
        String[] arrCommand = index.split(" ");
        if (arrCommand[0].equals("clear")) {
            try {
                List<ParkingPlace> carList = parking.getParkingPlaceList();

                carList.get(Integer.parseInt(arrCommand[1])).setCar(null);
                System.out.println("Car under index " + arrCommand[1] + " was successfully deleted");
                printParkingPlaceInfo(carList);
                return true;
            } catch (IndexOutOfBoundsException | NumberFormatException ignored) {
                System.out.println("Element not found");
            }
        }
        return false;
    }

    @Override
    public void printHelpCommands() {
        System.out.println(
                " -------------------------------------------------------------------------------- \n"
                        + "| <status> - Displays all current cars with their lifetime and parking number.   |\n"
                        + "| <clear all> - completely cleans parking.                                       |\n"
                        + "| <clear [index]> - cleans parking by index                                         |\n"
                        + " --------------------------------------------------------------------------------");
    }
}

package service;

import model.Car;
import model.Parking;

import java.util.*;

import static service.PrinterService.*;


public class ParkingServiceImpl implements ParkingService {
    private static final int UPPER_RANGE_RANDOM = 50;
    private List<Car> carList = new ArrayList<>();
    private Random random = new Random(System.currentTimeMillis());
    private Parking parking = new Parking();
    private Scanner scanner = new Scanner(System.in);

    @Override
    public boolean isHasFreePlace() {
        return parking.getParkingSize() > carList.size();
    }

    @Override
    public void parkingHandler() {


        while (true) {

            String command = scanner.nextLine();
            switch (command) {
                case ("status"):
                    printParkingPlaceInfo(carList);
                    continue;

                case ("help"):
                    printHelpCommands();
                    continue;

                case (""):
                    emptyMoveHandler();
                    continue;

                case ("clear all"):
                    clearAll(carList);
                    continue;

                default:
                    if (!clearByIndex(command)) {
                        System.out.println("Invalid command");
                    }
            }
        }
    }

    @Override
    public void emptyMoveHandler() {
        int randomNumber = getRandomNumber(parking);
        decrementAndRemoveCarList(carList);
        generateCarsAndAddToList(carList, randomNumber);

        if (printAndGetNumberParkingSpace(parking, carList) == 0) {
            printIterBeforeLeavingParking(carList);
            int emptyPlace = randomNumber - (parking.getParkingSize() - carList.size());
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
    public void decrementAndRemoveCarList(List<Car> carList) {
        for (int i = 0; i <= carList.size() - 1; i++) {
            Car car = carList.get(i);
            car.setRemainingIterate(car.getRemainingIterate() - 1);

            if (car.getRemainingIterate() <= 0) {
                carList.remove(car);
            }
        }
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
    public void generateCarsAndAddToList(List<Car> carList, int randomNumber) {
        for (int i = 1; i <= randomNumber; i++) {
            if (isHasFreePlace()) {
                int remainingIterate = random.nextInt(UPPER_RANGE_RANDOM);
                carList.add(new Car(remainingIterate <= 0 ? 1 : remainingIterate));
            }
        }
    }

    @Override
    public void clearAll(List<Car> carList) {
        carList.removeAll(carList);
    }

    @Override
    public boolean clearByIndex(String command) {
        String[] arrCommand = command.split(" ");
        if (arrCommand[0].equals("clear")) {
            try {
                carList.remove(Integer.parseInt(arrCommand[1]));
                return true;
            } catch (IndexOutOfBoundsException | NumberFormatException ignored) {
                System.out.println("Элемент не найден");
            }
        }
        return false;
    }

    @Override
    public void printHelpCommands() {
        System.out.println(
                " -------------------------------------------------------------------------------- \n" +
                        "| <status> - Displays all current cars with their lifetime and parking number.   |\n" +
                        "| <clear all> - completely cleans parking.                                       |\n" +
                        "| <clear [index]> - cleans parking by id                                         |\n" +
                        " --------------------------------------------------------------------------------"
        );
    }
}

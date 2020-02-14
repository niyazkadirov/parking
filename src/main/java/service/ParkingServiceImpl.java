package service;

import model.Car;
import model.Parking;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static service.PrinterService.*;


public class ParkingServiceImpl implements ParkingService {
    private static final int UPPER_RANGE_RANDOM = 10;
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
            String s = scanner.nextLine();
            if (!s.isEmpty()) {
                continue;
            }

            int randomNumber = getRandomNumber(parking);
            decrementAndRemoveCarList(carList);
            generateCarsAndAddToList(carList, randomNumber);
            printNumberParkingSpace(parking, carList);

            int emptyPlace = randomNumber - (parking.getParkingSize() - carList.size());
            printNotPlaceInParking(emptyPlace);
            printParkingPlaceInfo(carList);
        }
    }

    @Override
    public int getParkingSizeFromConsole() {
        int parkingSizeFromConsole = scanner.nextInt();
        parking.setParkingSize(parkingSizeFromConsole);
        return parkingSizeFromConsole;
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
}

package service;

import model.Car;
import model.Parking;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static service.PrinterService.*;


public class ParkingServiceImpl implements ParkingService {
    private List<Car> carList = new ArrayList<>();
    private Random random = new Random(System.currentTimeMillis());
    private Parking parking = new Parking();
    private Scanner scanner = new Scanner(System.in);


    @Override
    public boolean isHasFreePlace() {
        return carList.size() >= parking.getParkingSize();
    }


    @Override
    public void parkingHandler() {

        while (true) {
            String s = scanner.nextLine();
            if (!s.isEmpty()) {
                continue;
            }

            int randomNumber = getRandomNumber(parking);
            generateCarsAndAddToList(carList, randomNumber);


            decrementCarList(carList);
            carList.removeIf(car -> car.getRemainingIterate() <= 0);

            printNumberParkingSpace(parking, carList);

            int emptyPlace = randomNumber - (parking.getParkingSize() - carList.size());
            printNotPlaceInParking(emptyPlace);
            printParkingPlaceInfo(carList);
        }
    }

    @Override
    public int getParkingSizeFromConsole() {
        Scanner parkingSize = new Scanner(System.in);
        int parkingSizeFromConsole = parkingSize.nextInt();
        parking.setParkingSize(parkingSizeFromConsole);
        return parkingSizeFromConsole;
    }

    @Override
    public void decrementCarList(List<Car> carList) {
        for (Car car : carList) {
            car.setRemainingIterate(car.getRemainingIterate() - 1);
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
        if (isHasFreePlace()) {
            return;
        }
        for (int i = 1; i <= randomNumber; i++) {

            if (isHasFreePlace()) {
                continue;
            }
            carList.add(new Car(random.nextInt(10)));
        }
    }

}

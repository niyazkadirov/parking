package service;

import model.Car;

import java.util.*;
import java.util.stream.Collectors;

public class ParkingServiceImpl implements ParkingService {
    public static int parkingSize;
    private static boolean checkEnter = true;
    private static int counter;
    private static int subCounter;
    private static int leaveCars;
    private Scanner scanner = new Scanner(System.in);
    private List<Car> carList = new ArrayList<>();
    private Random random = new Random(1);
    private PrinterService printerService;

    @Override
    public boolean isEmptyParking() {
        return carList.size() < getParkingSize();
    }

    @Override
    public void addOneCarToList() {
        if (isEmptyParking()) {

            carList.add(new Car(random.nextInt(10)));
            System.out.println("Car added, in parking " + carList.size() + " cars");
            responseAgain();
        } else {
            System.out.println("Parking is full ");
            carList.forEach(car -> System.out.println("Through " + car.getParkingExpired() + " free parking space"));
            clearParking();
        }
    }

    @Override
    public void addFewCarsToList() throws InterruptedException {

        int randomNumberCars = 6;


        for (int i = 0; i <= randomNumberCars; i++) {
            Thread.sleep(2500);
            if (isEmptyParking()) {
                carList.add(new Car(random.nextInt(10)));
                System.out.println("Car added, in parking " + carList.size() + " cars");
            } else {
                System.out.println("Parking is full ");
                carList.forEach(car -> System.out.println("Through " + car.getParkingExpired() + " free parking space"));
                clearParking();
            }
        }
        responseAgain();
    }

    public void setCheckEnter() {
        checkEnter = false;
    }


    @Override
    public void addFewCarsToListAndStopByEnter() throws InterruptedException {

        int randomNumber = random.nextInt((getParkingSize() / 3) + 1);

        while (true) {
            String s = scanner.nextLine();

            if (s.isEmpty()) {
                for (int i = 0; i < carList.size(); i++) {
                    carList.get(i).setParkingExpired(carList.get(i).getParkingExpired() - 1);
                    carList.set(i, carList.get(i));
                }

                if (carList.removeIf(car -> car.getParkingExpired() <= 0)) {
                    leaveCars++;
                }

                counter++;
                subCounter++;
                if (randomNumber == subCounter) {
                    System.out.println();
                    System.out.println(leaveCars + " cars left the parking lot ");
                    System.out.println((parkingSize - carList.size()) + " more parking spaces left");
                    subCounter = 0;
                }

                if (isEmptyParking()) {
                    carList.add(new Car(random.nextInt(100)));
                } else {
                    System.out.println("Parking is full ");
                    List<Integer> collect = carList.stream().map(Car::getParkingExpired).collect(Collectors.toList());
                    System.out.println("Parking will be free in " + Collections.min(collect) + " iteration \n");
                    carList.forEach(car -> System.out.println("Parking expired: " + car.getParkingExpired()));
                    System.out.println("Wait....");
                    Thread.sleep(1500);
                    clearParking();
                }
            } else {
                break;
            }
        }
    }

    //printParkingInfo(carList);


    @Override
    public void clearParking() {

        //  System.out.println("Please wait until the place is free");

        while (!isEmptyParking()) {
            for (int i = 0; i < carList.size(); i++) {
                carList.get(i).setParkingExpired(carList.get(i).getParkingExpired() - 1);
                carList.set(i, carList.get(i));
            }
            carList.removeIf(car -> car.getParkingExpired() <= 0);
        }

        System.out.println("Parking freed up, free spaces " + (getParkingSize() - carList.size()));
        // responseAgain();
    }


    @Override
    public void responseAgain() {
        Scanner response = new Scanner(System.in);
        System.out.println("You want to add car?");
        String userResponse = response.nextLine();

        if (userResponse.equals("Yes")) {
            addOneCarToList();
        } else if (userResponse.equals("Add few cars")) {
            try {
                addFewCarsToList();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Invalid input, please try again");
            responseAgain();
        }
    }

    public void printParkingInfo(List<Car> cars) {
        System.out.println("In the parking lot of " + cars.size() + " cars");
    }

    public int getParkingSize() {
        return parkingSize;
    }

    public void setParkingSize(int parkingSizee) {
        parkingSize = parkingSizee;
    }
}

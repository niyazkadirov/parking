package service;

import model.Car;

import java.util.*;
import java.util.stream.Collectors;

public class ParkingServiceImpl implements ParkingService {
    public static int parkingSize;
    private static boolean checkEnter = true;
    private static int subCounter;
    private static int emptySeat;
    private Scanner scanner = new Scanner(System.in);
    private List<Car> carList = new ArrayList<>();
    private Random random = new Random(1);

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


    @Override
    public void addFewCarsToListAndStopByEnter() throws InterruptedException {


        while (true) {

            int randomNumber = random.nextInt((getParkingSize() / 3) + 1);
            if (getParkingSize() <= 3) {
                randomNumber = 1;
            }

            for (int i = 0; i < carList.size(); i++) {
                carList.get(i).setParkingExpired(carList.get(i).getParkingExpired() - 1);
                carList.set(i, carList.get(i));
            }

            carList.removeIf(car -> car.getParkingExpired() <= 0);

            subCounter++;
            if (randomNumber == subCounter) {
                System.out.println();
                subCounter = 0;
            }

            if (isEmptyParking()) {
                System.out.println("Number of parking spaces: " + (getParkingSize() - carList.size()));
                emptySeat = randomNumber - (getParkingSize() - carList.size());

                carList.add(new Car(random.nextInt(100)));

            } else {
                for (int i = 1; i <= emptySeat; i++) {
                    System.out.println("Parking full");
                }
                List<Integer> collect = carList.stream().map(Car::getParkingExpired).collect(Collectors.toList());
                System.out.println("Parking will be empty in " + Collections.min(collect) + " later iteration \n");
                carList.forEach(car -> System.out.println("Car life : " + car.getParkingExpired() +
                        "  Parking place: " + carList.indexOf(car)));
                System.out.println();
            }
            Thread.sleep(200);
        }
    }


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

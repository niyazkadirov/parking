package service;

import model.Car;
import util.RandomNumberHandler;

import java.util.*;
import java.util.stream.Collectors;

public class ParkingServiceImpl implements ParkingService {
    private static int parkingSize;
    private List<Car> carList = new ArrayList<>();
    private Random random = new Random(1);
    private RandomNumberHandler numberHandler = new RandomNumberHandler();

    @Override
    public boolean isEmptyParking() {
        return carList.size() < getParkingSize();
    }


    @Override
    public void addCarsToList() {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String s = scanner.nextLine();
            if (s.isEmpty()) {
                int randomNumber = numberHandler.getRandomNumber(random, getParkingSize());

                if (isEmptyParking()) {
                    for (int i = 1; i <= randomNumber; i++) {
                        if (isEmptyParking()) {
                            carList.add(new Car(random.nextInt(100)));
                        }
                    }
                }
                for (Car car : carList) {
                    car.setParkingExpired(car.getParkingExpired() - 1);
                }

                carList.removeIf(car -> car.getParkingExpired() <= 0);

                System.out.println("Number of parking spaces: " + ((getParkingSize() - carList.size())));
                int emptySeat = randomNumber - (getParkingSize() - carList.size());


                for (int i = 1; i <= emptySeat; i++) {
                    System.out.println("Sorry, we don't have any openings.");
                }

                carList.stream().distinct().forEach(car -> System.out.println("Iteration before leaving the parking lot : " + car.getParkingExpired() +
                        "  Parking place: " + carList.indexOf(car)));

                List<Integer> collect = carList.stream().map(Car::getParkingExpired).collect(Collectors.toList());
                if (!collect.isEmpty()) {
                    System.out.println("Parking will be empty in " + Collections.min(collect) + " later iteration");
                }


            }
        }
    }

    public int getParkingSize() {
        return parkingSize;
    }

    public void setParkingSize(int parkingSizee) {
        parkingSize = parkingSizee;
    }
}

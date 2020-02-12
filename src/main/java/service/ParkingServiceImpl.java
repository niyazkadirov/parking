package service;

import model.Car;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ParkingServiceImpl implements ParkingService {
    private static int parkingSize;
    private static int emptySeat;
    private List<Car> carList = new ArrayList<>();
    private Random random = new Random(1);

    @Override
    public boolean isEmptyParking() {
        return carList.size() < getParkingSize();
    }


    @Override
    public void addCarsToList() throws InterruptedException {

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


            if (isEmptyParking()) {
                System.out.println("Number of parking spaces: " + (getParkingSize() - carList.size()));
                emptySeat = randomNumber - (getParkingSize() - carList.size());

                carList.add(new Car(random.nextInt(100)));
            }

            if (!isEmptyParking()) {

                System.out.println();
                for (int i = 1; i <= emptySeat; i++) {
                    System.out.println("Parking full");
                }
                List<Integer> collect = carList.stream().map(Car::getParkingExpired).collect(Collectors.toList());
                System.out.println("\n Parking will be empty in " + Collections.min(collect) + " later iteration \n");
                carList.stream().distinct().forEach(car -> System.out.println("Car life : " + car.getParkingExpired() +
                        "  Parking place: " + carList.indexOf(car)));
                System.out.println();
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

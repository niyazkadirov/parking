package service;

import model.Car;

import java.util.*;
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
    public void addCarsToList() {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String s = scanner.nextLine();
            if (s.isEmpty()) {
                int randomNumber;
                if (getParkingSize() <= 5) {
                    randomNumber = 1;
                } else {
                    int i = getParkingSize() / 3;
                    randomNumber = random.nextInt(i); //nextInt от 1 до n (+1 делает включительно)
                    while (randomNumber == 0) {
                        randomNumber = random.nextInt(i); //nextInt от 1 до n (+1 делает включительно)
                    }
                }
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
                emptySeat = randomNumber - (getParkingSize() - carList.size());


                for (int i = 1; i <= emptySeat; i++) {
                    System.out.println("Parking full");
                }

                List<Integer> collect = carList.stream().map(Car::getParkingExpired).collect(Collectors.toList());
                if (!collect.isEmpty()) {
                    System.out.println("Parking will be empty in " + Collections.min(collect) + " later iteration");
                }
                carList.stream().distinct().forEach(car -> System.out.println("Iteration before leaving the parking lot : " + car.getParkingExpired() +
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

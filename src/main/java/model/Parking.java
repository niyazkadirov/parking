package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Parking {
    private int parkingSize;
    private List<Car> carList = new ArrayList<>();
    private Random random = new Random(1);


    private boolean isEmptyParking() {
        return carList.size() < parkingSize;
    }

    private void addCarsToList() {
        if (isEmptyParking()) {

            carList.add(new Car(random.nextInt(10)));
            System.out.println("Car added, in parking " + carList.size() + " cars");
            responseAgain();
        } else {
            System.out.println("Parking is full ");
            carList.forEach(car -> System.out.println("Through " + car.getLifeCycle() + " free parking space"));
            getEmptyParking();
        }
    }


    private void getEmptyParking() {

        System.out.println("Please wait until the place is free");

        while (!isEmptyParking()) {
            for (int i = 0; i < carList.size(); i++) {
                carList.get(i).setLifeCycle(carList.get(i).getLifeCycle() - 1);
                carList.set(i, carList.get(i));
            }
            carList.removeIf(car -> car.getLifeCycle() <= 0);
        }

        System.out.println("Parking freed up, free spaces " + (getParkingSize() - carList.size()));
        responseAgain();
    }

    void responseAgain() {
        Scanner response = new Scanner(System.in);
        System.out.println("You want to add car?");
        String userResponse = response.nextLine();

        if (userResponse.equals("Yes")) {
            addCarsToList();
        } else {
            System.out.println("Invalid input, please try again");
            responseAgain();
        }
    }


    public int getParkingSize() {
        return parkingSize;
    }

    public void setParkingSize(int parkingSize) {
        this.parkingSize = parkingSize;
    }
}

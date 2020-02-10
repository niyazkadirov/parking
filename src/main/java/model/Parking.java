package model;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.*;

public class Parking {
    public int parkingSize;
    public List<Car> carMap = new ArrayList<>();
    Random random = new Random(1);


    public void removeCarsFromList(List<Car> cars) {
        for (int i = 0; i < cars.size(); i += 1) {
            System.out.println("I'm here");
            cars.get(i).setLifeCycle(cars.get(i).getLifeCycle() + 1);
            cars.set(i, cars.get(i));
        }

        cars.removeIf(car -> car.getLifeCycle() < 0);
    }

    public boolean isEmptyParking() {
        return carMap.size() < parkingSize;
    }

    public void addCarsToList() {


        if (isEmptyParking()) {
            System.out.println(isEmptyParking());
            int lifeCycle = random.nextInt(10);
            System.out.println(lifeCycle);
            carMap.add(new Car(lifeCycle));
           // removeCarsFromList(carMap);
            System.out.println("Машина добавлена, в парковке " + carMap.size() + " машин");
            responseAgain();
        } else {
            System.out.println("Парковка заполнена ");
          //  carMap.forEach(car -> System.out.println("Через " + car.getLifeCycle() + " освободится место"));
            System.out.println(isEmptyParking());

            while (!isEmptyParking()){
                for (int i = 0; i < carMap.size(); i += 1) {
                    System.out.println("I'm here");
                    carMap.get(i).setLifeCycle(carMap.get(i).getLifeCycle() - 1);
                    carMap.set(i, carMap.get(i));
                }

                carMap.removeIf(car -> car.getLifeCycle() < 0);
            }

            System.out.println("Exit loop");
            responseAgain();
        }
    }


    public void getEmptyParking() {



    }


    public void responseAgain() {
        Scanner response = new Scanner(System.in);
        System.out.println("Хотите еще добавить?");
        String userResponse = response.nextLine();

        if (userResponse.equals("да")) {
            addCarsToList();
        } else {
            System.exit(1);
        }
    }


    public int getParkingSize() {
        return parkingSize;
    }

    public void setParkingSize(int parkingSize) {
        this.parkingSize = parkingSize;
    }
}

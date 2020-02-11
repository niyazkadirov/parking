package service;

import java.util.Scanner;

public interface ParkingService {

    boolean isEmptyParking();

    void addOneCarToList();

    void clearParking();

    void responseAgain();

    void addFewCarsToList() throws InterruptedException;

    void addFewCarsToListAndStopByEnter() throws InterruptedException;
}

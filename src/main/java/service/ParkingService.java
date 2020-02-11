package service;

public interface ParkingService {

    boolean isEmptyParking();

    void addCarsToList();

    void clearParking();

    void responseAgain();

    void addFewCarsToList() throws InterruptedException;
}

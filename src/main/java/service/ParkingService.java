package service;

public interface ParkingService {

    boolean isEmptyParking();

    void addCarsToList() throws InterruptedException;
}

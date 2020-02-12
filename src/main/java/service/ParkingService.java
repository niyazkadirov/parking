package service;

public interface ParkingService {

    boolean isEmptyParking();

    void addFewCarsToListAndStopByEnter() throws InterruptedException;
}

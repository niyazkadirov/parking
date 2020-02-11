package model;


import lombok.Data;

@Data
public class Car {
    private int parkingExpired;

    public Car(int parkingExpired) {
        this.parkingExpired = parkingExpired;
    }
}

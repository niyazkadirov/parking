package model;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Parking {
    private int parkingSize;
    private int freePlace;
    private List<ParkingPlace> parkingPlace = new ArrayList<>();
}

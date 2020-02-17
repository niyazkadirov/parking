package model;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Parking {
    private int parkingSize;
    private int emptyPlace;
    private List<ParkingPlace> parkingPlace = new ArrayList<>();
}

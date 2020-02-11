import model.Parking;

import static model.PrintConsole.printQuestion;

public class ApplicationRunner {
    public static void main(String[] args) {
        Parking parking = new Parking();
        printQuestion(parking);
    }
}

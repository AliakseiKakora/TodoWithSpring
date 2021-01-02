package by.itacademy.speeds;

import by.itacademy.speeds.exceptions.SpeedException;
import by.itacademy.speeds.model.Speed;
import by.itacademy.speeds.model.Unit;
import by.itacademy.speeds.services.SpeedService;

import java.util.List;

public class Runner {

    public static void main(String[] args) {
        SpeedService service = new SpeedService();
        String fileName = args[0];
        List<Speed> speeds;

        try {
            speeds = service.getSpeedsFromFile(fileName);

            service.convertSpeedsAndPrint(speeds);
            System.out.println();
            service.printSortedSpeedsByAscending(speeds);
            System.out.println();
            if (service.checkValueSpeedInList(2, speeds, Unit.KILOMETRES_HOUR)) {
                System.out.println("yes");
            } else {
                System.out.println("no");
            }
            System.out.println();
            service.printSortedGroupsSpeedsByDescending(speeds);
        } catch (SpeedException e) {
            e.printStackTrace();
        }
    }
}
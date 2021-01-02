package by.itacademy.speeds.services;

import by.itacademy.speeds.comparators.SpeedAscendingComparator;
import by.itacademy.speeds.comparators.SpeedDescendingComparator;
import by.itacademy.speeds.exceptions.SpeedException;
import by.itacademy.speeds.model.Speed;
import by.itacademy.speeds.model.Unit;
import by.itacademy.speeds.utils.Converter;
import by.itacademy.speeds.utils.Parser;

import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class SpeedService {

    public List<Speed> getSpeedsFromFile(String fileName) throws SpeedException {
        return new Parser().getSpeedsFromFile(fileName);
    }

    public void convertSpeedsAndPrint(List<Speed> speeds) {
        for (Speed speed: speeds) {
            float valueMetresSecond = new Converter().convertSpeedToMetresSecond(speed);
            DecimalFormat decimalFormat = new DecimalFormat("##.###");
            String formatNumber = decimalFormat.format(valueMetresSecond);
            System.out.println(speed + " = " + formatNumber + " ms");
        }
    }

    public void printSortedSpeedsByAscending(List<Speed> speeds) {
        speeds.stream().sorted(new SpeedAscendingComparator()).forEach(System.out::println);
    }

    public void printSortedGroupsSpeedsByDescending(List<Speed> speeds) {
        Comparator<Speed> comparator = new SpeedDescendingComparator();

        speeds.stream().filter(a -> a.getUnit().equals(Unit.KILOMETRES_HOUR)).
                sorted(comparator).forEach(System.out::println);
        speeds.stream().filter(a -> a.getUnit().equals(Unit.MILES_HOUR)).
                sorted(comparator).forEach(System.out::println);
        speeds.stream().filter(a -> a.getUnit().equals(Unit.KNOTS)).
                sorted(comparator).forEach(System.out::println);
        speeds.stream().filter(a -> a.getUnit().equals(Unit.METERS_SECOND)).
                sorted(comparator).forEach(System.out::println);
    }

    public boolean checkValueSpeedInList(int value, List<Speed> speeds, Unit unit) {
        boolean result = false;
        Optional<Speed> optionalSpeed = speeds.stream().filter(a -> a.getUnit().equals(unit)).
                filter(a -> a.getValue() == value).findFirst();
        if (optionalSpeed.isPresent()) {
            result = true;
        }
        return result;
    }
}
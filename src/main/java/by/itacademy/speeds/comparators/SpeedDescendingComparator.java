package by.itacademy.speeds.comparators;

import by.itacademy.speeds.model.Speed;
import by.itacademy.speeds.utils.Converter;

import java.util.Comparator;

public class SpeedDescendingComparator implements Comparator<Speed> {

    @Override
    public int compare(Speed o1, Speed o2) {
        Converter converter = new Converter();
        int result = 0;
        if (o1 == null || o2 == null) {
            return result;
        } else if (converter.convertSpeedToMetresSecond(o1) > converter.convertSpeedToMetresSecond(o2)) {
            result = -1;
            return result;
        } else if (converter.convertSpeedToMetresSecond(o1) < converter.convertSpeedToMetresSecond(o2)) {
            result = 1;
            return result;
        } else if (converter.convertSpeedToMetresSecond(o1) == converter.convertSpeedToMetresSecond(o2)) {
            return result;
        }
        return result;
    }
}
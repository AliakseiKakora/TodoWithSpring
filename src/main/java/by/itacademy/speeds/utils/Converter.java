package by.itacademy.speeds.utils;

import by.itacademy.speeds.model.Speed;

public class Converter {

    public float convertSpeedToMetresSecond(Speed speed) {
        int metersInKilometer = 1000;
        int metersInMile = 1609;
        int metersInKnot = 1852;
        int secondsInHour = 3600;
        float result;
        switch (speed.getUnit()) {
            case KILOMETRES_HOUR:
                result = (float) speed.getValue() * metersInKilometer / secondsInHour;
                break;
            case MILES_HOUR:
                result = (float) speed.getValue() * metersInMile / secondsInHour;
                break;
            case KNOTS:
                result = (float) speed.getValue() * metersInKnot / secondsInHour;
                break;
            case METERS_SECOND:
                result = speed.getValue();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + speed.getUnit());
        }
        return result;
    }
}
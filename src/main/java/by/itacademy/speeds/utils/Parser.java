package by.itacademy.speeds.utils;

import by.itacademy.speeds.exceptions.SpeedException;
import by.itacademy.speeds.model.Speed;
import by.itacademy.speeds.model.Unit;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    public List<Speed> getSpeedsFromFile(String fileName) throws SpeedException {
        File file = new File(fileName);
        List<Speed> listSpeeds;
        FileReader fileReader;
        BufferedReader reader = null;
        try {
            fileReader = new FileReader(file);
            reader = new BufferedReader(fileReader);
            String line;
            listSpeeds = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                String pattern = " +";
                String[] strings = line.split(pattern);
                Speed speed = createSpeed(strings[0], strings[1]);
                listSpeeds.add(speed);
            }
        } catch (FileNotFoundException e) {
            throw new SpeedException("File not found", e);
        } catch (IOException e) {
            throw new SpeedException("Error reading file", e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return listSpeeds;
    }

    private Speed createSpeed(String value, String unit) throws SpeedException {
        Speed speed;
        int intValue = Integer.parseInt(value);
        switch (unit) {
            case ("kmh"):
                speed = new Speed(intValue, Unit.KILOMETRES_HOUR);
                break;
            case ("mph"):
                speed = new Speed(intValue, Unit.MILES_HOUR);
                break;
            case ("kn"):
                speed = new Speed(intValue, Unit.KNOTS);
                break;
            case ("ms"):
                speed = new Speed(intValue, Unit.METERS_SECOND);
                break;
            default:
                throw new SpeedException("Invalid value");
        }
        return speed;
    }
}
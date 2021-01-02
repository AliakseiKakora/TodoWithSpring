package by.itacademy.speeds.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Unit {
    KILOMETRES_HOUR("kmh"),
    MILES_HOUR("mph"),
    KNOTS("kn"),
    METERS_SECOND("ms");

    private final String designation;
}
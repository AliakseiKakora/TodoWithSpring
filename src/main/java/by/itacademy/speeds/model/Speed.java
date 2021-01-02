package by.itacademy.speeds.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Speed {
    private final int value;
    private final Unit unit;

    @Override
    public String toString() {
        return value + " " + unit.getDesignation();
    }
}
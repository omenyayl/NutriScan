package com.nutriscan.misc.enums;

/**
 * Unit of measure
 */
public enum Unit {
    mg,
    g,
    aeg,
    kcal;

    public static Unit fromString(String string) {
        switch (string.toLowerCase().trim()) {
            case "kcal": return Unit.kcal;
            case "mg": return Unit.mg;
            case "g": return Unit.g;
            case "æg":
            case "aeg": return Unit.aeg;
        }
        throw new IllegalArgumentException("Unknown unit: " + string);
    }
}

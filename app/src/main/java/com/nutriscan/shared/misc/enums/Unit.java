package com.nutriscan.shared.misc.enums;

/**
 * Unit of measure
 */
public enum Unit {
    mg("mg"),
    g("g"),
    aeg("μg"),
    kcal("kcal");

    private String name;

    Unit(String name) {
        this.name = name;
    }

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

    @Override
    public String toString() {
        return this.name;
    }
}

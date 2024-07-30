/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilities;

/**
 *
 * @author jotam
 */
public enum DietType {
    NO_RESTRICTION("no restriction"),
    VEGAN("vegan"),
    VEGETARIAN("vegetarian"),
    HALAL("halal"),
    PESCATARIAN("pescatarian");

    private final String value;

    DietType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static DietType fromValue(String value) {
        for (DietType type : DietType.values()) {
            if (type.getValue().equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown diet type: " + value);
    }
}
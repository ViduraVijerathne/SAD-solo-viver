/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.util.regex.Pattern;

/**
 *
 * @author vidur
 */
public class Validator {
     public static boolean isValidDouble(String input) {
        String doubleRegex = "^[+-]?\\d*(\\.\\d+)?([eE][+-]?\\d+)?$";
        return Pattern.matches(doubleRegex, input);
    }
}

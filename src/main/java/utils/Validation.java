package utils;

import java.util.Date;

public class Validation {

    public static boolean validDate(String date) {
        try {
            new Date(date);
            return true;
        } catch (Exception e) {
            System.err.println("Formato de fecha no valido");
        }
        return false;
    }

    public static String leftPad(String input, int length, String fill){            
        String pad = String.format("%"+length+"s", "").replace(" ", fill) + input.trim();
        return pad.substring(pad.length() - length, pad.length());
    }
}

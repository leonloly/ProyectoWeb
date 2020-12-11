package utils;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

    public static String leftPad(String input, int length, String fill) {
        String pad = String.format("%" + length + "s", "").replace(" ", fill) + input.trim();
        return pad.substring(pad.length() - length, pad.length());
    }

    public static Map requestMap(BufferedReader reader) {
        try {
            StringBuffer jb = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                jb.append(line);
            }
            Gson gson = new Gson();
            return gson.fromJson(jb.toString(), Map.class);
        } catch (Exception e) {
            /*report an error*/
        }
        return new HashMap<String,Object>();
    }
}

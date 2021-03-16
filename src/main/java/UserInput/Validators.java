package UserInput;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class Validators {


    /**
     * Checks if the date provided is in the correct format to be used by the String to LocalDate
     * parse.
     * @param date String provided by the user.
     * @return boolean returns true if date is valid and false if format is incorrect.
     */

    public static boolean isDateValid (String date) {

        try {

            LocalDate.parse(date,
                    DateTimeFormatter.ofPattern("uuuu/M/d")
                            .withResolverStyle(ResolverStyle.STRICT));

            return true;

        } catch (DateTimeParseException e) {
            return false;
        }

    }








}

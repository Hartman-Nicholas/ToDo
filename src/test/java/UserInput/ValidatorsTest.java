package UserInput;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorsTest {

    @Test
    void isDateValid_True() {

        String date = "1986/12/20";

        assertTrue(Validators.isDateValid(date));


    }

    @Test
    void isDateValid_False() {

        String date = "198620/12";
        String date1 = "1986-12-20";
        String date2 = "19/20/2012";
        String date3 = "15-13/2020";
        String date4 = "1986/20/12";
        String date5 = "1986/12/40";


        assertFalse(Validators.isDateValid(date));
        assertFalse(Validators.isDateValid(date1));
        assertFalse(Validators.isDateValid(date2));
        assertFalse(Validators.isDateValid(date3));
        assertFalse(Validators.isDateValid(date4));
        assertFalse(Validators.isDateValid(date5));





    }
}
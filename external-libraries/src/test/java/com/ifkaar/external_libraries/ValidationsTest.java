package com.ifkaar.external_libraries;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ValidationsTest {

    @Test
    public void validateEmptyField() throws Exception {
        boolean expected = true;
        Boolean output = Validations.validateEmptyField("a");
        assertEquals(expected, output);

    }

    @Test
    public void validateName() throws Exception {
        // Valid Name. (length greater or equal to 3)
        assertTrue(Validations.validateName("Ali"));
        assertTrue(Validations.validateName("Sajid"));

        // Invalid Name
        assertFalse(Validations.validateName("sa"));
        assertFalse(Validations.validateName("ah"));
        assertFalse(Validations.validateName(""));
    }

    @Test
    public void validateEmail() throws Exception {
        // Valid email addresses
        assertTrue(Validations.validateEmail("test@example.com"));
        assertTrue(Validations.validateEmail("user@gmail.com"));

        // Invalid email addresses
        assertFalse(Validations.validateEmail("example.com"));
        assertFalse(Validations.validateEmail("user@"));
        assertFalse(Validations.validateEmail(""));
    }

    @Test
    public void validatePassword() throws Exception {
        // Valid password (length greater or equal to 8 and must be Alphanumeric)
        assertTrue(Validations.validatePassword("ahsan123"));
        assertTrue(Validations.validatePassword("Ahsan112"));

        //Invalid password
        assertFalse(Validations.validatePassword("ahsanmurtaza"));
        assertFalse(Validations.validatePassword("12345678"));
        assertFalse(Validations.validatePassword("ahsan@sdas"));
        assertFalse(Validations.validatePassword(""));
    }

    @Test
    public void validatePhoneNumber() throws Exception {
        // Valid Number (start with + sign and length greater or equal to 4)
        assertTrue(Validations.validatePhoneNumber("+923153333"));
        assertTrue(Validations.validatePhoneNumber("+111111111"));
        assertTrue(Validations.validatePhoneNumber("+9230010000"));

        // Invalid number
        assertFalse(Validations.validatePhoneNumber("+123"));
        assertFalse(Validations.validatePhoneNumber("1234"));
        assertFalse(Validations.validatePhoneNumber("12345678"));
        assertFalse(Validations.validatePhoneNumber(""));
    }
}
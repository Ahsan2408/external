package com.ifkaar.external_libraries;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringConverterTest {
    @Test
    public void toUpperCase() throws Exception {
        String expected = "SAJID";
        String output = StringConverter.toUpperCase("Sajid");

        assertEquals(expected, output);
    }

    @Test
    public void toLowerCase() throws Exception {
        String expected = "sajid";
        String output = StringConverter.toLowerCase("SAJID");

        assertEquals(expected, output);
    }

    @Test
    public void toToggleCase() throws Exception {
        String expected = "SajiD";
        String output = StringConverter.toToggleCase("sAJId");

        assertEquals(expected, output);
    }

    @Test
    public void toCamelCase() throws Exception {
        String expected = "Sajid";
        String output = StringConverter.toCamelCase("sajiD");

        assertEquals(expected, output);

    }

    @Test
    public void toSentenceCase() throws Exception {
        String expected = "This is sajid";
        String output = StringConverter.toSentenceCase("this is sajid");

        assertEquals(expected, output);
    }

    @Test
    public void toUpperCaseOnlyFirstLetter() throws Exception {
        String expected = "Sajid";
        String output = StringConverter.toUpperCaseOnlyFirstLetter("sajid");

        assertEquals(expected, output);
    }

    @Test
    public void conciseWordLength() throws Exception {
        String expected = "Alexander...";
        String output = StringConverter.conciseWordLength("AlexanderTheGreat", 12);

        assertEquals(expected, output);
    }

    @Test
    public void isAlphanumeric() throws Exception {
        Boolean expected = true;
        Boolean output = StringConverter.isAlphanumeric("Sajid123");

        assertEquals(expected, output);
    }

    @Test
    public void getInitials() throws Exception{
        //Arrange (create objects)
        String expected = "AM";
        //Act (Logic execute)
        String output = StringConverter.getInitials("ahsan Murtaza");

        //Assert (expected output)
        assertEquals(expected, output);
    }
}

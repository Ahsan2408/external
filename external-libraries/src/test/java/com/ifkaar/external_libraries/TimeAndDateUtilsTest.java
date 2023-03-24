package com.ifkaar.external_libraries;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class TimeAndDateUtilsTest {
    @Test
    public void computeDateDifference() throws Exception {
        String expectedDate = "1484197200000";
        String outputDate = "1491624000000";

        final Map<TimeUnit, Long> result = TimeAndDateUtils.computeDateDifference(
                new Date(Long.parseLong(expectedDate)),
                new Date(Long.parseLong(outputDate))
        );

        String difference = result.get(TimeUnit.DAYS).toString();
        assertEquals("85", difference);
    }

    @Test
    public void milliSecondToTimer() throws Exception {
        //Arrange (create objects)
        String expected = "2:00";
        //Act (Logic execute)
        String output = TimeAndDateUtils.milliSecondToTimer(120000);
        //Assert (expected output)
        assertEquals(expected, output);
    }
}
package com.example.demo.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class PersonalIdUtilsTest {

    @Test
    @DisplayName("Valid personal id test")
    public void shouldBeValidPersonalIdTest() {
        String validPersonalId = "11011386908";

        boolean actualValidationFlag = PersonalIdUtils.isValidPersonalId(validPersonalId);

        assertTrue(actualValidationFlag);
    }

    @Test
    @DisplayName("Invalid personal id test")
    public void shouldNotBeValidPersonalIdTest() {
        String validPersonalId = "11111111111";

        boolean actualValidationFlag = PersonalIdUtils.isValidPersonalId(validPersonalId);

        assertFalse(actualValidationFlag);
    }

    @Test
    @DisplayName("Woman from personal id test")
    public void shouldBeWomanFromPersonalIdTest() {
        String womanPersonalId = "11011386908";
        String expectedSexChar = "F";

        String actualSexChar = PersonalIdUtils.getSexFromPersonalId(womanPersonalId);

        assertEquals(expectedSexChar, actualSexChar);
    }

    @Test
    @DisplayName("Man from personal id test")
    public void shouldBeManFromPersonalIdTest() {
        String manPersonalId = "32020435319";
        String expectedSexChar = "M";

        String actualSexChar = PersonalIdUtils.getSexFromPersonalId(manPersonalId);

        assertEquals(expectedSexChar, actualSexChar);
    }

    @Test
    @DisplayName("Valid date person born before 2000th year from personal id test")
    public void shouldBeValidDateBefore2000YearTest() {
        String personalId = "68061806816";
        LocalDate expectedDate = LocalDate.parse("1968-06-18");

        LocalDate actualDate = PersonalIdUtils.getDateFromPersonalId(personalId);

        assertEquals(expectedDate, actualDate);
    }

    @Test
    @DisplayName("Valid date person born after 2000th year from personal id test")
    public void shouldBeValidDateAfter2000YearTest() {
        String personalId = "01300946113";
        LocalDate expectedDate = LocalDate.parse("2001-10-09");

        LocalDate actualDate = PersonalIdUtils.getDateFromPersonalId(personalId);

        assertEquals(expectedDate, actualDate);
    }
}

package com.example.demo.utils;

import java.time.LocalDate;
import java.util.HashMap;

public class PersonalIdUtils {

    private static final HashMap<Integer, Integer> weight = new HashMap<>() {{
        put(0, 1);
        put(1, 3);
        put(2, 7);
        put(3, 9);
        put(4, 1);
        put(5, 3);
        put(6, 7);
        put(7, 9);
        put(8, 1);
        put(9, 3);
    }};

    /**
     * First six digits in personal id make birth date in yyMMdd order.
     * MM is different for people born in different centuries as shown below:
     * people born between 1900 to 1999 have normal month value
     * people born between 2000 to 2099 add 20 to month value
     * people born between 2100 to 2199 add 40 to month value
     * people born between 2200 to 2299 add 60 to month value.
     * @param personalId - Personal id.
     * @return Birth date in yy-MM-yyyy format.
     */
    public static LocalDate getDateFromPersonalId(String personalId) {
        String day = personalId.substring(4,6);
        String month = personalId.substring(2, 4);
        String yearEnd = personalId.substring(0,2);
        String yearBeginning = "19";
        int monthNumber;

        monthNumber = Integer.parseInt(personalId.substring(2,4));

        if (monthNumber > 20 && monthNumber <= 32) {
            yearBeginning = "20";
            month = String.valueOf(monthNumber - 20);
        } else if (monthNumber > 40 && monthNumber <= 52) {
            yearBeginning = "21";
            month = String.valueOf(monthNumber - 40);
        } else if (monthNumber > 60 && monthNumber <= 72) {
            yearBeginning = "22";
            month = String.valueOf(monthNumber - 60);
        }

        if (month.length() == 1) {
            month = new StringBuilder("0").append(month).toString();
        }
        String year = new StringBuilder(yearBeginning).append(yearEnd).toString();

        String date = String.format("%s-%s-%s", year, month, day);
        return LocalDate.parse(date);
    }

    /**
     * 10th digit of personal id is sex identifier.
     * Even numbers are for women, Odd numbers are for men.
     * @param personalId - Personal id to get sex from.
     * @return Single-char sex value.
     */
    public static String getSexFromPersonalId(String personalId) {
        int sexDigit = Character.getNumericValue(personalId.charAt(9));
        if (sexDigit % 2 == 0) {
            return "F";
        } else {
            return "M";
        }
    }

    /**
     * Last digit of personal id is control digit. It is computed in special way:
     * Every digit(last excluding) has special weight. They are sequentially:
     * 1-3-7-9-1-3-7-9-1-3.
     * Every digit is multiplied by its weight. Next they are summed up
     * (if a two-digit number was obtained during multiplication, then
     * only the last one is taken to sum).
     * From 10 subtract obtained sum. Obtained difference should be equal control digit.
     * (Again if two-digit number was obtained during total, then
     *  only the last one is taken to calculate difference)
     * @param personalId - Personal id to be checked.
     * @return True/false
     */
    public static boolean isValidPersonalId(String personalId) {
        int sum = 0;

        for (int i = 0; i < personalId.length() - 1; i++) {
            int digit = Character.getNumericValue((personalId.charAt(i)));

            int result = digit * weight.get(i);
            sum += getLastDigitFromNumber(result);
        }

        int controlDigit = 10 - getLastDigitFromNumber(sum);

        return Character.getNumericValue(personalId.charAt(10)) == controlDigit;
    }

    /**
     * Helper method obtain last digit from number.
     * @param number - Number to get last digit from.
     * @return Last digit of number or number itself if one-digit.
     */
    private static int getLastDigitFromNumber(int number) {
        if (number > 9) {
            String stringNumber = String.valueOf(number);
            return Character.getNumericValue(
                    stringNumber.charAt(stringNumber.length() - 1)
            );
        } else {
            return number;
        }
    }
}

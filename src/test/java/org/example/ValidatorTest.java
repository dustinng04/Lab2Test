package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {
    private Validator validator;
    @BeforeEach
    public void setup() {
        validator = new Validator();
    }
    @AfterEach
    public void tearDown() {
        validator = null;
    }

    @Test
    public void isValidEmailAddressTest() {
        assertAll("Email Validation",
            () -> assertTrue(validator.isValidEmailAddress("test@example.com")),
            () -> assertFalse(validator.isValidEmailAddress("")),
            () -> assertFalse(validator.isValidEmailAddress(null)),
            () -> assertFalse(validator.isValidEmailAddress("plainaddress")),
            () -> assertFalse(validator.isValidEmailAddress("user@name@example.com")),
            () -> assertFalse(validator.isValidEmailAddress("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa@example.com")),
            () -> assertFalse(validator.isValidEmailAddress("user@examplecom"))
        );
    }

    @Test
    void validatePasswordTest() {
        assertAll("Password Validation",
            () -> assertTrue(validator.validatePassword("StrongPassw0rd!")),
            () -> assertFalse(validator.validatePassword("")),
            () -> assertThrows(IllegalArgumentException.class, () -> validator.validatePassword(null)),
            () -> assertFalse(validator.validatePassword("nouppercase1!")),
            () -> assertFalse(validator.validatePassword("NOLOWERCASE1!")),
            () -> assertFalse(validator.validatePassword("NoDigits!!")),
            () -> assertFalse(validator.validatePassword("NoSpecial123"))
        );
    }

    @Test
    void isValidPhoneTest() {
        assertAll("Phone Validation",
            () -> assertTrue(validator.isValidPhone("0123456789")),
            () -> assertFalse(validator.isValidPhone("012345678")),
            () -> assertFalse(validator.isValidPhone(null)),
            () -> assertFalse(validator.isValidPhone("1123456789")),
            () -> assertFalse(validator.isValidPhone("012345678A"))
        );
    }

    @Test
    void validateCreditCardTest() {
        assertAll("Credit Card Validation",
            () -> assertTrue(validator.validateCreditCard("1234567812345678")),
            () -> assertFalse(validator.validateCreditCard("123456781234567")),
            () -> assertThrows(IllegalArgumentException.class, () -> validator.validateCreditCard(null)),
            () -> assertFalse(validator.validateCreditCard("123456781234567a"))
        );
    }

    @Test
    void validatePostalCodeTest() {
        assertAll("Postal Code Validation",
            () -> assertTrue(validator.validatePostalCode("12345")),
            () -> assertFalse(validator.validatePostalCode("1234a")),
            () -> assertThrows(IllegalArgumentException.class, () -> validator.validatePostalCode(null)),
            () -> assertFalse(validator.validatePostalCode("123456")),
            () -> assertTrue(validator.validatePostalCode("12345-16789"))
        );
    }

    @Test
    void isValidDateOfBirthTest() {
        assertAll("Date of Birth Validation",
            () -> assertTrue(validator.isValidDateOfBirth(23, 5, 2001)),
            () -> assertFalse(validator.isValidDateOfBirth(29, 2, 2001)),
            () -> assertTrue(validator.isValidDateOfBirth(29, 2, 2020)),
            () -> assertFalse(validator.isValidDateOfBirth(23, 5, 2050))
        );
    }

    @Test
    void validateTimeTest() {
        assertAll(
            () ->  {assertFalse(validator.validateTime("12:60"));},
            () -> {assertTrue(validator.validateTime("12:30"));},
            () -> {assertThrows(IllegalArgumentException.class, () -> {
                validator.validateTime(null);
            });}
        );
    }

    @Test
    void validateIPAddress() {
        assertAll("IP Address Validation",
            () -> assertFalse(validator.validateIPAddress("192.168.0.1.2")),
            () -> assertFalse(validator.validateIPAddress("")),
            () -> assertTrue(validator.validateIPAddress("192.168.0.1")),
            () -> assertThrows(IllegalArgumentException.class, () -> validator.validateIPAddress(null))
        );
    }

    @Test
    void fibonacciTest() {
        assertAll("validator Calculation",
            () -> assertThrows(IllegalArgumentException.class, () -> validator.fibonacci(-1)),
            () -> assertEquals(1, validator.fibonacci(2)),
            () -> assertEquals(5, validator.fibonacci(5))
        );
    }

    @Test
    void fibonacciTest_2() {
        assertEquals(23416728348467685L, validator.fibonacci(80));
    }

    @Test
    void factorialTest() {
        assertAll("validator Calculation",
            () -> assertThrows(IllegalArgumentException.class, () -> validator.factorial(-1)),
            () -> assertEquals(1, validator.factorial(0))
        );
    }

    @Test
    void factorialTest_2() {
        BigInteger expected = new BigInteger("2432902008176640000");
        int input = 20;

        try {
            BigInteger actual = validator.factorial(input);
            assertTrue(actual.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) > 0, "Factorial result should exceed the int boundary");
            assertEquals(expected, actual);
        } catch (IllegalArgumentException e) {
            fail("Unexpected IllegalArgumentException");
        }
    }

    @ParameterizedTest
    @CsvSource({
            "abcba, true",
            "abccab, false",
            "'null', java.lang.IllegalArgumentException"
    })
    void isPalindromeTest(String input, String expected) {
        Validator validator = new Validator();

        if (expected.equals("java.lang.IllegalArgumentException")) {
            assertThrows(IllegalArgumentException.class, () -> validator.isPalindrome(null));
        } else {
            boolean actual = validator.isPalindrome(input);
            boolean expectedValue = Boolean.parseBoolean(expected);
            assertEquals(expectedValue, actual);
        }
    }
    @Test
    void isPrimeTest() {
        assertAll("Prime Number Check",
            () -> assertFalse(validator.isPrime(1)),
            () -> assertTrue(validator.isPrime(2)),
            () -> assertThrows(IllegalArgumentException.class, () -> validator.isPrime(-1))
        );
    }

    @Test
    void areAnagramsTest() {
        assertAll("Anagram Strings Check",
                () -> assertTrue(validator.areAnagrams("listen", "silent")),
                () -> assertFalse(validator.areAnagrams(null, "silent")),
                () -> assertFalse(validator.areAnagrams("speak", "peaked"))
        );
    }
}
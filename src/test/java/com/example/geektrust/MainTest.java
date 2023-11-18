package com.example.geektrust;

import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@DisplayName("IntegrationTest")
public class MainTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();


    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    @DisplayName("Integration Test - 1")
    void test_1() {


        Main.main(new String[]{"sample_input/input1.txt"});

        String expectedOutput =
                "C-Cave D-Tower G-Mansion" + System.lineSeparator() +
                        "C-Cave" + System.lineSeparator() +
                        "NO_VACANT_ROOM" + System.lineSeparator() +
                        "G-Mansion" + System.lineSeparator() +
                        "D-Tower" + System.lineSeparator() +
                        "C-Cave" + System.lineSeparator() +
                        "D-Tower" + System.lineSeparator() +
                        "INCORRECT_INPUT" + System.lineSeparator() +
                        "C-Cave" + System.lineSeparator() +
                        "G-Mansion" + System.lineSeparator() +
                        "G-Mansion" + System.lineSeparator() +
                        "NO_VACANT_ROOM";

        Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString().trim());

    }

    @Test
    @DisplayName("Integration Test - 2")
    void test_2() {


        Main.main(new String[]{"sample_input/input2.txt"});

        String expectedOutput =
                "C-Cave" + System.lineSeparator() +
                        "C-Cave" + System.lineSeparator() +
                        "INCORRECT_INPUT" + System.lineSeparator() +
                        "D-Tower" + System.lineSeparator() +
                        "G-Mansion" + System.lineSeparator() +
                        "G-Mansion" + System.lineSeparator() +
                        "G-Mansion" + System.lineSeparator() +
                        "NO_VACANT_ROOM" + System.lineSeparator() +
                        "D-Tower" + System.lineSeparator() +
                        "NO_VACANT_ROOM" + System.lineSeparator() +
                        "INCORRECT_INPUT";

        Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString().trim());

    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

}
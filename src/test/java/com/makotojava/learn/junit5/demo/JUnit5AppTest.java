package com.makotojava.learn.junit5.demo;

/*
 * Copyright 2017 Makoto Consulting Group, Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

/**
 * JUnit 5 (with JUnitPlatform.class)
 *
 */
@RunWith(JUnitPlatform.class)
@DisplayName("Testing using JUnit 5")
public class JUnit5AppTest {

  private App classUnderTest;

  @BeforeAll
  public static void init() {
    // Do something before ANY test is run in this class
  }

  @AfterAll
  public static void done() {
    // Do something after ALL tests in this class are run
  }

  @BeforeEach
  public void setUp() throws Exception {
    classUnderTest = new App();
  }

  @AfterEach
  public void tearDown() throws Exception {
    classUnderTest = null;
  }

  @Test
  @DisplayName("When numbers are > 0")
  public void testAdd() {
    assertAll(
        () -> {
          //
          // Test #1
          long[] numbersToSum = { 1, 2, 3, 4 };
          long expectedSum = 10;
          long actualSum = classUnderTest.add(numbersToSum);
          assertEquals(expectedSum, actualSum);
        },
        () -> {
          //
          // Test #2
          long[] numbersToSum = new long[] { 20, 934, 110 };
          long expectedSum = 1064;
          long actualSum = classUnderTest.add(numbersToSum);
          assertEquals(expectedSum, actualSum);
        },
        () -> {
          //
          // Test #3
          long[] numbersToSum = new long[] { 2, 4, 6 };
          long expectedSum = 12;
          long actualSum = classUnderTest.add(numbersToSum);
          assertEquals(expectedSum, actualSum);
        });
  }

  @Nested
  @DisplayName("When numbers to add are < 0")
  class NegativeNumbersTest {

    private App classUnderTest;

    @BeforeEach
    public void setUp() throws Exception {
      classUnderTest = new App();
    }

    @AfterEach
    public void tearDown() throws Exception {
      classUnderTest = null;
    }

    @Test
    @DisplayName("Three tests with numbers < 0")
    public void testAdd() {
      assertAll(
          () -> {
            //
            // Test #1
            long[] numbersToSum = { -1, -2, -3, -4 };
            long expectedSum = -10;
            long actualSum = classUnderTest.add(numbersToSum);
            assertEquals(expectedSum, actualSum);
          },
          () -> {
            //
            // Test #2
            long[] numbersToSum = { -20, -934, -110 };
            long expectedSum = -1064;
            long actualSum = classUnderTest.add(numbersToSum);
            assertEquals(expectedSum, actualSum);
          },
          () -> {
            //
            // Test #3
            long[] numbersToSum = new long[] { -2, -4, -6 };
            long expectedSum = -12;
            long actualSum = classUnderTest.add(numbersToSum);
            assertEquals(expectedSum, actualSum);
          });
    }
  }

  @Nested
  @DisplayName("When 0 < numbers > 0")
  class PositiveAndNegativeNumbersTest {

    @Test
    @DisplayName("Three tests with both positive and negative numbers")
    public void testAdd() {
      assertAll(
          () -> {
            //
            // Test #1
            long[] numbersToSum = { -1, 2, -3, 4 };
            long expectedSum = 2;
            long actualSum = classUnderTest.add(numbersToSum);
            assertEquals(expectedSum, actualSum);
          },
          () -> {
            //
            // Test #2
            long[] numbersToSum = { -20, 934, -110 };
            long expectedSum = 804;
            long actualSum = classUnderTest.add(numbersToSum);
            assertEquals(expectedSum, actualSum);
          },
          () -> {
            //
            // Test #3
            long[] numbersToSum = new long[] { -2, -4, 6 };
            long expectedSum = 0;
            long actualSum = classUnderTest.add(numbersToSum);
            assertEquals(expectedSum, actualSum);
          });
    }
  }

  @Nested
  @DisplayName("When using a single operand")
  class JUnit5AppSingleOperandTest {

    @Test
    @DisplayName("Numbers > 0")
    public void testAdd_NumbersGt0() {
      assertAll(
          () -> {
            long[] numbersToSum = { 1 };
            long expectedSum = 1;
            long actualSum = classUnderTest.add(numbersToSum);
            assertEquals(expectedSum, actualSum);
          },
          () -> {
            long[] numbersToSum = { 0 };
            long expectedSum = 0;
            long actualSum = classUnderTest.add(numbersToSum);
            assertEquals(expectedSum, actualSum);
          });
    }

    @Test
    @DisplayName("Numbers < 0")
    public void testAdd_NumbersLt0() {
      assertAll(
          () -> {
            long[] numbersToSum = { -1 };
            long expectedSum = -1;
            long actualSum = classUnderTest.add(numbersToSum);
            assertEquals(expectedSum, actualSum);
          },
          () -> {
            long[] numbersToSum = { -10 };
            long expectedSum = -10;
            long actualSum = classUnderTest.add(numbersToSum);
            assertEquals(expectedSum, actualSum);
          });
    }
  }

  @Nested
  @DisplayName("When zero operands")
  class JUnit5AppZeroOperandsTest {

    @Test()
    @DisplayName("Empty argument")
    public void testAdd_ZeroOperands_EmptyArgument() {
      long[] numbersToSum = {};
      assertThrows(IllegalArgumentException.class, () -> classUnderTest.add(numbersToSum));
    }

    @Test
    @DisplayName("Null argument")
    public void testAdd_ZeroOperands_NullArgument() {
      long[] numbersToSum = null;
      Throwable expectedException = assertThrows(IllegalArgumentException.class,
          () -> classUnderTest.add(numbersToSum));
      assertEquals("Operands argument cannot be null", expectedException.getLocalizedMessage());
    }

  }

}

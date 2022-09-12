Feature: Sum of Consecutive Odd Numbers III

  Scenario Outline: Beecrows first exemple
    Given the input is
      """
      2
      4 5
      7 4
      """
    When the program runs
    Then the output should be
      """
      45
      40
      """

  Scenario Outline: Beecrows second exemple
    Given the input is
      """
      2
      4 3
      11 2
      """
    When the program runs
    Then the output should be
      """
      21
      24
      """

  Scenario Outline: My test
    Given the input is
      """
      1
      2 11
      """
    When the program runs
    Then the output should be
      """
      143
      """

  Scenario Outline: My first error test
    Given the input is
      """
      2
      2 11
      """
    When the program runs
    Then the output should be
      """
      Parametros invalidos
      """

  Scenario Outline: My second error test
    Given the input is
      """
      1
      2 11
      2 11
      """
    When the program runs
    Then the output should be
      """
      Parametros invalidos
      """

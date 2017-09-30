Feature: Users are able to send their multiplication
  attempts, which may be correct or not. When users
  send a correct attempt, they get a response indicating
  that the result is the right one. Also, they get points
  and potentially some badges when they are right, so they
  get motivation to come back and keep playing. Badges are
  won for the first right attempt and when the user gets 100,
  500 and 999 points respectively. If users send a wrong
  attempt, they don't get any point or badge.

  Scenario: The user sends a first right attempt and gets a badge
    When the user john_snow sends 1 right attempts
    Then the user gets a response indicating the attempt is right
    And the user gets 10 points for the attempt
    And the user gets the FIRST_WON badge

  Scenario: The user sends a second right attempt and gets points only
    Given the user john_snow sends 1 right attempts
    And the user gets the FIRST_WON badge
    When the user john_snow sends 1 right attempts
    Then the user gets a response indicating the attempt is right
    And the user gets 10 points for the attempt
    And the user does not get any badge

  Scenario: The user sends a wrong attempt and gets nothing
    When the user john_snow sends 1 wrong attempts
    Then the user gets a response indicating the attempt is wrong
    And the user gets 0 points for the attempt
    And the user does not get any badge

  # Checks the Bronze, Silver and Gold badges
  Scenario Outline: The user sends a right attempt after <previous_attempts> right attempts and then gets a badge <badge_name>
    Given the user john_snow sends <previous_attempts> right attempts
    When the user john_snow sends 1 right attempts
    Then the user gets a response indicating the attempt is right
    And the user gets 10 points for the attempt
    And the user gets the <badge_name> badge

    Examples:
      | previous_attempts | badge_name           |
      | 9                 | BRONZE_MULTIPLICATOR |
      | 49                | SILVER_MULTIPLICATOR |
      | 99                | GOLD_MULTIPLICATOR   |

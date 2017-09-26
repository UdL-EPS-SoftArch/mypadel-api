Feature: Reserve a court
  in order to play a match with my own players
  As a player
  I want to reserve a court

  Scenario: Reserve a court
    Given I introduce the "day", "month", "year", "duration" and "courtType"
    When The courtType is free
    Then the court is reservered

  Scenario: Reserve a court
    Given I introduce the "day", "month", "year", "duration" and "courtType"
    When The courtType isn't free
    Then the court isn't reservered


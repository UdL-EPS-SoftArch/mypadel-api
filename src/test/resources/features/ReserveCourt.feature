Feature: Reserve a court
  in order to play a match with my own players
  As a player
  I want to reserve a court

  Scenario: Reserve a court
    Given I login as "player" with password "password"
    When I make a reservation with the "date", "duration" and "courtType"
    Then The response code is 201
    And The reservation is created and has "date", "duration" and "courtType"


  Scenario: Reserve a court
    Given I'm not logged in
    When I make a reservation with the "date ", "duration" and "courtType"
    Then The response code is 401
    And The reservation can't be created

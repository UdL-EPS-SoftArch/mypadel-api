Feature: Join match
  In order to join an already created match
  As a player
  I want to join a match


  Scenario: Successfully joined a match
    Given I login as "player" with password "password"
    When I join to a match
    Then I successfully joined a match

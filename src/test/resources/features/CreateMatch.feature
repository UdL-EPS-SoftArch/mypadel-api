Feature: Create match
  In order to organize a new match
  As a player
  I want to create a new public match

  Scenario: A player creates a public match
    Given I login as "player" with password "password"
    When I create a new public match
    Then The response code is 201
    And A match has been created

  Scenario: An admin creates a public match
    Given I login as "admin" with password "password"
    When I create a new public match
    Then The response code is 201
    And A match has been created

  Scenario: Unlogged person tries to create a public match
    Given I'm not logged in
    When I create a new public match
    Then The response code is 401


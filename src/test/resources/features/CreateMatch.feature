Feature: Create match
  In order to organize a new match
  As a player
  I want to create a new public match

  Scenario: A user creates a public match
    Given I login as "user" with password "password"
    When I click into create public match option
    And I confirm with the correct match parameters
    Then The response code is 201
    And A match has been created

  Scenario: Unlogged person tries to create a match
    Given I'm not logged in
    When I click into create public match option
    Then The response code is 401
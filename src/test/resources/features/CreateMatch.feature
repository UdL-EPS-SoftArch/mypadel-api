Feature: Create match
  In order to organize a new match
  As a player
  I want to create a new public match

  Scenario: A player creates a public match
    Given I login as "player" with password "password"
	And date 1 - 10 - 2017 with a duration of 30 minutes and a cancelation deadline for 30 - 9 - 2017
    When I create a new public match
    Then The response code is 201
    And A match has been created

  Scenario: An admin creates a public match
    Given I login as "admin" with password "password"
    And date 1 - 10 - 2017 with a duration of 30 minutes and a cancelation deadline for 30 - 9 - 2017
    When I create a new public match
    Then The response code is 201
    And A match has been created

  Scenario: Unlogged person tries to create a public match
    Given I'm not logged in
    And date 1 - 10 - 2017 with a duration of 30 minutes and a cancelation deadline for 30 - 9 - 2017
    When I create a new public match
    Then The response code is 401


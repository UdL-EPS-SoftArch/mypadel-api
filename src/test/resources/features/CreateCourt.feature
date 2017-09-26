Feature: Create court
  In order to make available a new court
  As an admin
  I want to create a new court

  Scenario: Create a new indoor court as an admin
    Given I login as "admin" with password "password"
    When I create a new "indoor" court
    Then The response code is 201
    And A new "indoor" court is "available"

  Scenario: Create a new outdoor court as an admin
    Given I login as "admin" with password "password"
    When I create a new "outdoor" court
    Then The response code is 201
    And A new "outdoor" court is "available"

  Scenario: Create a new court as non admin
    Given I login as "user" with password "password"
    When I create a new "indoor" court
    Then The response code is 401
    And A new court has not been created

  Scenario: Create a new court without being logged
    Given I'm not logged in
    When I create a new "indoor" court
    Then The response code is 401
    And A new court has not been created
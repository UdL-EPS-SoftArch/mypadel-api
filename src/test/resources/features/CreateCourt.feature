Feature Create court
  In order to make available a new court
  As an admin
  I want to create a new court

  Scenario: Create a new court as an admin
    Given I login as "admin" with password "password"
    When I create a new court
    Then The response code is 201
    And A new court is available

  Scenario: Create a new court as non admin
    Given I login as "non-admin" with password "patata"
    When I create a new court
    Then The response code is 401
    And A new court has not been created
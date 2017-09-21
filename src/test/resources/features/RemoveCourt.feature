Feature Remove court
  In order to make unavailable a court
  As an admin
  I want to remove an existing court

  Scenario: Remove a court as an admin
    Given  I login as "admin" with password "password"
    And There is an existing court
    When I remove a court
    Then The response code is 201
    And A court is unavailable

  Scenario: Remove a court that does not exist
    Given I login as "admin" with password "password"
    And The court does not exist
    When I remove a court
    Then The response code is 404

  Scenario: Remove a court as non admin
    Given I login as "non-admin" with password "patata"
    And There is an existing court
    When I remove a court
    Then The response code is 401
    And The court is available

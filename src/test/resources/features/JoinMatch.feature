Feature: Join match
  In order to join an already created match
  As a player
  I want to join a match

  Scenario: Join a match without confirm previous match results
    Given I login as "user" with password "password"
    When I join to a match with name "matchname" and datetime "01-01-2017 18:00:00"
    And I have a "none" match verification
    Then The response code is 401

  Scenario: Join a match if you joined to another match in the same datetime
    Given I login as "user" with password "password"
    When I join to a match with datetime "01-01-2017 18:00:00"
    And I join to a match with datetime "01-01-2017 18:00:00"
    Then The response code is 401

  Scenario: Succesfully joined a match
    Given I login as "user" with password "password"
    When I join to a match with datetime "01-01-2017 18:00:00"
    And I don't have any pending match verification
    Then The response code is 201
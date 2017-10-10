Feature: Invite player to match
  In order that an staff person can use the platform
  As a sport center user
  I want to invite other players to match

  Scenario: Invite a player to match
    Given I login as "player" with password "password"
    When I create new match invitation for a new match with message "A player has invited you to a match."
    Then The response code is 201
    And It has been created a new match invitation 

  Scenario: Invite a player to match without authentification
    Given I'm not logged in
    When I create new match invitation for a new match with message "A player has invited you to a match."
    Then The response code is 401
    And It has not been created a new match invitation

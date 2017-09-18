Feature: Register player
  In order that a player can use the platform
  As a sport center administrator
  I want to register a new player

  Scenario: Register new player
    Given I login as "admin" with password "password"
    When I register a new player with username "player", email "player@udl.cat" and password "password"
    Then The response code is 201
    And It has been created a player with username "player", email "player@udl.cat" and the password is not returned
Feature: Create Player
  In order to allow a new player to use the app
  As an admin
  I want to create a new player account
  Scenario: Register new player as admin
    Given I login as "admin" with password "password"
    When I register a new player with username "newplayer", email "newplayer@udl.cat",password "password", score 12 and level "NOVICE"
    Then The response code is 201
    And It has been created a player with username "newplayer", email "newplayer@udl.cat", level "NOVICE", the password and score are not returned
  Scenario: Register new player without authentication
    Given I'm not logged in
    When I register a new player with username "newplayer", email "newplayer@udl.cat",password "password", score 12 and level "NOVICE"
    Then The response code is 401
    And It has not been created a player with username "newplayer"
Feature: Remove Player
  In order to avoid a player to use the app
  As an admin
  I want to remove a player account

  Scenario: Remove a player as admin
    Given I login as "testadmin@mypadel.cat" with password "password"
    And There is an existing player with username "newplayer", email "newplayer@udl.cat",password "password", score 12 and level "NOVICE"
    When I remove a player with username "newplayer"
    Then The response code is 204
    And It has been removed the player with username "newplayer"

  Scenario: Register new player without authentication
    Given I'm not logged in
    And There is an existing player with username "newplayer", email "newplayer@udl.cat",password "password", score 12 and level "NOVICE"
    When I remove a player with username "newplayer" without authentication
    Then The response code is 401
    And It has not been removed the player with username "newplayer"

  Scenario: Remove an unexisting player as admin
    Given I login as "testadmin@mypadel.cat" with password "password"
    When I remove a player with username "newplayer"
    Then The response code is 404

#  Scenario: Remove a player as User
#    Given I login as "testplayer@mypadel.cat" with password "password"
#    And There is an existing player with username "newplayer", email "newplayer@udl.cat",password "password", score 12 and level "NOVICE"
#    When I remove a player with username "newplayer"
#    Then The response code is 401
#    And It has not been removed the player with username "newplayer"

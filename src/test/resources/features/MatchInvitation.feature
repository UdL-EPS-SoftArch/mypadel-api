Feature: Invite player to match
  In order that an staff person can use the platform
  As a sport center user
  I want to invite other players to match

  	Scenario: Invite a player to match as admin
	Given I login as "testadmin@mypadel.cat" with password "password"
	And There is a "public" match on 1 - 10 - 2017 at 1 pm for 30 minutes and deadline 30 - 9 - 2017
	When I create new match invitation for a new match for a player with username "testplayer" with message "A player has invited you to a match."
	Then The response code is 201
	And Match is created by "testadmin@mypadel.cat"
	And It has been created a new match for player "testplayer@mypadel.cat" invitation with message "A player has invited you to a match."

  	Scenario: Invite a player to match without authentification
	Given I'm not logged in
	And There is a "public" match on 1 - 10 - 2017 at 1 pm for 30 minutes and deadline 30 - 9 - 2017
	When I create new match invitation for a new match for a player with username "testplayer" with message "A player has invited you to a match."
	Then The response code is 401
	And It has not been created a new match invitation

	Scenario: Invite a player to match as a player
	Given I login as "testplayer@mypadel.cat" with password "password"
	And There is a "public" match on 1 - 10 - 2017 at 1 pm for 30 minutes and deadline 30 - 9 - 2017
	And There is a player with username "testPlayer2" and email "testplayer2@mypadel.cat"
	When I create new match invitation for a new match for a player with username "testPlayer2" with message "A player has invited you to a match."
	Then The response code is 201
	And Match is created by "testplayer@mypadel.cat"
	And It has been created a new match for player "testplayer2@mypadel.cat" invitation with message "A player has invited you to a match."


Feature: Create match
	In order to organize a new match
	As a player
	I want to create a new public match

	Scenario: A player creates a public match
		Given I login as "testplayer@mypadel.cat" with password "password"
		When I set a new public match on 1-10-2017 at 13h for 30 minutes
		And I create it
		Then The response code is 201
		And A match with the id 1 has been created
		And The match creator is "testplayer"
		And A join match with the id 1 has been created, having the match 1 and the player "testplayer"

	Scenario: An admin creates a public match
		Given I login as "testadmin@mypadel.cat" with password "password"
		When I set a new public match on 1-10-2017 at 13h for 30 minutes
		And the user creating it is "testplayer@mypadel.cat"
		And I create it
		Then The response code is 201
		And A match with the id 1 has been created
		And The match creator is "testplayer"

	Scenario: Unlogged person tries to create a public match
		Given I'm not logged in
		When I set a new public match on 1-10-2017 at 13h for 30 minutes
		And I create it
		Then The response code is 401

	Scenario: A player creates a match with wrong duration
		Given I login as "testplayer@mypadel.cat" with password "password"
		When I set a new public match on 1-10-2017 at 13h for 120 minutes
		And I create it
		Then The response code is 403

	Scenario: Player wants to create a match when he has one created
		Given I login as "testplayer@mypadel.cat" with password "password"
		When I set a new public match on 1-10-2017 at 14h for 70 minutes
		And I create it
		And I create a match with a similar hour, 13h
		Then The response code is 403

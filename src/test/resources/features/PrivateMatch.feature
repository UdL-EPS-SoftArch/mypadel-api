Feature: Create a private match
	In order to organize a new match
	As a player
	I want to create a new private match

	Scenario: A player creates a private match
		Given I login as "testplayer@mypadel.cat" with password "password"
		When I create a new private match on 12 - 10 - 2017 at 18 hours for 60 minutes and deadline 11 - 10 - 2017
		Then The response code is 201
		And A private match has been created



	Scenario: An unlogged  player creates a private match
		Given I'm not logged in
		When I create a new private match on 10 - 10 - 2017 at 18 hours for 60 minutes and deadline 9 - 10 - 2017
		Then The response code is 401
		And A private match isn't created


	Scenario: An admin creates a private match
		Given I login as "testadmin@mypadel.cat" with password "password"
		When I create a new private match on 12 - 10 - 2017 at 18 hours for 60 minutes and deadline 11 - 10 - 2017 for the user "testplayer@mypadel.cat"
		Then The response code is 201
		And A private match has been created

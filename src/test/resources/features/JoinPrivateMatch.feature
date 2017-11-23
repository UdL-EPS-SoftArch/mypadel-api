Feature: Ensure only invited players join a private match.
	In order to join a private match with an invited player
	As a player
	I want to join a match



	Scenario: An invited player joins a private match
		Given I login as "testplayer@mypadel.cat" with password "password"
			And I create a new private match on 18 - 10 - 2017 at 18 hours for 60 minutes and deadline 17 - 10 - 2017
			And the player joining it is "testplayer@mypadel.cat"
			And the player recived an invitation for Privatematch 1 for 18 - 10 - 2017 at 18 hours for 60 minutes
			When I join to a private match 1
			Then I successfully joined a private match


	Scenario: An invited player joins a private match
		Given I login as "testplayer@mypadel.cat" with password "password"
		And I create a new private match on 18 - 10 - 2017 at 18 hours for 60 minutes and deadline 17 - 10 - 2017
		And the player joining it is "testplayer@mypadel.cat"
		When the player didn't recive an invitation for Privatematch 1 for 18 - 10 - 2017 at 18 hours for 60 minutes
		Then I couldn't join a private match

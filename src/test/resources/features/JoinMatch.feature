Feature: Join match
  In order to join an already created match
  As a player
  I want to join a match


  Scenario: Successfully joined a match
    Given I login as "testplayer@mypadel.cat" with password "password"
    When I join to a match
    Then I successfully joined a match

	Scenario: A player joins a public match
		Given I login as "testplayer@mypadel.cat" with password "password"
		And the user joining it is "testplayer@mypadel.cat"
		And There is a "public" match on 1 - 10 - 2017 at 1 pm for 30 minutes and deadline 30 - 9 - 2017
		When I join to a created match 1
		Then A player has successfully joined a match 1

	Scenario: A player joins a private match
		Given I login as "testplayer@mypadel.cat" with password "password"
		And the user joining it is "testplayer@mypadel.cat"
		And There is a "private" match on 1 - 10 - 2017 at 1 pm for 30 minutes and deadline 30 - 9 - 2017
		When I join to a created match 1
		Then A player has successfully joined a match 1

	Scenario: A player joins a custom match
		Given I login as "testplayer@mypadel.cat" with password "password"
		And the user joining it is "testplayer@mypadel.cat"
		And There is a "custom" match on 1 - 10 - 2017 at 1 pm for 30 minutes and deadline 30 - 9 - 2017
		When I join to a created match 1
		Then A player has successfully joined a match 1

	Scenario: Unsuccessfully joined a match
		Given I'm not logged in
		And There is a "public" match on 11 - 10 - 2017 at 1 pm for 30 minutes and deadline 10 - 10 - 2017
		When I join to a match
		Then The response code is 401

	Scenario: Leaving a match
		Given I login as "testplayer@mypadel.cat" with password "password"
		When I already joined a match with id 1
		And I leave a match with id 1
		Then I successfully leave the match with id 1


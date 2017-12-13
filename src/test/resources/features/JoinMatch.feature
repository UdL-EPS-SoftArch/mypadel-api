Feature: Join match
  In order to join an already created match
  As a player
  I want to join a match

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
		And I have been invited to a private match 1 with the message "You have been invited to a match"
		And And It has been created a new match invitation 1
		When I join to a created private match 1
		Then A player has successfully joined a match 1

	Scenario: A player joins a private match without invitation
		Given I login as "testplayer@mypadel.cat" with password "password"
		And the user joining it is "testplayer@mypadel.cat"
		And There is a "private" match on 1 - 10 - 2017 at 1 pm for 30 minutes and deadline 30 - 9 - 2017
		When I join to a created private match 1
		Then The response code is 403

	Scenario: Unsuccessfully joined a match
		Given I'm not logged in
		And There is a "public" match on 11 - 10 - 2017 at 1 pm for 30 minutes and deadline 10 - 10 - 2017
		When I join to a match
		Then The response code is 401

	Scenario: Leaving a match
		Given I login as "testplayer@mypadel.cat" with password "password"
		And I already joined a match with id 1
		When I leave a match with id 1
		Then I successfully leave the match with id 1

	Scenario: Joining to two matches in the same datetime
		Given I login as "testplayer@mypadel.cat" with password "password"
		And There is a match on 11 - 10 - 2017 at 1 pm for 30 minutes and deadline 10-10-2017
		And There is a match on 11 - 10 - 2017 at 1 pm for 30 minutes and deadline 10-10-2017
		When I join to a created match 1
		And I join to a created match 2
		Then The response code is 403

	Scenario: Reserve a court when the match is full
		Given I login as "testplayer@mypadel.cat" with password "password"
		And There is a "public" match on 11 - 10 - 2017 at 1 pm for 30 minutes and deadline 10-10-2017
		And there are three more players in this match
		When I join to a created match 1
		Then A court is reserved

	Scenario: Cancel a reservation when a player left a full match
		Given I login as "testplayer@mypadel.cat" with password "password"
		And There is a "public" match on 11 - 10 - 2017 at 1 pm for 30 minutes and deadline 10-10-2017
		And there are three more players in this match
		And I join to a created match 1
		And A court is reserved
		When A player leaves this match
		Then The reservation for the match 1 is cancelled

	Scenario: Join a match when a player has a pending match result
		Given I login as "testplayer@mypadel.cat" with password "password"
		And There is a "public" match on 11 - 10 - 2017 at 1 pm for 30 minutes and deadline 10-10-2017
		And I join to a created match 1
		And I don't agree with the match result of the match 1
		And There is a "public" match on 11 - 12 - 2017 at 1 pm for 30 minutes and deadline 10-10-2017
		When I join to a created match 2
		Then The response code is 403

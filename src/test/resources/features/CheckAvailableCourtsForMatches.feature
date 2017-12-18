Feature: Check available courts for open matches
	Once a new Reservation is created
	The system
	Has to check if the other matches are playable

	Scenario: A new reservation makes impossible to play a match
		Given I login as "testplayer@mypadel.cat" with password "password"
		And There is an "available" court
		And I set a new public match on 21-10-2017 at 18h for 60 minutes
		And I create it
		And I add the player "testplayer" to the match
		When I make a reservation on 21/10/2017-17:00 for 90 minutes with CourtType "UNDEFINED"
		Then The match has been canceled
		And The reservation is created on on 21/10/2017-17:00 for 90 minutes with CourtType "UNDEFINED"
		And an email has been sent to "testplayer@mypadel.cat" with subject "Match cancelled" and containing "Your match has been cancelled"

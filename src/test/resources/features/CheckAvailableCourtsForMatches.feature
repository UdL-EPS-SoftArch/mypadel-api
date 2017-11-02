Feature: Check available courts for open matches
	Once a new Reservation is created
	The system
	Has to check if the other matches are playable

	Scenario: A new reservation makes impossible to play a match
		Given I login as "testplayer@mypadel.cat" with password "password"
		And There is an "available" court
		And I set a new public match on 21 - 10 - 2017 at 7 pm for 60 minutes
		And I create it
		When I make a reservation on 21/10/2017-17:00 for 90 minutes with CourtType "UNDEFINED"
		Then The match has been canceled
		And The reservation is created on on 21/10/2017-17:00 for 90 minutes with CourtType "UNDEFINED"
		And An email will be sent to the canceled match players with emails: "player1@mypadel.cat", "player2@mypadel.cat" and "player3@mypadel.cat"

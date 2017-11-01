Feature: Check available courts for open matches
	Once a new Reservation is created
	The system
	Has to check if the other matches are playable

	Scenario: A new reservation makes impossible to play a match
		Given There is an "available" court
		And I set a new public match on 21 - 10 - 2017 at 7 pm for 60 minutes
		And I create it
		When I make a reservation on 21/10/2017-17:00 for 90 minutes with CourtType "UNDEFINED"
		Then The match has been canceled

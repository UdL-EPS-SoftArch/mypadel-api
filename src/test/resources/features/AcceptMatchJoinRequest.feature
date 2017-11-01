Feature: Accept MatchJoinRequest
	In order to accept a Player requesting joining a CustomMatch
	As the creator of that CustomMatch
	I want to accept the MatchJoinRequest
	Scenario: As the owner of a Custom Match, I accept the request
		Given I login as "testplayer@mypadel.cat" with password "password"
		And  There is a custom match created on 1 - 10 - 2017 with duration 30 minutes and deadline 30 - 9 - 2017
		And There is a MatchJoinRequest for that match by user "player@mypadel.cat"
		When I accept the MatchJoinRequest
		Then The response code is 200
	Scenario: As the owner of a Custom Match, I reject the request
		Given I login as "testplayer@mypadel.cat" with password "password"
		And  There is a custom match created on 1 - 10 - 2017 with duration 30 minutes and deadline 30 - 9 - 2017
		And There is a MatchJoinRequest for that match by user "player@mypadel.cat"
		When I reject the MatchJoinRequest
		Then The response code is 200



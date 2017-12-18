Feature: Create a MatchResultVerification
	To check if the creation of a new MatchResultsVerification succeed
	As a Player/Admin
	I want to create a new MatchResultVerification for an existing MatchResult

	Scenario: A player creates a new MatchResultVerification
		Given I login as "testplayer@mypadel.cat" with password "password"
		When I set a new public match on 1-10-2017 at 13h for 30 minutes
		And I create it
		Then The response code is 201
		And There is a MatchResult for the public match player on "2017/10/1"
		When I agree through a MatchResultVerification with the MatchResult of the match played on "2017/10/1"
		Then The response code is 201
		And A new MatchResultVerification is added for the MatchResult of the match played on "2017/10/1"

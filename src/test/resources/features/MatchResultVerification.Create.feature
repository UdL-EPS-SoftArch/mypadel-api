Feature: Create a MatchResultVerification
	To check if the creation of a new MatchResultsVerification succeed
	As a Player/Admin
	I want to create a new MatchResultVerification for an existing MatchResult

	Scenario: An admin creates a new MatchResultVerification
		Given I login as "testadmin@mypadel.cat" with password "password"
		When I create a new MatchResultVerification
		Then The response code is 201
		And A new MatchResultVerification is added

	Scenario: A player creates a new MatchResultVerification
		Given I login as "testadmin@mypadel.cat" with password "password"
		When I create a new MatchResultVerification
		Then The response code is 201
		And A new MatchResultVerification is added

	Scenario: Unsigned-in user tries to create a MatchResultVerification
		Given I'm not logged in
		When I create a new MatchResultVerification
		Then The response code is 401
		And The new MatchResultVerification has not been created

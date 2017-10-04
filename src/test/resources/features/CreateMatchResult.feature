Feature: Create a MatchResult
	To check if the creation of a new MatchResults succeed
	As a Player/Admin
	I want to create a new MatchResult for an existing match that has already finished

	Scenario: An admin creates a new MatchResult
		Given I login as "admin" with password "password"
		When I create a new MatchResult
		Then The response code is 201
		And A new MatchResult is added

	Scenario: A player creates a new MatchResult
		Given I login as "admin" with password "password"
		When I create a new MatchResult
		Then The response code is 201
		And A new MatchResult is added

	Scenario: Unlogged user tries to create a public match
        Given I'm not logged in
        When I create a new public match
        Then The response code is 401
        And The new MatchResult has not been created

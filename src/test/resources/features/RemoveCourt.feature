Feature: Remove court
	In order to make unavailable a court
	As an admin
	I want to remove an existing court

	Scenario: Remove a court as an admin
		Given  I login as "admin" with password "password"
		And There is an existing court
		When I remove a court
		Then The response code is 204
		And The court has been removed

	Scenario: Remove a court that does not exist
		Given I login as "admin" with password "password"
		And The court does not exist
		When I remove a court
		Then The response code is 404

	Scenario: Remove a court as non admin
		Given I login as "user" with password "password"
		And There is an existing court
		When I remove a court
		Then The response code is 401
		And The court has not been removed

	Scenario: Remove a court without being logged
		Given I'm not logged in
		And There is an existing court
		When I remove a court
		Then The response code is 401
		And The court has not been removed

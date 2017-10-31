Feature: Cancel match
	In order to control the cancelled matches
	As an administrator
	I want a periodic control over the cancel deadlines

	Scenario: A match has reached the deadline
		Given I login as "testplayer@mypadel.cat" with password "password"
		When I create a new public match on tomorrow at same time
		Then The response code is 201
		And It has been cancelled

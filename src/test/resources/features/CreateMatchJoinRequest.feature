Feature: Create MatchJoinRequest
	In order to join a new custom match
	As a player
	I want to send a new join request

	Scenario: A player creates a match join request for a custom match
		Given I login as "testplayer@mypadel.cat" with password "password"
		And There is a custom match created on 1 - 10 - 2017 with duration 30 minutes and deadline 30 - 9 - 2017
		When The player "testplayer@mypadel.cat" creates a new matchJoinRequest for the match on 1 - 10 - 2017  with message "Hi, i would like to join your match!"
		Then The response code is 201
		And A matchJoinRequest has been created by player "testplayer@mypadel.cat" for the required match



Feature: Assign an available court to a Reservation when it is created
	In order to make the reservation of a court possible
	As the system
	I will automatically assign an available court to the reservation

	Scenario: Assign an available court to a new Reservation
		Given There is an existing court
		When A user creates a new Reservation
		Then An available court has been assigned to the reservation
		And The response code is 201

	Scenario: There are no courts available
  # Not sure what to do in this situation, we have to ask

	Scenario: There is an available court but not at the desired date-time
		Given There is an "undefined" reserved court at 15 - 12 - 2017 for 60 minutes
		When I make a reservation on 15 - 12 - 2017 for 60 minutes with CourtType "undefined"
  # Then The response code is		Specify response code

	Scenario: A users tries to manually assign a court
		Given I login as "testadmin@mypadel.cat" with password "password"
		And I make a reservation on 11 - 11 - 2017 for 60 minutes with CourtType "indoor"
		When I assign a court manually
		Then The response code is 405
		And The court has not been assigned

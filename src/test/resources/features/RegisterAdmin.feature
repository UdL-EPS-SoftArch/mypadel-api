Feature: Register admin
  In order that an staff person can use the platform
  As a sport center administrator
  I want to register a new admin

  Scenario: Register new admin as admin
    Given I login as "admin" with password "password"
    When I register a new admin with username "newadmin", email "newadmin@udl.cat" and password "password"
    Then The response code is 201
    And It has been created a admin with username "newadmin", email "newadmin@udl.cat" and the password is not returned

  Scenario: Register new admin without authentication
    Given I'm not logged in
    When I register a new admin with username "newadmin", email "newadmin@udl.cat" and password "password"
    Then The response code is 401
    And It has not been created a admin with username "newadmin"
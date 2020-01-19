Feature: Get People
  Scenario: User calls web service to get all people
    When a user retrieves the all people
    Then all people the status code is 200
    And people response includes the following
      | count 	  | 87 	              |
  Scenario: User calls web service to get a people by Id
    Given a people exists with an id of 1
    When a user retrieves the people by id
    Then a people the status code is 200
    And people response includes the following
      | name 	  | Luke Skywalker 	  |

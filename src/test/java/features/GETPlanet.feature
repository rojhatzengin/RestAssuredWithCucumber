Feature: Get Planet
  Scenario: User calls web service to get all planets
    When a user retrieves the all planets
    Then all planets the status code is 200
    And planet response includes the following
      | count 	  | 61 	              |
  Scenario: User calls web service to get a planet by Id
    Given a planet exists with an id of 1
    When a user retrieves the planet by id
    Then a planets the status code is 200
    And planet response includes the following
      | name 	  | Tatooine 	      |

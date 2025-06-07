
Feature: Validating Librray E2E Flow

  Scenario Outline: Create BooK ID with examples hardcoded
    Given "createBookAPI" request payload with "<name>" "<aisle>" "<author>"
    When user calls "createBookAPI" with "POST" https request
    Then the Response should be success with code 200
    And "Msg" message should be "successfully added"
    Examples:
      | name   | aisle      | author |
      | Dhconi | ascirle    | raina  |
      | Kochli | asrcise    | abd    |
      | Rohcit | icccsdsrle | pandya |
      | data   | Testdata   | TD1    |

  Scenario Outline: Create BooK ID with examples from excel file
    Given "createBookAPI" request payload with "<name>" "<aisle>" "<author>"
    When user calls "createBookAPI" with "POST" https request
    Then the Response should be success with code 200
    And "Msg" message should be "successfully added"
    Examples:
      | name | aisle    | author |
      | data | Testdata | TD2    |
      | data | Testdata | TD3    |








Feature: Validating Place API's


  @Ignore
  Scenario: Verify if place is being successfully added using AddPlaceAPI
    Given "addPlaceAPI" request Payload
    When user calls "addPlaceAPI" with "POST" https request
    Then the Response should be success with code 200
    And "status" message should be "OK"
    And "scope" in response should be "APP"

  @Ignore
  Scenario Outline: Verify if place is being successfully added using AddPlaceAPI
    Given "addPlaceAPI" request Payload with name "<name>" "<language>" "<address>"
    When user calls "addPlaceAPI" with "POST" https request
    Then the Response should be success with code 200
    And "status" message should be "OK"
    And "scope" in response should be "APP"
        Examples:
          | name       | address      | language |
          | Test Place | 123 Test St  | English  |
          | Demo Place | 456 Demo Ave | French   |
          | Demo Test  | 457 Demo St  | Japanese |

@addPlace
Scenario Outline: Verify if place is being successfully added using AddPlaceAPI
    Given "addPlaceAPI" request Payload with name "<name>" "<language>" "<address>"
    When user calls "addPlaceAPI" with "POST" https request
    Then the Response should be success with code 200
    And "status" message should be "OK"
    And "scope" in response should be "APP"
    And verify "<name>" "<language>" "<address>" should be equal from "getPlaceAPI" response
  Examples:
    | name       | address         | language |
    | Test Place | 123 Test Street | English  |


  @deletePlace
Scenario: Delete place API functionality
   Given delPlaceAPI request payload
   When user calls "delPlaceAPI" with "DELETE" https request
   Then the Response should be success with code 200
   And "status" message should be "OK"












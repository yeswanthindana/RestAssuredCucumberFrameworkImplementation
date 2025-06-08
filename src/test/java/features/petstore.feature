Feature: Validating Pet - Store API Details

  Scenario Outline: Place an order for the Pet
    Given "placeOrderAPI" request payload with <id> <petId> <quantity> "<shipDate>" "<status>" "<complete>"
    When user calls "placeOrderAPI" with "POST" https request
    Then the Response should be success with code 200
    And "status" message should be "<status>"
    And "id" in response should be "<id>"
    And "petId" in response should be "<petId>"
    And "quantity" in response should be "<quantity>"
    And "shipDate" in response should be "<shipDate>"
    And "status" in response should be "<status>"
    And "complete" in response should be "<complete>"
    Examples:
      | id | petId | quantity | shipDate                     | status    | complete |
      | 1  | 101   | 2        | 2023-10-01T00:00:00.000+0000 | placed    | true     |
      | 2  | 102   | 3        | 2023-10-02T00:00:00.000+0000 | shipped   | false    |
      | 3  | 103   | 1        | 2023-10-03T00:00:00.000+0000 | delivered | true     |




    Scenario: get the order by Order Id
      Given "getOrderAPI" request payload with "1"
      When user calls "getOrderAPI" with "GET" https request
      Then the Response should be success with code 200

  Scenario: delete the order by Order Id
    Given "getOrderAPI" request payload with "1"
    When user calls "getOrderAPI" with "DELETE" https request
    Then the Response should be success with code 200


  Scenario: Add Pet to the Store
    Given "addPetApi" request Payload
    When user calls "addPetApi" with "POST" https request
    Then the Response should be success with code 200





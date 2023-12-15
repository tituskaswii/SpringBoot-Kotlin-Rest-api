Feature: Product API

  Scenario: Get all products
    Given the startIndex is 0
    When the client requests GET /products
    Then the response status code should be 200
    And the response should contain products

  Scenario: Get product by ID
    Given the product ID is "123"
    When the client requests GET /products/123
    Then the response status code should be 200
    And the response should contain product details

  Scenario: Search for products
    Given the search key is "smartphone"
    When the client requests POST /products/search with searchKey "smartphone"
    Then the response status code should be 200
    And the response should contain products matching the search key
